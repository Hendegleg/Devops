pipeline {
    agent any
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
                echo 'Building the frontend...' //h
                dir('Devop-Front') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        stage('Unit Test') {
            when {
                changeRequest()
            }
            steps {
                echo 'Running unit tests for Backend...'
                dir('Backendfoyer') {
                    sh 'mvn test'
                }
            }
        }
    }    
}