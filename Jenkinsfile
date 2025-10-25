pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Gowtham8296/news_fetcher.git', branch: 'main'
            }
        }
      stage('Build') {
          steps {
              sh 'mvn clean install'
          }
      }
        stage('Test') {
            steps {
                sh 'mvn test' // or 'mvn test'
            }
        }
    }
}
