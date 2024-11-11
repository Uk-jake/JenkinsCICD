pipeline {
    agent any
    stages {
        stage("checkout") {
            steps {
                sh "echo checkout"
                // git url: "https://github.com/Uk-jake/JenkinsCICD.git", branch: "master"
            }
        }

        stage("Permission"){
            steps{
                sh "chmod +x ./gradlew"
            }

        }

        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
    }
}
