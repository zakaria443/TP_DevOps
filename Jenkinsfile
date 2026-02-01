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
    }
}