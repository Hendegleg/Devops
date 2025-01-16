pipeline {
    agent any
    environment {
        GIT_REPO = 'git@github.com:oussemabhouri/Devops.git'
        GIT_CREDENTIALS_ID = 'oussema'
    }
    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from the repository...'
                git branch: 'main',
                    url: env.GIT_REPO,
                    credentialsId: env.GIT_CREDENTIALS_ID
            }
        }

        stage('Build') {
            steps {
                echo 'Building theapp...'
                sh 'echo "Build step"'
            }
        }
    }
}
