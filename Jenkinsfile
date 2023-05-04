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
        sh 'ls -l'
//         git branch: 'main', url: 'https://github.com/Sathishbs/pipeline.git'
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
        withCredentials([string(credentialsId: 'sonar', variable: 'sonarqube-token')])
         sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=${SONAR_URL}'
      }
    }
    stage('Build and upload Image') {
      environment {
        DOCKER_IMAGE = "sathishbs/springbootapp:${BUILD_NUMBER}"
        REGISTRY_CREDENTIALS = credentials('docker-credentials')
      }
      steps {
        script {
          sh 'cd pipeline && docker build -t ${DOCKER_IMAGE} .'
          def dockerImage = docker.image("${DOCKER_IMAGE}")
          docker.withRegistry('https://index.docker.io/v1', "docker-cred") {
            dockerImage.push()
          }
        }
      }
    }
  }
}