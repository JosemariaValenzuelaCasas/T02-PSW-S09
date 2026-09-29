pipeline {
    agent any

    environment {
        // Canal de Slack donde se enviarán las notificaciones
        SLACK_CHANNEL = '#general'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Obteniendo el codigo fuente desde el repositorio...'
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Ejecutando pruebas unitarias con Maven y JUnit...'
                script {
                    if (isUnix()) {
                        sh 'mvn clean test'
                    } else {
                        bat 'mvn clean test'
                    }
                }
            }
            post {
                always {
                    // Publica los resultados de las pruebas JUnit en Jenkins
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        success {
            echo "BUILD SUCCESS: Todas las pruebas pasaron correctamente."
            echo "Job: ${env.JOB_NAME} | Build: #${env.BUILD_NUMBER} | Resultado: SUCCESS"
            
            // NOTIFICACIÓN A SLACK (Requiere el plugin 'Slack Notification' en Jenkins)
            // Descomenta la siguiente línea cuando configures el plugin de Slack en Jenkins:
            // slackSend channel: env.SLACK_CHANNEL, color: 'good', message: "BUILD SUCCESS\nJob: ${env.JOB_NAME}\nBuild: #${env.BUILD_NUMBER}\nResultado: SUCCESS"
        }
        failure {
            echo "BUILD FAILURE: Se presentaron fallos en la compilacion o en las pruebas."
            echo "Job: ${env.JOB_NAME} | Build: #${env.BUILD_NUMBER} | Resultado: FAILURE"
            
            // NOTIFICACIÓN A SLACK (Requiere el plugin 'Slack Notification' en Jenkins)
            // Descomenta la siguiente línea cuando configures el plugin de Slack en Jenkins:
            // slackSend channel: env.SLACK_CHANNEL, color: 'danger', message: "BUILD FAILURE\nJob: ${env.JOB_NAME}\nBuild: #${env.BUILD_NUMBER}\nResultado: FAILURE"
        }
    }
}
