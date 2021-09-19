@Library ('chs-basic-shared-library') _
import org.chs.AwsCli
import org.chs.Git


node('ecs-node'){
  String cdkRepository = params.CDK_REPOSITORY
  String cdkReference = params.CDK_REFERENCE
  def awsCli = new AwsCli(this)
  def gitCli = new Git(this)

  stage('AWS shared library') {
    gitCli.checkout(cdkRepository, cdkReference)
    sh(script: 'ls -larth', returnStdout: true)
  }
}
