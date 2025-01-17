pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/oussema/Devops.git'
        GIT_CREDENTIALS_ID = 'oussema-access-token' 
    }

    stages {
        stage('Build Backend') {
            when {
                changeRequest()
            }
            steps {
                echo 'Building the backend using Maven...'
                dir('Backendfoyer') {
                    sh 'mvn clean compile'
                    sh 'mvn package'
                }
            }
        }
        stage('Build Frontend') {
            when {
                changeRequest()
            }
            steps {
                echo 'Building the frontend...'
                dir('Devop-Front') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
    }    
}
