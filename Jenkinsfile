pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = '' // Replace with your Docker registry URL (e.g., Docker Hub or private registry)
        DOCKER_CREDENTIALS_ID = 'dockerhub' // ID of your Jenkins credentials for Docker
    }

    stages {
       // Premier pipeline (pour les demandes de fusion)
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
        stage('Sonar') {
            when {
                changeRequest()
            }
            steps {
                script {
                    dir('Backendfoyer') {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Hend@1234567'
                    }
                }
            }
        }

//------------------------------------------------------------//
        // Deuxième pipeline (pour la branche hend)
        stage('Build Backend on hend') {
            when {
                branch 'hend'
            }
            steps {
                echo 'Building the backend using Maven...'
                dir('Backendfoyer') {
                    sh 'mvn clean compile'
                    sh 'mvn package'
                }
            }
        }
        stage('Build Frontend on hend') {
            when {
                branch 'hend'
            }
            steps {
                echo 'Building the frontend...'
                dir('Devop-Front') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        stage('Unit Test on hend') {
            when {
                branch 'hend'
            }
            steps {
                echo 'Running unit tests for Backend...'
                dir('Backendfoyer') {
                    sh 'mvn test'
                }
            }
        }
        stage('Sonar on hend') {
            when {
                branch 'hend'
            }
            steps {
                script {
                    dir('Backendfoyer') {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Hend@1234567'
                    }
                }
            }
        }

//------------------------------------------------------------//

        // 3rd pipeline
        stage('Docker Login') {
            when {
                branch 'release-*'
            }
            steps {
                script {
                    echo 'login'
                    withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh '''
                        docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD $DOCKER_REGISTRY
                        '''
                    }
                }
            }
        }
        stage('Build Docker Image') {
            when {
                branch 'release-*'
            }
            steps {
                script {
                    echo 'Building Docker image..'
                    sh "docker build -t hendlegleg/tpfoyer -f Backendfoyer/Dockerfile Backendfoyer/"
                    sh "docker build -t hendlegleg/tpfoyerfront -f Devop-Front/Dockerfile Devop-Front/"
                }
            }
        }
        stage('Docker Push') {
            when {
                branch 'release-*'
            }
            steps {
                script {
                    echo 'pushing'
                    withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh '''
                        docker push hendlegleg/tpfoyer 
                        docker push hendlegleg/tpfoyerfront
                        '''
                    }
                }//hhhh
            }
        }
        stage('Deploy Application') {
            steps {
                echo 'Deploying the application using the created Docker images...'
                dir('Backendfoyer') {
                    sh 'docker compose up -d'
                }
            }
        }
    }
}