pipeline {
    agent any

    environment {
        IMAGE_NAME = 'thegamdong'
        CONTAINER_NAME = 'thegamdong-container'
        PORT = '8080'
    }

    stages {
        stage('Clone') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build -x test'
            }
        }

        stage('Docker Build & Deploy') {
            steps {
                sh """
                docker stop ${CONTAINER_NAME} || true
                docker rm ${CONTAINER_NAME} || true
                docker build -t ${IMAGE_NAME}:latest .
                docker run -d --name ${CONTAINER_NAME} -p ${PORT}:${PORT} ${IMAGE_NAME}:latest
                """
            }
        }
    }
}
