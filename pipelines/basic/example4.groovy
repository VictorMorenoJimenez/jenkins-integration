node('ecs'){
  try {
    Map stagesDir = [:]
    stagesDir['createDir1'] = {
      stage('Create directory 1') {
        sh(script: 'mkdir -p output')
      }      
    }

    stagesDir['createDir2'] = {
      stage('Create directory 2') {
        sh(script: 'mkdir -p output2')
      }
    }

    stagesDir['createDir3'] = {
      stage('Create directory 3') {
        sh(script: 'mkdir -p output3')
      }
    }

    Map stagesFiles = [:]

    stagesFiles['newFile1'] = {
      stage('Create new file 1') {
        writeFile file: "output1/file.txt", text: "This file is my useful, we need to save it."
        writeFile file: "output1/useless-file.md", text: "This file is my useless, discard it."
      }
    }

    stagesFiles['newFile2'] = {
      stage('Create new file 2') {
        writeFile file: "output2/file.txt", text: "This file is my useful, we need to save it."
        writeFile file: "output2/useless-file.md", text: "This file is my useless, discard it."
      }
    }

    stagesFiles['newFile3'] = {
      stage('Create new file 3') {
        writeFile file: "output3/file.txt", text: "This file is my useful, we need to save it."
        writeFile file: "output3/useless-file.md", text: "This file is my useless, discard it."
      }
    }
    
    parallel { stagesDir }
    parallel { stagesFiles }

    stage('Archive my new file') {
      archiveArtifacts artifacts: 'output*/*.txt', excludes: 'output/*.md'
    }

  } finally {
    cleanWs(cleanWhenFailure: false, cleanWhenUnstable: false)
  }
}


