pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('GitHub Stage') {
      steps {
        echo 'Hello, This is example1 from GitHub!'
      }
    }
  }
}
