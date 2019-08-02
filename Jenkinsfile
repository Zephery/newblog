pipeline {
    agent {
        label "jenkins-maven"
    }
    stages{
        stage('部署'){
            stages('部署到容器云') {
                stage('check') {
                    steps{
                        echo "hello world"
                    }
                }
            }
        }
    }
}