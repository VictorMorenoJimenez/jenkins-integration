package org.chs
import org.chs.Constants

class AWS implements Serializable {
  
  static final String BINARY = 'aws'
  static final String REGION = 'us-east-2'
  static final int AWS_CALL_RETRIES = 3
  static final int AWS_CALL_RETRIES_WAIT = 5

  private final def pipelineScript

  AWS(def pipelineScript) {
    this.pipelineScript = pipelineScript
  }

  boolean finished = false
  def executeCommand(List params, String region = AWS.REGION) {
    String ACCESS_KEY_SECRET_ID = this.pipelineScript.credentials(Constants.JENKINS_AWS_CREDENTIALS_ACCESS_KEY_ID_ID)
    String SECRET_KEY_SECRET_ID = this.pipelineScript.credentials(Constants.JENKINS_AWS_SECRET_ACCESS_KEY_ID)
    String command = "AWS_ACCESS_KEY_ID=${ACCESS_KEY_SECRET_ID} AWS_SECRET_ACCESS_KEY=${SECRET_KEY_SECRET_ID} ${AWS.BINARY} --region ${region} "
    String output = ''
    boolean retry = false

    // Include params to AWSCLI call.
    params.each { p ->
      command += p + ' '
    }

    this.pipelineScript.sh(script: "echo ${command}")

    println('Printing command ' + command)

    this.pipelineScript.retry(AWS_CALL_RETRIES){
      if(retry) {
        this.pipelineScript.sleep(AWS_CALL_RETRIES_WAIT)
      } else {
        retry = true
      }

      String command_output = this.pipelineScript.sh(script: command, returnStdout: true)
      output = this.pipelineScript.readJSON(text: command_output)
    }

    return output
  }
}