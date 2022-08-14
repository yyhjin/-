node{

        stage('Prepare') {
            steps {
                sh 'echo "Clonning Repository"'
                checkout csm
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
        stage('Bulid Image'){
            steps{
                sh 'echo " Image Bulid Start"'
                image = docker.build("hseol/jangbojang")
            }
            post {
                      success {
                     sh 'echo "Successfully Build Image"'
                 }
                 failure {
                     sh 'echo "Bulid Image Fail"'
                }
            }
        }

        stage('Bulid Docker'){
            steps{
                sh 'echo " Image Push Start"'
                 docker.withRegistry('https://registry.hub.docker.com','jang'){
                image.push("latest")
                }
             
                 
                
                
               
            }
            post {
                 failure {
                     sh 'echo "Bulid Docker Fail"'
                }
            }
        }

    

}
