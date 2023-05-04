pipeline {
  agent {
    docker {
      image 'sathishbs/baseimage:v1'
      args '-v /var/run/docker.sock:/var/run.docker.sock'
    }
  }
  stages {
    stage('Checkout') {
      steps {
        sh 'ls -ltr'
        // git branch: 'main', url: 'https://github.com/Sathishbs/pipeline.git'
      }
    }
    stage('Build and Test') {
      steps {
        sh 'ls -ltr'
        sh 'mvn clean package'
      }
    }
    stage('Static Code Analysis') {
      environment {
        SONAR_URL = 'http://192.168.1.25:9091'
      }
      steps {
        withCredentials([string(credentialsId: 'sonarqube-token', variable: 'TOKEN')]) {
          sh 'mvn sonar:sonar -Dsonar.token=$TOKEN -Dsonar.host.url=${SONAR_URL}'
        }
      }
    }
    stage('Build and Push Docker Image') {
      environment {
        DOCKER_IMAGE="sathishbs/springbootapp:${BUILD_NUMBER}"
        REGISTRY_CREDENTIALS = credentials('hub-docker-com')
      }
      steps {
        script {
          def dockerImage = docker.build("${DOCKER_IMAGE}")
          dockerImage.push()
        }
      }
    }
  }  
}