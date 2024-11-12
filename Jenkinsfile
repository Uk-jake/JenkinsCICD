pipeline {
    agent any // Jenkins가 가용한 모든 에이전트에서 실행되도록 설정
     environment {
            imagename = "ukjang/springcalculation"
            registryCredential = 'docker-hub'
            ubuntuIp = '13.209.76.15'
            dockerImage = ''
        }
    stages {
        stage("checkout") { // 소스 코드 Checkout 단계
            steps {
                sh "echo checkout" // 체크아웃 작업이 시작되었음을 출력
                // git url: "https://github.com/Uk-jake/JenkinsCICD.git", branch: "master" // Git 리포지토리에서 소스 코드를 가져오는 명령어 (주석 처리됨)
            }
        }

        stage("Set Variables"){
           steps{
               echo "SetVariables"
               script{
                   DOCKER_HUB_URL = 'registry.hub.docker.com'
                   DOCKER_HUB_FULL_URL = 'https://' + DOCKER_HUB_URL
                   DOCKER_HUB_CREDENTIAL_ID = 'docker-hub'
               }
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

        stage("Static Code Analysis") { // 정적 코드 분석을 실행하는 단계
            steps {
                sh "./gradlew checkstyleMain" // Checkstyle을 사용해 코드 분석
                publishHTML(target: [ // HTML 형식의 Checkstyle 리포트를 Jenkins에 게시
                    reportDir: 'build/reports/checkstyle/', // 리포트 디렉터리 경로
                    reportFiles: 'main.html', // 리포트 파일 이름
                    reportName: 'Checkstyle Report' // 리포트의 이름 설정
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
                ]) // 닫는 대괄호를 주석 처리하지 않음
            }
        }

        stage("Gradle Build") { // Gradle 빌드 단계
            steps {
                sh "./gradlew clean build" // Gradle을 사용해 프로젝트를 클린 빌드
            }
        }

        stage("Docker Image Build") { // Docker 이미지를 빌드하는 단계
            steps {
                sh "docker build -t ${imagename} ." // Docker 이미지를 빌드하고 태그를 imagename 설정
            }
        }

        stage("Push Docker Image") { // Docker Hub에 이미지를 푸시하는 단계
                    steps {
                        script {
                            // Docker Hub에 로그인하고 이미지 푸시
                            docker.withRegistry('', registryCredential) {
                                sh "docker push ${imagename}" // Docker Hub에 이미지 푸시
                            }
                        }
                    }
                }

        stage('SSH-Server-EC2'){
                    steps {
                        sshagent(credentials: ['TestImage_ssh']) {
                            sh 'ssh -o StrictHostKeyChecking=no ubuntu@${ubuntuIp} "whoami"'
                            sh 'ssh -o StrictHostKeyChecking=no ubuntu@${ubuntuIp} "docker pull ${imagename}"'
                            sh 'ssh -o StrictHostKeyChecking=no ubuntu@${ubuntuIp} "docker run ${imagename}'
                        }
                    }
                }

        // 1. docker-hub에서 image 다운
        // 2. Public Cloud에 docker image 실행 -> docker 사용하던 k8s

    }
}
