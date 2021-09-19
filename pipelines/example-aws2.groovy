@Library ('chs-basic-shared-library') _
import org.chs.AwsCli
import org.chs.Git


node('ecs'){
  String repository = 'https://github.com/VictorMorenoJimenez/jenkins-code-example.git'
  String reference = 'main'
  def awsCli = new AwsCli(this)
  def gitCli = new Git(this)

  stage('AWS test Stage') {
    //gitCli.checkout(repository, reference)
    awsCli.executeCommand(['s3', 'ls'])
  }
}
