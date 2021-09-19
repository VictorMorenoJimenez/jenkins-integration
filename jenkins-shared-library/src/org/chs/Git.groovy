package org.chs

class Git implements Serializable {
  private final def pipelineScript

  Git(def pipelineScript) {
    this.pipelineScript = pipelineScript
  }

  def checkout(String repository, String reference) {
    this.pipelineScript.git branch: reference, url: repository
  }
}