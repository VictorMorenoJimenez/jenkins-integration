package org.chs
import org.chs.Constants

class NpmCli implements Serializable {
  private final def pipelineScript

  NpmCli(def pipelineScript) {
    this.pipelineScript = pipelineScript
  }

  def install(String registry = 'https://registry.npmjs.org/') {
    String configRegistryOutput = sh(script:"npm config set registry ${registry}", returnStdout: true)
    String npmInstallOutput = sh(script: 'npm install', returnStdout: true)
    String output = configRegistryOutput + '\n' + npmInstallOutput
    return output
  }

  def synth(Map context = [:], String region = AwsCli.REGION) {
    String command = 'npx cdk synth'
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