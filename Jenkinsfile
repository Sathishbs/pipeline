pipeline {
  agent {
    docker {
      image 'sathishbs/springbootapp:v1'
      args '--user root -v /var/run/docker.sock:/var/run.docker.sock'
    }
  }
  stages {
    stage('Checkout') {
      steps {
        sh 'echo git clone in progress'
        // git branch: 'main', url: 'https://github.com/Sathishbs/pipeline.git'
      }
    }
    stage('Build and Test') {
      steps {
        sh 'ls -ltr'
        sh 'cd pipeline && mvn clean package'
      }
    }
  }
}