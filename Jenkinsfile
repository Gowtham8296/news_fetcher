pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Gowtham8296/news_fetcher.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'  // 👈 Removed -DskipTests
            }
        }

        stage('Test') {
            steps {
                // 👇 Run TestNG tests and generate reports
                sh 'mvn test'
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Publishing Extent and TestNG Reports...'
                publishHTML([
                    reportDir: 'target',
                    reportFiles: 'GoogleSearchReport.html',
                    reportName: 'Extent Report'
                ])
                publishHTML([
                    reportDir: 'test-output',
                    reportFiles: 'index.html',
                    reportName: 'TestNG Report'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed. Cleaning up workspace...'
            cleanWs()
        }
    }
}
