pipeline {
    agent any // Jenkins가 가용한 모든 에이전트에서 실행되도록 설정
    stages {
        stage("checkout") { // 소스 코드 Checkout 단계
            steps {
                sh "echo checkout" // 체크아웃 작업이 시작되었음을 출력
                // git url: "https://github.com/Uk-jake/JenkinsCICD.git", branch: "master" // Git 리포지토리에서 소스 코드를 가져오는 명령어 (주석 처리됨)
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

        stage("Unit Test") { // 단위 테스트를 실행하는 단계
            steps {
                sh "./gradlew test" // Gradle을 사용해 테스트 실행
            }
        }

        stage("Static Code Analysis"){
                  steps{
                      sh "./gradlew checkstyleMain"
                          publishHTML(target: [
                                      reportDir: 'build/reports/checkstyle/',
                                      reportFiles: 'main.html',
                                      reportName: 'Checkstyle Report'
                          ])
                  }
                }

        stage("Code Coverage") { // 코드 커버리지를 측정하고 리포트를 생성하는 단계
            steps {
                sh "./gradlew jacocoTestCoverageVerification" // Jacoco를 사용해 코드 커버리지 검증
                sh "./gradlew jacocoTestReport" // Jacoco를 사용해 코드 커버리지 리포트 생성
                publishHTML(target: [ // HTML 형식의 코드 커버리지 리포트를 Jenkins에 게시
                    reportDir: 'build/reports/jacoco/test/html', // 리포트 디렉터리 경로
                    reportFiles: 'index.html', // 리포트 파일 이름
                    reportName: 'Jacoco Report' // 리포트의 이름 설정
                ])
            }
        }



    }
}
