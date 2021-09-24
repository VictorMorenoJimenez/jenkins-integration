@Library ('chs-basic-shared-library') _
import org.chs.AwsCli
import org.chs.Git
import org.chs.CdkCli
import org.chs.NpmCli

def awsCli = new AwsCli(this)
def gitCli = new Git(this)
def cdkCli = new CdkCli(this)
def npmCli = new NpmCli(this)
def cdkContext = [
  ipToAllow: '145.1.253.134',
  securityGroupId: 'sg-0686e4c67f9a948ce'
]

pipeline {
  agent {
    label 'ecs'
  }

  stages {
    stage('Clone repository') {
      steps{
        gitCli.checkout(cdkRepository, cdkReference)
      }
    }

    stage('Install CDK project dependencies') {
      steps {
        println(npmCli.install())
      }
    }

    stage('Launch CDK tests') {
      steps {
        println(npmCli.test())
      }
    }

    stage('Build CDK project') {
      steps {
        println(npmCli.build())
      }
    }

    stage('Cdk synth') {
      steps {
        println(cdkCli.synth())
        println('Cdk template to deploy')
      }
    }

    stage('Cdk deploy stacks') {
      steps {
        script {
          println(cdkCli.deploy(cdkContext))
        }
      }
    }
  }
}

