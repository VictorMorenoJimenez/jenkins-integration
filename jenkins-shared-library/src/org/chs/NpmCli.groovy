package org.chs
import org.chs.Constants

class NpmCli implements Serializable {
  private final def pipelineScript

  NpmCli(def pipelineScript) {
    this.pipelineScript = pipelineScript
  }

  def install(String registry = 'https://registry.npmjs.org/') {
    String configRegistryOutput = this.pipelineScript.sh(script:"npm config set registry ${registry}", returnStdout: true)
    String npmInstallOutput = this.pipelineScript.sh(script: 'npm install', returnStdout: true)
    return configRegistryOutput + '\n' + npmInstallOutput
  }

  def test() {
    return this.pipelineScript.sh(script: 'npm run test', returnStdout: true)
  }

  def build() {
    return this.pipelineScript.sh(script: 'npm run build', returnStdout: true)
  }
}