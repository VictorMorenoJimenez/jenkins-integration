pipeline {
  agent {
    label 'ecs'
  }
  environment {
    AWS_ACCESS_KEY_ID     = credentials('aws-key-id-ohio')
    AWS_SECRET_ACCESS_KEY = credentials('aws-secret-key-ohio')
  }
  stages {
    stage('AWS test Stage') {
      steps {
        sh('aws s3 ls')
        sh('aws ec2 describe-instances')
      }
    }
  }
}
