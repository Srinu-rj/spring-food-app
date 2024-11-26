// @Library('my-shared-library') _
//
// pipeline {
//     agent any
//
//     tools {
//      jdk 'jdk17'
//      maven 'maven3'
//     }
//
//     parameters{
//         choice(name: 'action', choices: 'create\ndelete', description: 'Choose create/Destroy')
//         string(name: 'ImageName', defaultValue: 'spring-image', description: 'Docker image name')
//         string(name: 'ImageTag', defaultValue: '1.0', description: 'Docker image tag')
//         string(name: 'DockerHubUser', defaultValue: 'srinu641', description: 'DockerHub Username')
//         string(name: 'action', defaultValue: 'create', description: 'Pipeline action to perform')
//     }
//
//     environment{
//         SCANNER_HOHE= tool 'sonar-scanner'
//         // DOCKER_HUB_USER = credentials('dnsrinu143@gmail.com')  // Docker Hub username
//         // DOCKER_HUB_PASSWORD = credentials('srinu1234')  // Docker Hub password stored in Jenkins credentials
//
//
//     }
//
//     stages {
//         stage('Git CheckOut') {
//
//             when {expression {params.action == 'create'} }
//             steps {
//                 echo "Running Git CheckOut..."
//                 gitCheckOut(
//                         branch: "main",
//                         url: "https://github.com/Srinu-rj/spring-food-app.git"
//                 )
//             }
//         }
//
//         stage('Unit Test maven'){
//             when {expression {params.action == 'create'} }
//             steps{
//                 echo "Running Unit Test maven..."
//                 mvnTest()
//             }
//         }
//
//         stage('Integration Test maven'){
//             when {
//                 expression { params.action == 'create'}
//             }
//             steps{
//                 echo "Running Integration Test maven..."
//                 mvnIntegratenTest()
//             }
//         }
//
//         stage('Static code analysis: Sonarqube'){
//             when {
//                 expression {  params.action == 'create' }
//             }
//             steps{
//                 echo "Running Static code analysis: Sonarqube..."
//                 script{
//                     statiCodeAnalysis()
//                 }
//             }
//         }
//
//         stage('Quality Gate Status Check : Sonarqube'){
//             when {
//                 expression {  params.action == 'create' }
//             }
//             steps{
//                 script{
//                     echo "Running Quality Gate Status Check : Sonarqube..."
//                     //   def SonarQubecredentialsId = 'sonar-scanner'
//                     sonarQualityGate()
//                 }
//             }
//         }
//
//         stage('Maven Build : maven'){
//             when { expression {  params.action == 'create' } }
//             steps{
//                 script{
//                     echo "Running Maven Build : maven ..."
//                     mvnBuild()
//                 }
//             }
//         }
//
//         // stage('DEPN Check'){
//         //  when {
//         //     expression {  params.action == 'create' }
//         //  }
//         //     steps{
//         //       script{
//         //           dependencyCheck()
//         //       }
//         //     }
//         // }
//
//         stage('Docker Image Build '){
//             when { expression {  params.action == 'create' } }
//             steps{
//                 script{
//                     echo "Running Docker Image Build..."
//                     dockerBuild("${params.ImageName}", "${params.ImageTag}", "${params.DockerHubUser}")
//                 }
//             }
//         }
//
//         stage('Docker Image Scan: trivy '){
//             when { expression {  params.action == 'create' } }
//             steps{
//                 script{
//                     echo "Running Docker Image Scan With trivy..."
//                     DockerScannTry("${params.ImageName}","${params.ImageTag}","${params.DockerHubUser}")
//                 }
//             }
//         }
//
//         stage('Docker Image Push : DockerHub '){
//             when { expression {  params.action == 'create' } }
//             steps{
//                 script{
//                     echo "Running Docker Image Push : DockerHub..."
//                     dockerBuildAndPushToHub("${params.ImageName}", "${params.ImageTag}", "${params.DockerHubUser}")
//
//                 }
//             }
//         }
//
//         stage('Qodana') {
//             environment {
//                 QODANA_TOKEN = credentials('qodana-token')
//             }
//             agent {
//                 docker {
//                     args '''
//                         -v "${WORKSPACE}":/data/project
//                         --entrypoint=""
//                         '''
//                     image 'jetbrains/qodana-jvm-community'
//                 }
//             }
//             when {
//                 branch 'main'
//             }
//             steps {
//                 sh '''qodana'''
//             }
//         }
//     }
// }
