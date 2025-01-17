pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/oussema/Devops.git'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Pulling code..'
                withCredentials([string(credentialsId: 'oussema-access-token', variable: 'GIT_CRED')]) {
                    git(
                        branch: 'main',
                        url: env.GIT_REPO,
                        credentialsId: 'oussema-access-token'
                    )
                }
            }
        }

        stage('Build Backend') {
            when {
                branch 'test-mr' 
            }
            steps {
                withCredentials([string(credentialsId: 'oussema-access-token', variable: 'GIT_CRED')]) {
                    echo 'Building the backend..'
                    dir('Backendfoyer') {
                        sh 'mvn clean compile'
                        sh 'mvn package'
                    }
                }
            }
        }
    }
}
