@Library('chs-basic-shared-library') _

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
