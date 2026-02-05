pipeline {
    agent any

    tools {
        // Doit correspondre au nom dans Global Tool Configuration
        maven 'maven-3' 
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compilation') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                // Obligatoire pour le TP avant l'analyse Sonar
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // 'sonar' doit être le nom configuré dans : 
                // Administrer Jenkins -> Configurer le système -> SonarQube servers
                withSonarQubeEnv('sonar') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

        stage('Packaging') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
        stage('Docker Build & Push') {
            steps {
                // 1. Construction de l'image (Étape 4 du TP)
                bat 'docker build -t zak44/tp-devops-j2ee:1.0 .' 

                // 2. Connexion et Publication sécurisée
                // On appelle l'ID que tu as créé dans Jenkins
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', 
                                usernameVariable: 'DOCKER_USER', 
                                passwordVariable: 'DOCKER_TOKEN')]) {
                    
                    // Connexion au Docker Hub en utilisant le token comme mot de passe
                    bat "echo %DOCKER_TOKEN% | docker login -u %DOCKER_USER% --password-stdin"
                    
                    // Envoi de l'image (Étape 4.41 du TP)
                    bat 'docker push zak44/tp-devops-j2ee:1.0' 
                }
            }
        }
    }
}