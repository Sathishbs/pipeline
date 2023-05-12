pipeline {
    agent {
        docker {
        image 'sathishbs/baseimage:v1'
        args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    stages {
        stage('Checkout') {
            steps {
                sh 'ls -ltr'
                git branch: 'main', url: 'https://github.com/Sathishbs/pipeline.git'
            }
        }
        stage('Build and Test') {
             steps {
                echo sh(script: 'env|sort', returnStdout: true)
                sh 'ls -ltr'
                sh 'mvn clean package'
             }
        }
        stage('Static Code Analysis') {
             environment {
                SONAR_URL = 'http://192.168.1.25:9000'
             }
             steps {
                withCredentials([string(credentialsId: 'sonarqube-token', variable: 'TOKEN')]) {
                  sh 'mvn sonar:sonar -Dsonar.token=$TOKEN -Dsonar.host.url=${SONAR_URL}'
                }
             }
        }
        stage('Build and Push Docker Image') {
            environment {
                dockerImageName = "sathishbs/springbootapp:${BUILD_NUMBER}"
                registryCredentials = 'hub-docker-com'
            }
            steps {
                script {
                    sh 'ls -ltr'
                    def dockerImage = docker.build("${dockerImageName}")
                    docker.withRegistry('', registryCredentials) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage("Update Deployment File") {
            environment {
                gitRepositoryName = "pipeline"
                gitUserName = "sathishbs"
            }
            steps {
                withCredentials([string(credentialsId: 'github-token', variable:'GITHUB_TOKEN')]) {
                    sh '''
                        ls -ltra
                        pwd
                        git config --global user.email sathishbs@gmail.com
                        git config --global user.name "Sathish Sakshi"
                        buildNumber = ${BUILD_NUMBER}
                        sed -i "s/imageTag/${BUILD_NUMBER}/g" deployment.yml
                        git add deploymeny.yml
                        git commit -m "Update deployment image to version ${BUILD_NUMBER}"
                        echo $GITHUB_TOKEN
                        git push https://${GITHUB_TOKEN}@github.com/${gitUserName}/${gitRepositoryName} HEAD:main
                    '''
                }
            }
        }
    }
}
