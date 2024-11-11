package com.jake.jenkinscicdproject;

import org.springframework.stereotype.Service;

@Service
public class JenkinsServices {
    public int sum(int a, int b) {
        return a + b;
    }

    public int minus(int a, int b) {
        return a - b;
    }
}
