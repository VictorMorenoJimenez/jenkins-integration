@Library('github.com/releaseworks/jenkinslib') _

pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('AWS test Stage') {
      steps {
        sh 'aws --region us-east-2 s3 ls'
      }
    }
  }
}
