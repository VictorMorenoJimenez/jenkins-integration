def call(String repository, String reference){
  stage("Git clone repository ${repository}"){
    script {
      git branch: reference, url: repository
      sh './src/script.sh'
    }
  }
}