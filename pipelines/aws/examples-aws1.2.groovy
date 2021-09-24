pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('AWS test Stage') {
      steps {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws-integration-ohio-key', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
          sh('aws s3 ls')
          sh('aws ec2 describe-instances')
        }
      }
    }
  }
}
