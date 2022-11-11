package de.dominik_geyer.jtimesched.project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectExceptionTest {

    @Test
    void ProjectException() {
        ProjectException exception = new ProjectException("Test");
        assertEquals("Test", exception.getMessage());
    }
}
