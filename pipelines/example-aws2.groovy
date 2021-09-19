@Library ('chs-basic-shared-library') _
import org.chs.AWS

pipeline {
  agent {
    label 'ecs'
  }
  stages {
    stage('AWS test Stage') {
      def awsCli = new AWS(this)
      steps {
        awsCli.executeCommand(['s3', 'ls'])
      }
    }
  }
}
