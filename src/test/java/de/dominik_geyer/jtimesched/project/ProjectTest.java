package de.dominik_geyer.jtimesched.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    Project testProject;

    @BeforeEach
    public void setUp() {
        testProject = new Project("test");
    }

    @Test
    void isRunningTrue() throws ProjectException {
        // when
        testProject.start();
        boolean isRunningAfterStart = testProject.isRunning();
        // then
        assertTrue(isRunningAfterStart);
    }

    @Test
    void isRunningFalse() {
        // when
        boolean isRunningNotStarted = testProject.isRunning();
        // then
        assertFalse(isRunningNotStarted);
    }

    @Test
    void toggleStarted() throws ProjectException {
        // given
        testProject.start();
        boolean isRunningBeforeToggle = testProject.isRunning();
        assertTrue(isRunningBeforeToggle);
        // when
        testProject.toggle();
        boolean isRunningAfterToggle = testProject.isRunning();
        // then
        assertFalse(isRunningAfterToggle);
    }

    @Test
    void toggleNotStarted() throws ProjectException {
        // given
        boolean isRunningBeforeToggle = testProject.isRunning();
        assertFalse(isRunningBeforeToggle);
        // when
        testProject.toggle();
        boolean isRunningAfterToggle = testProject.isRunning();
        // then
        assertTrue(isRunningAfterToggle);
    }
}