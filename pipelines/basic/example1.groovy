String repository = params.REPOSITORY
String reference = params.getOrDefault('REFERENCE', 'main')

pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('Clone repository') {
      steps {
        git url: repository, branch: reference
      }
    }

    stage('Execute bash command') {
      steps {
        println(sh(script: 'ls -larth', returnStdout: true))
      }
    }
  }
}
