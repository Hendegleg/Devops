pipeline {
    agent any
    environment {
        GIT_REPO = 'git@github.com:oussemabhouri/Devops.git'
    }
    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out ..'
                git branch: 'main', url: env.GIT_REPO
            }
        }

        stage('Build') {
            steps {
                echo 'Building theapp..'
                sh 'echo "Build step triggered by a commit to the main branch."'
            }
        }
    }
}