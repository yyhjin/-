node {
  stage('========== Clone repository ==========') {
    checkout scm
  }
  stage('========== Build image ==========') {
    app = docker.build("hseol/jangbojang") 
  }
  stage('========== Push image ==========') {
    docker.withRegistry('https://registry.hub.docker.com', 'jang') { 
    
      app.push("latest") 
    }
  }
}
