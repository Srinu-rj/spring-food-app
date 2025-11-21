pipeline {
    agent any

    tools {
        maven "maven-lts"
    }

    parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: ['dev', 'qa', 'prod'],
            description: 'Choose the deployment environment'
        )
    }

    environment {
        SONAR_HOST_URL = "http://10.0.2.197:9000"
        SONAR_TOKEN    = credentials('SONAR_CREDS')
        NEXUS_URL      = "http://10.0.2.87:8081/repository/maven-releases/"
        ARTIFACT_ID    = "simple-java-maven-app"

        IMAGE_TAG      = "${BUILD_NUMBER}"   // unique tag for each build
    }

    stages {

        stage('Set Environment Variables') {
            steps {
                script {
                    def envConfig = [
                        dev : [
                            ACR_REPO     : "project1appdev.azurecr.io/project1-app-dev",
                            CLUSTER_NAME : "project1-aks-dev",
                            NAMESPACE    : "project1-dev"
                        ],
                        qa  : [
                            ACR_REPO     : "project1appqa.azurecr.io/project1-app-qa",
                            CLUSTER_NAME : "project1-aks-qa",
                            NAMESPACE    : "project1-qa"
                        ],
                        prod: [
                            ACR_REPO     : "project1appprod.azurecr.io/project1-app-prod",
                            CLUSTER_NAME : "project1-aks-prod",
                            NAMESPACE    : "project1-prod"
                        ]
                    ]

                    env.ACR_REPO     = envConfig[params.ENVIRONMENT].ACR_REPO
                    env.CLUSTER_NAME = envConfig[params.ENVIRONMENT].CLUSTER_NAME
                    env.NAMESPACE    = envConfig[params.ENVIRONMENT].NAMESPACE

                    echo "Deploying to environment: ${params.ENVIRONMENT}"
                    echo "ACR_REPO: ${env.ACR_REPO}"
                    echo "CLUSTER_NAME: ${env.CLUSTER_NAME}"
                    echo "NAMESPACE: ${env.NAMESPACE}"
                }
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/jenkins-docs/simple-java-maven-app.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar-lts') {
                    sh "mvn sonar:sonar -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_TOKEN"
                }
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package Artifact') {
            steps {
                sh 'mvn package'
                sh 'ls -l target/'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                script {
                    def jarFile = sh(script: "ls target/*.jar | head -n 1", returnStdout: true).trim()
                    echo "Deploying JAR: ${jarFile}"

                    def version = "1.0.${env.BUILD_NUMBER}"
                    echo "Artifact version: ${version}"

                    withCredentials([usernamePassword(credentialsId: 'NEXUS-CRED',
                                                    usernameVariable: 'NEXUS_USER',
                                                    passwordVariable: 'NEXUS_PASS')]) {
                        sh """
                        mvn deploy:deploy-file \
                            -DartifactId=${ARTIFACT_ID} \
                            -Dversion=${version} \
                            -Dpackaging=jar \
                            -Dfile=${jarFile} \
                            -DrepositoryId=nexus-releases \
                            -Durl=${NEXUS_URL} \
                            -Dusername=${NEXUS_USER} \
                            -Dpassword=${NEXUS_PASS}
                        """
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def jarFile = sh(script: "ls target/*.jar | head -n 1", returnStdout: true).trim()
                    echo "Using JAR: ${jarFile}"

                    sh """
                    echo 'FROM openjdk:17-jdk-slim
                    COPY ${jarFile} /app/${ARTIFACT_ID}.jar
                    ENTRYPOINT ["java", "-jar", "/app/${ARTIFACT_ID}.jar"]' > Dockerfile

                    docker build -t ${ACR_REPO}:${IMAGE_TAG} .
                    docker tag ${ACR_REPO}:${IMAGE_TAG} ${ACR_REPO}:latest
                    """
                }
            }
        }

        stage('Push Docker Image to ACR') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'AZURE-ACR-CRED',
                                                usernameVariable: 'ACR_USER',
                                                passwordVariable: 'ACR_PASS')]) {
                    sh """
                    echo ${ACR_PASS} | docker login ${ACR_REPO} -u ${ACR_USER} --password-stdin

                    docker push ${ACR_REPO}:${IMAGE_TAG}
                    docker push ${ACR_REPO}:latest
                    """
                }
            }
        }

        stage('Deploy to AKS') {
            steps {
                withCredentials([azureServicePrincipal(credentialsId: 'AZURE-SP-CRED',
                                                      subscriptionIdVariable: 'AZ_SUBSCRIPTION_ID',
                                                      clientIdVariable: 'AZ_CLIENT_ID',
                                                      clientSecretVariable: 'AZ_CLIENT_SECRET',
                                                      tenantIdVariable: 'AZ_TENANT_ID')]) {
                    sh """
                    az aks get-credentials --resource-group ${CLUSTER_NAME}-rg --name ${CLUSTER_NAME} --overwrite-existing

                    kubectl set image deployment/${ARTIFACT_ID}-deployment ${ARTIFACT_ID}-container=${ACR_REPO}:${IMAGE_TAG} --namespace=${NAMESPACE}
                    kubectl rollout status deployment/${ARTIFACT_ID}-deployment --namespace=${NAMESPACE}
                    """
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline finished."
        }
        failure {
            echo "Pipeline failed. Check the logs!"
        }
    }
}
