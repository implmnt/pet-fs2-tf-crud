pipeline {
  agent {
    docker {
      image 'hseeberger/scala-sbt:11.0.6_1.3.9_2.13.1'
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