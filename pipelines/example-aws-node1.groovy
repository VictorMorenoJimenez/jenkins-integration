@Library ('chs-basic-shared-library') _
import org.chs.AwsCli
import org.chs.Git


node('ecs-node'){
  String cdkRepository = params.CDK_REPOSITORY
  String reference = params.CDK_REFERENCE
  def awsCli = new AwsCli(this)
  def gitCli = new Git(this)

  stage('AWS shared library') {
    gitCli.checkout(repository, reference)
    sh(command: 'ls -larth', returnStdout: true)
  }
}
