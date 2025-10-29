pipeline {
    agent any

    tools {
        maven 'Maven_3' // 👈 this name must match what you configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Gowtham8296/news_fetcher.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }
    }

    post {
        always {
            // publish extent report
            publishHTML([
                reportDir: 'target',
                reportFiles: 'GoogleSearchReport.html',
                reportName: 'Extent Report'
            ])
        }
    }
}
