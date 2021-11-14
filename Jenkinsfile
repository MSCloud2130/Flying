pipeline {
    agent any
    environment {
        DOCKER_CREDENTIALS = 'docker-credentials'
        image = "juancastaneda20/${params.service}"
        dockerImage = ''
        }
    stages {
        stage('build'){
            steps {
                dir("${params.service}") {
                    script {
                        dockerImage = docker.build image + ':latest'
                    }
                }             
            }              
        }
        stage('Push the image') {
            steps {
                echo 'Pushing image'
                script {
                    docker.withRegistry( '', DOCKER_CREDENTIALS) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage ('Remove the images'){
            steps{
                sh 'docker rmi ' + image + ':latest'
            }    
        }
        stage ('Deploy') {
            steps {
                sh "kubectl rollout restart deployment ${params.service}-deployment"
            }
        }
    }
}