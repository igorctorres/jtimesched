package de.dominik_geyer.jtimesched.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTimeTest {

    @Test
    void formatSecondsWithNegative() {
        assertThrows(ProjectException.class, () -> {
            ProjectTime.formatSeconds(-1);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "0,         0:00:00",
            "1,         0:00:01",
            "60,        0:01:00",
            "3600,      1:00:00",
            "5025,      1:23:45"
    })
    void formatSecondsWithPositive(int secs, String expected) {
        String formatted = ProjectTime.formatSeconds(secs);
        assertEquals(expected, formatted);
    }

    @Test
    void parseSecondsWithInvalidString() {
        assertThrows(ParseException.class, () -> {
            ProjectTime.parseSeconds("xyz");
        });
    }

    @Test
    void parseSecondsWithNegative() {
        assertThrows(ParseException.class, () -> {
            ProjectTime.parseSeconds("-1:00:00");
        });
    }

    @ParameterizedTest
    @CsvSource({
            "0:00:00, 0",
            "0:00:01, 1",
            "0:01:00, 60",
            "1:00:00, 3600",
            "1:23:45, 5025"
    })
    void parseSecondsWithPositive(String timeString, int expected) throws ParseException {
        int parsed = ProjectTime.parseSeconds(timeString);
        assertEquals(expected, parsed);
    }

    @Test
    void formatDate() {
        // given
        Date d = new Date(122, Calendar.OCTOBER, 7); // first int is the number of years since 1900
        // when
        String formatted = ProjectTime.formatDate(d);
        // then
        assertEquals("2022-10-07", formatted);
    }
    @Test
    void formatDateNull() {
        assertThrows(NullPointerException.class, () -> {
            ProjectTime.formatDate(null);
        });
    }

    @Test
    void parseDate() throws ParseException {
        // given
        String dateString = "2022-10-07";
        // when
        Date parsed = ProjectTime.parseDate(dateString);
        // then
        Date expected = new Date(122, 9, 7); // first int is the number of years since 1970, second is the enum of the month
        assertEquals(expected, parsed);
    }

    @Test
    void parseDateInvalidString() {
        assertThrows(ParseException.class, () -> {
            ProjectTime.parseDate("xyz");
        });
    }
}