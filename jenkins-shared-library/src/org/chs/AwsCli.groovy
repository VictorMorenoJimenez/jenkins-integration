package org.chs
import org.chs.Constants

class AwsCli implements Serializable {
  static final String REGION = 'us-east-2'
  static final int AWS_CALL_RETRIES = 3
  static final int AWS_CALL_RETRIES_WAIT = 5

  private final def pipelineScript

  AwsCli(def pipelineScript) {
    this.pipelineScript = pipelineScript
  }

  def executeCommand(List params, String region = AwsCli.REGION) {
    String command = "aws --region ${region} "
    String command_output = ''
    boolean retry = false

    // Include params to AWSCLI call.
    params.each { p ->
      command += p + ' '
    }

    this.pipelineScript.retry(AwsCli.AWS_CALL_RETRIES){
      if(retry) {
        this.pipelineScript.sleep(AwsCli.AWS_CALL_RETRIES_WAIT)
      } else {
        retry = true
      }

      this.pipelineScript.withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws-integration-ohio-key', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
          command_output = this.pipelineScript.sh(script: command, returnStdout: true)
      }
    }

    return command_output
  }
}