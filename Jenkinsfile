pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            when {
                branch 'main'
            }
            steps {
                echo 'Pulling code'
                git(
                    branch: 'main',
                    url: 'https://github.com/oussema/Devops.git',
                    credentialsId: 'oussema-access-token'
                )
            }
        }
        stage('Build Backend') {
            when {
                changeRequest target: 'main'
            }
            steps {
                echo 'Building the backend using Maven...'
                dir('Backendfoyer') { 
                    sh 'mvn clean compile'
                    sh 'mvn package'
                }
            }
        }
    }
}

