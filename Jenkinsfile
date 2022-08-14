node{

        stage('Prepare') {
                sh 'echo "Clonning Repository"'
                checkout scm
         
        }
        stage('Build Image'){
            
                sh 'echo " Image Bulid Start"'
                image = docker.build("hseol/jangbojang")
        }

        stage('Bulid Docker'){
            
                sh 'echo " Image Push Start"'
                 docker.withRegistry('https://registry.hub.docker.com','jang'){
                image.push("latest")
                 }
        }

}
