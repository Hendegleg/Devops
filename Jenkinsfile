pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/oussema/Devops.git'
        GIT_CREDENTIALS_ID = 'oussema-access-token' 
    }

    stages {
        stage('Checkout') {
            when { 
                changeRequest target: 'main' 
            }
            steps {
                echo 'Pulling code from PR-test'
                echo 'Building the project...'
                sh 'echo "Build process started..."'
                
            }
        }
    }    
}
