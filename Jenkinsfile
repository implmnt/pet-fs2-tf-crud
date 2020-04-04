pipeline {
  agent {
    docker {
      image 'aa8y/sbt:latest'
    }

  }
  stages {
    stage('build') {
      steps {
        sh 'sbt compile test'
      }
    }

  }
}