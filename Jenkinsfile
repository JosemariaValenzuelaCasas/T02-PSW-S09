pipeline {
    agent any

    environment {
        // Canal de Slack oficial del equipo
        SLACK_CHANNEL = '#s9_jenkins-slack'
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
            
            slackSend channel: env.SLACK_CHANNEL, 
                      color: 'good', 
                      message: "*BUILD SUCCESS*\n*Job:* ${env.JOB_NAME}\n*Build:* #${env.BUILD_NUMBER}\n*Resultado:* SUCCESS"
        }
        failure {
            echo "BUILD FAILURE: Se presentaron fallos en la compilacion o en las pruebas."
            echo "Job: ${env.JOB_NAME} | Build: #${env.BUILD_NUMBER} | Resultado: FAILURE"
            
            slackSend channel: env.SLACK_CHANNEL, 
                      color: 'danger', 
                      message: "*BUILD FAILURE*\n*Job:* ${env.JOB_NAME}\n*Build:* #${env.BUILD_NUMBER}\n*Resultado:* FAILURE"
        }
    }
}
