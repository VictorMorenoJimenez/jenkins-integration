@Library ('chs-basic-shared-library') _
import org.chs.AwsCli
import org.chs.Git

pipeline {
  agent {
    label 'ecs'
  }

  String repository = 'https://github.com/VictorMorenoJimenez/jenkins-code-example.git'
  String reference = 'main'
  def awsCli = new AwsCli(this)
  def gitCli = new Git(this)

  stages {
    stage('AWS shared library') {
      gitCli.checkout(repository, reference)
      String commandOutput = awsCli.executeCommand(['s3', 'ls'])
      println(commandOutput)
    }
  }
}

