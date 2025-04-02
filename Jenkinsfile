pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/prasetyo531/java.selenium4.web.git', credentialsId: '2b156766-3248-467d-9da3-cab11203e347'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        post {
                always {
                    junit 'target/surefire-reports/*.xml' // Publish JUnit test results
                    cucumber buildStatus: 'UNCHANGED', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: 'target/cucumber.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
                }
            }
    }
}