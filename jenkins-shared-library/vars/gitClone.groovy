def call(String repository){
  stage("Git clone repository ${repository}"){
    script {
      git url: repository
    }
  }
}