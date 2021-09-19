@Library ('chs-basic-shared-library') _

import org.chs.Git
import org.chs.CdkCli
import org.chs.NpmCli

node('ecs-node'){
  ansiColor('xterm'){
    String cdkRepository = params.CDK_REPOSITORY
    String cdkReference = params.CDK_REFERENCE
    def gitCli = new Git(this)
    def cdkCli = new CdkCli(this)
    def npmCli = new NpmCli(this)

    stage('Clone repository') {
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

    stage('Destroy stacks') {
      println(cdkCli.destroy())
    }
  }
}

