@Library ('chs-basic-shared-library') _
import org.chs.AWS


node('ecs'){
  def awsCli = new AWS(this)
  stages {
    stage('AWS test Stage') {
      steps {
        awsCli.executeCommand(['s3', 'ls'])
      }
    }
  }
}
