package com.jake.jenkinscicdproject;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class JenkinsServices {
    // 캐싱 기능
    //@Cacheable("sum")
    public int sum(int a, int b) {
        return a + b;
    }

    public int minus(int a, int b) {
        return a - b;
    }
}
