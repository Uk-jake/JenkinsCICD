#!/bin/bash

# 테스트할 URL
URL="http://localhost:8000/cal?a=5&b=3"

# 예상되는 결과
expected_result=Sum: 8, Minus: 2

# curl을 사용하여 요청하고, 결과를 변수에 저장
response=$(curl -s "$URL")

# 응답 결과와 예상 결과를 비교
if test "$response" -eq "$expected_result"; then
    echo "Test Passed: Expected $expected_result, got $response"
    exit 0
else
    echo "Test Failed: Expected $expected_result, but got $response"
    exit 1
fi
