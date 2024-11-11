package com.jake.jenkinscicdproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JenkinsCicdProjectTest {

    private JenkinsServices service = new JenkinsServices();

    @Test
    public void testService(){
        assertEquals(10, service.sum(4,6));
        assertEquals(9, service.sum(4,5));
        assertEquals(-1, service.minus(5,6));
        assertEquals(0, service.minus(5,5));

    }
}
