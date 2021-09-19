@Library ('chs-basic-shared-library') _
import org.chs.AwsCli
import org.chs.Git


node('ecs-node'){
  String cdkRepository = params.CDK_REPOSITORY
  String cdkReference = params.CDK_REFERENCE
  def awsCli = new AwsCli(this)
  def gitCli = new Git(this)

  stage('Clone repository') {
    gitCli.checkout(cdkRepository, cdkReference)
  }

  stage('Install CDK project dependencies') {
    String npmOutput = sh(script:'npm install', returnStdout: true)
    println(npmOutput)
  }

  stage('Launch CDK tests') {
    String cdkTestOutput = sh(script:'npm run test', returnStdout: true)
    println(cdkTestOutput)
  }

  stage('Build CDK project') {
    String npmBuildOutput = sh(script:'npm run build', returnStdout: true)
    println(npmBuildOutput)
  }

  stage('Cdk synth') {
    String cdkTemplate = sh(script:'npx cdk synth', returnStdout: true)
    println('Cdk template to deploy')
    println(cdkTemplate)
  }
}
