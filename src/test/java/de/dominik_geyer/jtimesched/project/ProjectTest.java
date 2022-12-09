package de.dominik_geyer.jtimesched.project;

import org.junit.jupiter.api.*;

import java.awt.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    Project testProject;

    @BeforeEach
    public void setUp() {
        testProject = new Project("test");
    }

    @Test
    void startWhenRunning() throws ProjectException {
        testProject.setRunning(true);
        assertThrows(ProjectException.class, () -> {
            testProject.start();
        });
    }

    @Test
    void pauseWhenNotRunning() throws ProjectException {
        testProject.setRunning(false);
        assertThrows(ProjectException.class, () -> {
            testProject.pause();
        });
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
    void toggleNotStarted() {
        // given
        boolean isRunningBeforeToggle = testProject.isRunning();
        assertFalse(isRunningBeforeToggle);
        // when
        testProject.toggle();
        boolean isRunningAfterToggle = testProject.isRunning();
        // then
        assertTrue(isRunningAfterToggle);
    }

    @Test
    void title() {
        testProject.setTitle("Test title");
        assertEquals("Test title", testProject.getTitle());
    }

    @Test
    void notes() {
        testProject.setNotes("Test note");
        assertEquals("Test note", testProject.getNotes());
    }

    @Test
    void timeCreated() {
        Date time = new Date();
        testProject.setTimeCreated(time);
        assertEquals(time, testProject.getTimeCreated());
    }

    @Test
    void timeStart() {
        Date time = new Date();
        testProject.setTimeStart(time);
        assertEquals(time, testProject.getTimeStart());
    }

    @Test
    void color() {
        Color color = new Color(0,0,0);
        testProject.setColor(color);
        assertEquals(color, testProject.getColor());
    }

    @Test
    void checkedTrue() {
        testProject.setChecked(true);
        assertTrue(testProject.isChecked());
    }

    @Test
    void checkedFalse() {
        testProject.setChecked(false);
        assertFalse(testProject.isChecked());
    }

    @Test
    void running() {
        testProject.setRunning(true);
        assertTrue(testProject.isRunning());
    }

    @Test
    void secondsToday() {
        testProject.setSecondsToday(100);
        assertEquals(100, testProject.getSecondsToday());
    }

    @Test
    void secondsTodayNegative() {
        testProject.setSecondsToday(-1);
        assertEquals(0, testProject.getSecondsToday());
    }

    @Test
    void secondsTodayOne() {
        testProject.setSecondsToday(1);
        assertEquals(1, testProject.getSecondsToday());
    }

    @Test
    void secondsOverall() {
        testProject.setSecondsOverall(100);
        assertEquals(100, testProject.getSecondsOverall());
    }

    @Test
    void secondsOverallNegative() {
        testProject.setSecondsOverall(-1);
        assertEquals(0, testProject.getSecondsOverall());
    }

    @Test
    void secondsOverallOne() {
        testProject.setSecondsOverall(1);
        assertEquals(1, testProject.getSecondsOverall());
    }

    @Test
    void adjustSecondsToday() {
        testProject.setSecondsOverall(100);
        testProject.setSecondsToday(50);
        testProject.adjustSecondsToday(40);
        assertEquals(90, testProject.getSecondsOverall());
        assertEquals(40, testProject.getSecondsToday());
    }

    @Test
    void adjustSecondsTodayNegative() {
        testProject.setSecondsOverall(100);
        testProject.setSecondsToday(50);
        testProject.adjustSecondsToday(-1);
        assertEquals(50, testProject.getSecondsOverall());
        assertEquals(0, testProject.getSecondsToday());
    }

    @Test
    void adjustSecondsTodayOne() {
        testProject.setSecondsOverall(100);
        testProject.setSecondsToday(50);
        testProject.adjustSecondsToday(1);
        assertEquals(51, testProject.getSecondsOverall());
        assertEquals(1, testProject.getSecondsToday());
    }

    @Test
    void resetToday() {
        testProject.setSecondsOverall(100);
        testProject.setSecondsToday(50);
        testProject.setQuotaToday(60);
        testProject.resetToday();
        assertEquals(0, testProject.getQuotaToday());
        assertEquals(0, testProject.getSecondsToday());
    }

    @Test
    void quotaToday() {
        testProject.setQuotaToday(100);
        assertEquals(100, testProject.getQuotaToday());
    }

    @Test
    void quotaOverall() {
        testProject.setQuotaOverall(100);
        assertEquals(100, testProject.getQuotaOverall());
    }

    @Test
    void string() {
        testProject.setRunning(true);
        testProject.setSecondsOverall(100);
        testProject.setSecondsToday(50);
        testProject.setChecked(true);
        assertEquals("Project [title=test, running=yes, secondsOverall=100, secondsToday=50, checked=yes]", testProject.toString());
    }
}