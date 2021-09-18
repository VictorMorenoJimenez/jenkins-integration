def call(String repository){
  script {
    sh """
        echo Cloning repository ${repository}
    """
  }
}