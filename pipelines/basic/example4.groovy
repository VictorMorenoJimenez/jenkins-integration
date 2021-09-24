pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('AWS parallel Stage') {
      parallel {
        stage('Create directory 1') {
          steps {
            sh(script: 'mkdir -p output')
          }
        }
        stage('Create directory 2') {
          steps {
            sh(script: 'mkdir -p output2')
          }
        }
        stage('Create directory 3') {
          steps {
            sh(script: 'mkdir -p output3')
          }
        }    
      }
    }
    post {
      always {
        cleanWs(cleanWhenFailure: false, cleanWhenUnstable: false)
      }
    }
  }
}

