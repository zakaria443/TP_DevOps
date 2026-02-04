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
        stage(' Docker Build & Push') {
            steps {
                // Construction de l'image (Remplace 'ton-pseudo' par ton login Docker Hub)
                bat 'docker build -t zak44/tp-devops-j2ee:1.0 .'
                
                // Publication sur Docker Hub
                // Note: Tu dois être connecté à Docker Desktop sur ton PC pour que cela fonctionne
                bat 'docker push zak44/tp-devops-j2ee:1.0'
            }
        }
    }
}