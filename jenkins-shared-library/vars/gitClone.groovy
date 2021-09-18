def call(String repository){
  stage("Git clone repository ${repository}"){
    steps {
      script {
        git url: repository
      }
    }
  }
}