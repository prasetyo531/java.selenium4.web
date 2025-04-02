pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git url: 'git@github.com:prasetyo531/java.selenium4.web.git', credentialsId: 'c4c3b0c2-d58a-48ff-ac4c-b4bbdabf1107'
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