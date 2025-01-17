pipeline {
    agent any

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
        // stage('Build Frontend on hend') {
        //     when {
        //         branch 'hend' 
        //     }
        //     steps {
        //         echo 'Building the frontend...'
        //         dir('Devop-Front') {
        //             sh 'npm install'
        //             sh 'npm run build'
        //         }
        //     }
        // }
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
        // stage('Sonar on hend') {
        //     when {
        //         branch 'hend'
        //     }
        //     steps {
        //         script {
        //             dir('Backendfoyer') {
        //                 sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Hend@1234567'
        //             }
        //         }
        //     }
        // }
        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker image..'
                    sh "docker build -t hendlegleg/tp-foyer:5.0.0 ."//add
                }
            }
        }
    }
}