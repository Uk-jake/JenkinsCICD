pipeline {
    agent any // Jenkins가 가용한 모든 에이전트에서 실행되도록 설정
    stages {
        stage("checkout") { // 소스 코드 Checkout 단계
            steps {
                sh "echo checkout" // 체크아웃 작업이 시작되었음을 출력
                // git url: "https://github.com/Uk-jake/JenkinsCICD.git", branch: "master"
            }
        }

        stage("Permission") { // Gradle Wrapper에 실행 권한을 부여하는 단계
            steps {
                sh "chmod +x ./gradlew" // gradlew 파일에 실행 권한 추가
            }
        }

        stage("Compile") { // Java 코드를 컴파일하는 단계
            steps {
                sh "./gradlew compileJava" // Gradle을 사용해 Java 코드를 컴파일
            }
        }

        stage("Unit Test"){
            steps{
                sh "./gradlew test"
            }
        }

        stage("Code Coverage"){
            steps{
                sh "./gradlew jacocoTestCoverageVerification"
                sh "./gradlew jacocoTestReport"
                publishHTML(target: [
                reportDir: 'build/reports/jacoco/test/html',
                reportFiles: 'index.html',
                reportName: 'Jacoco Report'
                ])
            }
    }
}
