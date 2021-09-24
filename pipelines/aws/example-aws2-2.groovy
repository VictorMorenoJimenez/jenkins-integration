@Library ('chs-basic-shared-library') _
import org.chs.AwsCli
import org.chs.Git

pipeline {
  agent {
    label 'ecs'
  }

  stages {
    stage('AWS shared library') {
      steps {
        String repository = 'https://github.com/VictorMorenoJimenez/jenkins-code-example.git'
        String reference = 'main'
        def awsCli = new AwsCli(this)
        def gitCli = new Git(this)
        gitCli.checkout(repository, reference)
        String commandOutput = awsCli.executeCommand(['s3', 'ls'])
        println(commandOutput)
      }
    }
  }
}

