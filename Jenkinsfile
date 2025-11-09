pipeline {
   agent any

   tools {
     jdk 'jdk17'
     maven 'maven3'
   }

   enviornment {
      SCANNER_HOME= tool 'sonar-scanner'
      DEV_DESTROY = "YES"
      PROD_DESTROY = "YES"
  }

   stages {
     stage('Git Checkout') {
        steps {
               git branch: 'master', url:'https://github.com/Srinu-rj/spring-Kubernetes.git'
        }
        stage('Compile') {
            steps {
                sh "mvn compile"
            }
        }
        stage('Test') {
             steps {
                 sh "mvn compile package"
             }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
        }
        stage('File System Scan') {
            steps {
                sh "trivy fs --format table -o trivy-fs-report.html ."
            }
        }
        stage('SonarQube Analsyis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh ''' $SCANNER_HOME/bin/sonar-scanner Dsonar.projectName=spring-application -Dsonar.projectKey=spring-application \
                            -Dsonar.java.binaries=. '''
                }
            }
        }
        stage('Quality Gate') {
            steps {
                script {
                  waitForQualityGate abortPipeline: false, credentialsId: 'sonar-token'
                }
            }
        }
        stage('Build') {
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
                    credentialsId: 'nexus-crd',
                    groupId: 'com.foodapp',
                    nexusUrl: '192.168.1.6:8081',
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: 'spring-maven',
                    version: '0.0.1'


                    nexusArtifactUploader artifacts:
                     [
                       [
                         artifactId: 'ncpl-dem0',
                         classifier: '',
                         file: 'target/spring-image.jar',
                         type: 'jar'
                         ]
                      ],
                       credentialsId: 'nexus-cred',
                       groupId: 'com.example',
                       nexusUrl: '172.18.183.16:8081',
                       nexusVersion: 'nexus3', \
                       protocol: 'http',
                       repository: 'spring-maven',
                       version: '0.0.1'
               }
           }
        }
        stage('Build & Tag Docker Image') {
            steps {
               script {
                   withDockerRegistry(credentialsId: 'docker-cred', toolName: 'docker') {
                            sh "docker build -t spring-application-k8s ."
                    }
               }
            }
        }
        // Install Trivy in your VM
        stage('Docker Image Scan') {
            steps {
                sh "trivy image --format table -o trivy-image-report.html adijaiswal/boardshack:latest "
            }
        }
        stage('Tag & Push Docker Image') {
            steps {
               script {
                   withDockerRegistry(credentialsId: 'docker-cred', toolName: 'docker') {
                            sh "docker tag spring-application-k8s srinu641/spring-application-k8s:v4.0"
                            sh "docker push srinu641/spring-application-k8s:v4.0"

                    }
               }
            }
        }
        stage ("Docker Scout Image Analysis ") {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'docker') {
                        sh 'docker-scout quickview srinu641/spring-image:1.0'
                        sh 'docker-scout cves srinu641/spring-image:1.0'
                        sh 'docker-scout recommendations srinu641/spring-image:1.0'
                    }
                }
            }
        }
		stage('Checking EKS Access') {
			steps {
				withAWS(credentials: 'aws-creds') {
					sh 'aws eks update-kubeconfig --region us-east-1  --name eks-cluster'
					sh 'kubectl get pods -A'
				}
			}
		}
		stage('Creating EKS Namespaces') {
			steps {
				withAWS(credentials: 'aws-creds') {
					sh 'aws eks update-kubeconfig --region us-east-1  --name eks-cluster'
					sh 'kubectl create ns development || exit 0'
					sh 'kubectl create ns production || exit 0'
				}
			}
		}
		stage('Deploy To Dev Namespace') {
			when {
				branch 'development'
	        }
			steps {
				withAWS(credentials: 'aws-creds') {
					sh 'aws eks update-kubeconfig --region us-east-1  --name eks-cluster'
					sh 'ls -al'
					sh 'kustomize build kustomize/overlays/development'
					sh 'kubectl apply -k kustomize/overlays/development'
					sh 'kubectl get pods,deploy,svc -n development'
				}
			}
        }
		stage('Destroy App In Dev Namespace') {
			when {
				expression {
					"${env.PROD_DESTROY}" == 'YES' && "$BRANCH_NAME" == 'development'
				}
			}

			steps {
				withAWS(credentials: 'aws-creds') {
					sh 'aws eks update-kubeconfig --region us-east-1  --name eks-cluster'
					sh 'ls -al'
					sh 'kubectl delete -k kustomize/overlays/development'
					sh 'kubectl get pods,deploy,svc -n development'
				}
			}
		}
		stage('Deploy To Prod Namespace') {
			when {
				branch 'production'
			}

			steps {
				withAWS(credentials: 'aws-creds') {
					sh 'aws eks update-kubeconfig --region us-east-1  --name eks-cluster'
					sh 'ls -al'
					sh 'kustomize build kustomize/overlays/production'
					sh 'kubectl apply -k kustomize/overlays/production'
					sh 'kubectl get pods,deploy,svc -n production'
				}
			}

		stage('Destroy App In Prod Namespace') {
			when {
				expression {
					"${env.PROD_DESTROY}" == 'YES' && "$BRANCH_NAME" == 'production'
				}
			}

			steps {
				withAWS(credentials: 'aws-creds') {
					sh 'aws eks update-kubeconfig --region us-east-1  --name eks-cluster'
					sh 'ls -al'
					sh 'kubectl delete -k kustomize/overlays/production'
					sh 'kubectl get pods,deploy,svc -n production'
				}
			}
		}
     }
   }
}
