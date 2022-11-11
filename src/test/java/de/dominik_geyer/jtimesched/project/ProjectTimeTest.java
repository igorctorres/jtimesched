package de.dominik_geyer.jtimesched.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTimeTest {

//    @Test
//    void formatSecondsWithNegative() {
//        assertThrows(ProjectException.class, () -> {
//            ProjectTime.formatSeconds(-1);
//        });
//    }

    @ParameterizedTest
    @CsvSource({
            "0,         0:00:00",
            "59,        0:00:59",
            "60,        0:01:00",
            "3599,      0:59:59",
            "3600,      1:00:00"
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

    @ParameterizedTest
    @CsvSource({
            "0:-1:-1",
            "0:-1:00",
            "0:-1:59",
            "0:-1:60",
            "0:00:-1",
            "0:59:-1",
            "0:60:-1",
            "1:-1:-1",
            "1:-1:00",
            "1:-1:59",
            "1:-1:60",
            "1:00:-1",
            "1:59:-1",
            "1:60:-1",
    })
    void parseSecondsWithNegativeValues(String timeString) {
        assertThrows(ParseException.class, () -> {
            ProjectTime.parseSeconds(timeString);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "0:00:60",
            "0:59:60",
            "0:60:00",
            "0:60:59",
            "0:60:60",
            "1:00:60",
            "1:59:60",
            "1:60:00",
            "1:60:59",
            "1:60:60",
    })
    void parseSecondsWithSecondsAndMinutesGreaterThan59(String timeString) {
        assertThrows(ParseException.class, () -> {
            ProjectTime.parseSeconds(timeString);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "0:00:00, 0",
            "0:00:59, 59",
            "0:59:00, 3540",
            "0:59:59, 3599",
            "1:00:59, 3659",
            "1:59:00, 7140",
            "1:59:59, 7199",
            "1:00:00, 3600",
    })
    void parseSecondsValidValues(String timeString, int expected) throws ParseException {
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