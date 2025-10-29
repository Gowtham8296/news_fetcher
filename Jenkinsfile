pipeline {
    agent any

    tools {
        maven 'Maven_3'
    }
    triggers {
            // Runs every day at 9 AM IST
            cron('30 3 * * *')
        }

    stages {

        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[url: 'https://github.com/Gowtham8296/news_fetcher.git']]
                ])
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

        stage('Publish HTML Reports') {
            steps {
                echo 'Publishing Extent and TestNG Reports...'

                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'GoogleSearchReport.html',
                    reportName: 'Extent Report'
                ])

                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'test-output',
                    reportFiles: 'index.html',
                    reportName: 'TestNG Report'
                ])
            }
        }

        stage('Publish JUnit Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

     post {
            always {
                echo 'Pipeline completed. Cleaning workspace...'
            }

            success {
                emailext(
                    to: 'gowthamgowthu2202@gmail.com',
                    subject: "✅ SUCCESS: Jenkins Build #${env.BUILD_NUMBER}",
                    body: """
                    <h3>Build Successful 🎉</h3>
                    <p>Project: ${env.JOB_NAME}</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p>View reports below:</p>
                    <ul>
                        <li><a href="${env.BUILD_URL}HTML_20Report/">Extent Report</a></li>
                        <li><a href="${env.BUILD_URL}TestNG_20Report/">TestNG Report</a></li>
                    </ul>
                    """,
                    mimeType: 'text/html'
                )
                cleanWs()
            }

            failure {
                emailext(
                    to: 'gowthamgowthu2202@gmail.com',
                    subject: "❌ FAILURE: Jenkins Build #${env.BUILD_NUMBER}",
                    body: """
                    <h3>Build Failed 💥</h3>
                    <p>Project: ${env.JOB_NAME}</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p><a href="${env.BUILD_URL}console">View Console Output</a></p>
                    """,
                    mimeType: 'text/html'
                )
                cleanWs()
            }
    }
}
