pipeline {
    agent any

    stages {
        stage('Checkout Code') {
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
                branch 'test-mr' 
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
