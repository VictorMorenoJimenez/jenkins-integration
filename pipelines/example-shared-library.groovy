pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('Call shared library') {
      steps {
        gitClone 'myFakeRepo'
      }
    }
  }
}
