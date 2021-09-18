@Library('github.com/releaseworks/jenkinslib') _

pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('AWS test Stage') {
      steps {
        sh('aws s3 ls')
        sh('aws ec2 describe instances')
      }
    }
  }
}
