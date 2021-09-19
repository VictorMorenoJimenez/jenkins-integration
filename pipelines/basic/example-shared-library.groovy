@Library('chs-basic-shared-library') _

String repository = params.REPOSITORY
String reference = params.getOrDefault('REFERENCE', 'main')

pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('Call shared library') {
      steps {
        gitClone(repository, reference)
      }
    }
  }
}
