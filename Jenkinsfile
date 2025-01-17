pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/oussema/Devops.git'
        GIT_CREDENTIALS_ID = 'oussema-access-token' 
    }

    stages {
        stage('Checkout and Build') {
            when {
                branch 'test-mr' 
            }
            steps {
                echo 'Pulling code from test-mr...'
                git(
                    branch: 'test-mr',
                    url: env.GIT_REPO,
                    credentialsId: env.GIT_CREDENTIALS_ID
                )

                echo 'Building the project...'
                sh 'echo "Build process started..."'
            }
        }
    }    
}