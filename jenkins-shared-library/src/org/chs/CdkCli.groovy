package org.chs
import org.chs.Constants

class CdkCli implements Serializable {
  static final String REGION = 'us-east-2'

  private final def pipelineScript

  CdkCli(def pipelineScript) {
    this.pipelineScript = pipelineScript
  }

  def deploy(Map context = [:], String region = AwsCli.REGION) {
    String command = "npx cdk --region ${region} deploy"
    String contextArgument=''
    context.each { entry -> 
      contextArgument += ' --context '
      contextArgument += "${entry.key}=${entry.value}"
    }
    command += contextArgument

    command += ' --require-approval never'

    return command

    //return this.executeCommand(command)
  }

  def destroy(Map context = [:], String region = AwsCli.REGION) {
    String command = "npx cdk --region ${region} destroy --force"
    String contextArgument=''
    context.each { entry -> 
      contextArgument += ' --context '
      contextArgument += "${entry.key}=${entry.value}"
    }
    command += contextArgument

    return this.executeCommand(command)
  }

  def synth(Map context = [:], String region = AwsCli.REGION) {
    String command = 'npx cdk synth'

    String contextArgument=''
    context.each { entry -> 
      contextArgument += ' --context '
      contextArgument += "${entry.key}=${entry.value}"
    }
    command += contextArgument

    return this.executeCommand(command)
  }

  void executeCommand(String command){
    String command_output = ''
    this.pipelineScript.withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws-integration-ohio-key', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
      command_output = this.pipelineScript.sh(script: command, returnStdout: true)
    }
    return command_output    
  }
}