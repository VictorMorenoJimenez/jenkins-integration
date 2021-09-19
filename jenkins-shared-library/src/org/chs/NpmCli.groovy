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

  def test() {
    String npmTestOutput = sh(script: 'npm run test', returnStdout: true)
    return npmTestOutput
  }
}