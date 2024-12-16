pipeline {
    agent any

    tools {
     jdk 'jdk17'
     maven 'maven3'

    }

    environment{
        SCANNER_HOHE= tool 'sonar-scanner'
    }

    stages {
        stage('Git CheckOut') {
            steps {
             git branch: 'main', url: 'https://github.com/Srinu-rj/spring-food-app.git'
            }
        }

        stage(' integration Test') {
            steps {
               sh "mvn clean install -DskipTests=true"
            }
        }

        stage('Maven Compile') {
            steps {
                sh "mvn compile"
            }
        }

        stage('SonarQube-Analysis') {
            steps {
                script {
                 echo "sonarqube code analysis"
                 withSonarQubeEnv(credentialsId: 'sonar-token') {
                     sh ''' $SCANNER_HOHE/bin/sonar-scanner -Dsonar.projectName=spring-application-with-mysql  -Dsonar.projectKey=spring-application-with-mysql \
                     -Dsonar.java.binaries=. '''
                 echo "End of sonarqube code analysis"

                   }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                  echo "sonarqube Quality Gate"
                  waitForQualityGate abortPipeline: false, credentialsId: 'sonar-token'
                  echo "End Sonarqube Quality Gate"

                }
            }
        }

        stage('Mvn Build') {
            steps {
              sh "mvn package"
            }
        }

        stage("Upload jar file to Nexus"){
           steps{
               script{
                   echo "--> Nexus started <--"

                   nexusArtifactUploader artifacts:
                   [
                     [
                       artifactId: 'spring-food-app',
                       classifier: '',
                       file: 'target/spring-image.jar',
                       type: 'jar'

                       ]

                    ],
                    credentialsId: 'nexus-credential',
                    groupId: 'com.foodapp',
                    nexusUrl: '192.168.0.100:8081',
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: 'spring-maven',
                    version: '0.0.1'
               }
           }
        }


        stage('OWASP CHECK'){
            steps{
                script{
                    echo "OWASP CHECK Started"
                    dependencyCheck additionalArguments: '', nvdCredentialsId: 'nvd-api-key', odcInstallation: 'Dependency-Check installations'
                    echo "End of OWASP CHECK Started"
                }
            }
        }

        stage('Docker Images') {
            steps {
                script {
                 echo "Docker Images"
                 withDockerRegistry(credentialsId: 'docker-cred',  toolName: 'docker') {
                    sh "docker build -t spring-image ."
                 }
                 echo "End of Docker Images"
                }
            }
        }

        stage("Tag & Push to DockerHub"){
            steps{
                script {
                    echo "Tag & Push to DockerHub Started..."
                    withDockerRegistry(credentialsId: 'docker-cred', toolName: 'docker') {
                      sh "docker tag spring-image srinu641/spring-image:latest"
                      sh "docker push srinu641/spring-image:latest"
                    }
                    echo "End of Tag & Push to DockerHub"
                }
            }
        }

        stage('Docker Image Scan') {
            steps {
                sh "trivy image --format table -o trivy-image-report.html srinu641/spring-image:latest "
                sh "trivy clean --java-db"
            }
        }


    }

}