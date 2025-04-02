pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git url: 'git@github.com:prasetyo531/java.selenium4.web.git', credentialsId: '2b156766-3248-467d-9da3-cab11203e347'
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
    }
}