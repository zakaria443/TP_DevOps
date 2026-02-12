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
                // 1. Déconnexion préalable pour nettoyer toute session erronée
                bat 'docker logout'

                // 2. Build de l'image (Utilise l'accès anonyme par défaut, plus fiable pour Tomcat)
                bat 'docker build -t zak44/tp-devops-j2ee:1.0 .'

                // 3. Authentification UNIQUEMENT pour le Push
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', 
                                usernameVariable: 'DOCKER_USER', 
                                passwordVariable: 'DOCKER_TOKEN')]) {
                    
                    // On utilise le token proprement
                    bat "echo %DOCKER_TOKEN% | docker login -u %DOCKER_USER% --password-stdin"
                    bat 'docker push zak44/tp-devops-j2ee:1.0'
                }
            }
        }
        stage('Step 7: Kubernetes Deployment') {
            steps {
                script {
                    // On ajoute --validate=false pour ignorer l'erreur d'authentification réseau
                    bat 'kubectl apply -f k8s/ --validate=false'
                    
                    // On force le redémarrage pour appliquer les changements du UserDAO
                    bat 'kubectl rollout restart deployment/jee-app'
                    
                    echo "Déploiement Kubernetes réussi sans validation externe."
                }
            }
        }
    }
}