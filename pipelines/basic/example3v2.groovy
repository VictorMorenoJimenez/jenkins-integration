node('ecs'){
  try {
    ansiColor('xterm'){
      stage('Create directory') {
        sh(script: 'mkdir -p output')
      }

      stage('Create new file') {
        writeFile file: "output/file.txt", text: "This file is my useful, we need to save it."
        writeFile file: "output/useless-file.md", text: "This file is my useless, discard it."
      }

      stage('Archive my new file') {
        archiveArtifacts artifacts: 'output/*.txt', excludes: 'output/*.md'
      }
    }
  } finally {
    cleanWs(cleanWhenFailure: false, cleanWhenUnstable: false)
  }
}


