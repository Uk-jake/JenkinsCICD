#!/bin/bash

# 인수 테스트 스크립트

BASE_URL="http://localhost:8000"  # Jenkins 컨테이너의 매핑된 포트를 사용합니다.
INDEX_URL="${BASE_URL}/"
CALC_URL="${BASE_URL}/cal?a=5&b=3"

test_passed=true

echo "Starting Acceptance Tests..."

# Index 테스트
index_response=$(curl -s -o /dev/null -w "%{http_code}" "${INDEX_URL}")
if [ "$index_response" -eq 200 ]; then
    echo "Index Test Passed: 200 OK"
else
    echo "Index Test Failed: Expected 200 OK, got ${index_response}"
    test_passed=false
fi

# Calculate API 테스트
calc_response=$(curl -s "${CALC_URL}")
expected_response="Sum: 8, Minus: 2"
if [ "$calc_response" == "$expected_response" ]; then
    echo "Calculate Test Passed: ${expected_response}"
else
    echo "Calculate Test Failed: Expected '${expected_response}', got '${calc_response}'"
    test_passed=false
fi

# 결과 출력
if [ "$test_passed" = true ]; then
    echo "All Acceptance Tests Passed!"
    exit 0
else
    echo "Some Acceptance Tests Failed."
    exit 1
fi
