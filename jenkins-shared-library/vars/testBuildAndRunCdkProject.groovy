import org.chs.AwsCli
import org.chs.Git
import org.chs.CdkCli
import org.chs.NpmCli


def call(String repository, String reference, String awsRegion = AwsCli.REGION) {
  stage("Git clone repository ${repository}"){
    gitCli.checkout(cdkRepository, cdkReference)
  }

  stage('Install CDK project dependencies') {
    println(npmCli.install())
  }

  stage('Launch CDK tests') {
    println(npmCli.test())
  }

  stage('Build CDK project') {
    println(npmCli.build())
  }

  stage('Cdk synth') {
    println('Cdk template to deploy')
    println(cdkCli.synth())
  }

  stage('Cdk deploy stacks') {
    println(cdkCli.deploy([:], awsRegion))
  }
}



