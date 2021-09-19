@Library ('chs-basic-shared-library') _
import org.chs.AWS

pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('AWS test Stage') {
      steps {
        def awsCli = new AWS(this)
        awsCli.executeCommand(['s3', 'ls'])
      }
    }
  }
}
