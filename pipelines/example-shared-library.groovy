@Library('chs-basic-shared-library') _

pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('Call shared library') {
      steps {
        gitClone 'https://github.com/VictorMorenoJimenez/jenkins-code-example.git'
      }
    }
  }
}
