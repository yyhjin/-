pipeline{
    agent any
    stages {
        stage('Prepare') {
            steps {
                sh 'echo "Clonning Repository"'
                git branch: 'dev',
                    url: 'https://jangbojang:_vJ69kGzqdJARyys-4As@lab.ssafy.com/s07-webmobile1-sub2/S07P12A602.git',
                    credentialsId: 'jang'
            }
            post {
                success {
                     sh 'echo "Successfully Cloned Repository"'
                 }
                 failure {
                     sh 'echo "Fail Cloned Repository"'
                 }
            }
        }
        stage('Bulid Gradle'){
            steps{
                sh "enter backend!!"
                sh "cd backend"
                sh 'echo "Bulid Gradle Start"'
                dir('.'){
                sh "./gradlew clean build"
                }
            }
            post {
                      success {
                     sh 'echo "Successfully Built Gradle"'
                 }
                 failure {
                     sh 'echo "Bulid Gradle Fail"'
                }
            }
        }

        stage('Bulid Docker'){
            steps{
                script{
                sh 'echo " Image Bulid Start"'
                 docker.withRegistry('https://registry.hub.docker.com','jang'){
                image = docker.build("hseol/jangbojang:v4")
                image.push()
                }
             
                 }
                
                
               
            }
            post {
                 failure {
                     sh 'echo "Bulid Docker Fail"'
                }
            }
        }

    }

}
