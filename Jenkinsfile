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
				sh 'mvn clean'
            }
        }
         stage('Run Docker Compose and Wait for Selenium Hub') {
			steps {
				// Run Docker Compose
                sh 'docker-compose up -d'
                sh 'chmod +x ./grid-healthcheck.sh || true'

                // Call the health check script
                script {
					def result = sh(script: './grid-healthcheck.sh', returnStatus: true)
                        if (result != 0) {
						error "Selenium Hub did not become ready in time."
                        }
                    }
            }
        }
        stage('Test') {
			steps {
				sh 'mvn test'
            }
        }
    }
    post {
		always {
			junit 'target/surefire-reports/*.xml' // Publish JUnit test results
            cucumber buildStatus: 'UNCHANGED', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: 'target/cucumber.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
            }
        }
}