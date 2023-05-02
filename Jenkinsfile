pipeline {
  agent {
    docker {
      image 'abhishekf5/maven-abhishek-docker-agent:v1'
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
  }
}