@Library('chs-basic-shared-library') _

String repository = params.GITHUB_PR_HEAD_SHA
String reference = params.getOrDefault('REFERENCE', 'main')

pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('Call shared library') {
      steps {
        gitClone repository reference
      }
    }
  }
}
