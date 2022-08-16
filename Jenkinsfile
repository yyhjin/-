node{

        stage('Prepare') {
                sh 'echo "Clonning Repository"'
                checkout scm
         
        }
        stage('Build Image'){
            
                sh 'echo " Image Bulid Start"'
                dir('backend'){
                image = docker.build("hseol/jangbojang")
                }
        }
  /*         stage('Build vue Image'){
               # sh 'echo " Image Bulid Start"'
                dir('frontend'){
                vuevue = docker.build("hseol/front-vue")
                }
        }
*/
        stage('Bulid Docker'){
                sh 'echo " Image Push Start"'
                 docker.withRegistry('https://registry.hub.docker.com','jang'){         
                image.push("latest")
                //vuevue.push("latest")
                 }
        }
     

        
        
}
