@Library ('chs-basic-shared-library') _
import org.chs.AwsCli
import org.chs.Git
import org.chs.CdkCli
import org.chs.NpmCli


node('ecs-node'){
  String cdkRepository = params.CDK_REPOSITORY
  String cdkReference = params.CDK_REFERENCE
  String ip = params.IP_TO_ALLOW
  String sgId = params.SG_ID
  def cdkContext = [
    ipToAllow: ip,
    securityGroupId: sgId
  ]
  def awsCli = new AwsCli(this)
  def gitCli = new Git(this)
  def cdkCli = new CdkCli(this)
  def npmCli = new NpmCli(this)

  stage('Clone repository') {
    gitCli.checkout(cdkRepository, cdkReference)
  }

  stage('Install CDK project dependencies') {
    println(npmCli.install())
  }

  stage('Build CDK project') {
    println(npmCli.build())
  }

  stage('Cdk synth') {
    println('Cdk template to deploy')
    println(cdkCli.synth())
  }

  stage('Cdk deploy stacks') {
    println(cdkCli.deploy(cdkContext))
  }
}
