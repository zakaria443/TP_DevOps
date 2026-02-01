pipeline {
    agent any

    tools {
        // On définit l'outil Maven (il doit être configuré dans Jenkins -> Global Tool Configuration)
        maven 'maven-3' 
    }

    stages {
        stage('Étape 1: Checkout Git') {
            steps {
                // Jenkins récupère le code de la branche sur laquelle le push a été fait
                checkout scm
                echo "Récupération du code source terminée."
            }
        }

        stage('Étape 2: Compilation') {
            steps {
                // On compile le code sans lancer les tests pour l'instant
                sh 'mvn clean compile'
            }
        }

        stage('Étape 3: Tests Unitaires') {
            steps {
                // On lance les tests JUnit
                sh 'mvn test'
            }
        }

        stage('Étape 4: Packaging (.war)') {
            steps {
                // On génère le fichier .war dans le dossier target/
                sh 'mvn package -DskipTests'
                echo "Le fichier WAR a été généré avec succès."
            }
        }

        stage('Étape 5: Analyse SonarQube') {
            steps {
                // Cette étape nécessite que SonarQube soit installé et configuré
                // Pour l'instant, on laisse un placeholder pour ne pas bloquer le build
                echo "Lancement de l'analyse SonarQube..."
                /* withSonarQubeEnv('SonarQubeServer') {
                    sh 'mvn sonar:sonar'
                }
                */
            }
        }
    }

    post {
        success {
            echo "Félicitations ! Le pipeline s'est terminé avec succès."
        }
        failure {
            echo "Le build a échoué. Vérifiez les logs pour corriger les erreurs."
        }
    }
}