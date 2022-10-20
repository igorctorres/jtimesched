package de.dominik_geyer.jtimesched.project;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTableModelTest {

    @ParameterizedTest
    @CsvSource({
            "0,        false",
            "1,        true",
            "4,        true",
            "5,        false",
            "6,        false",
            "7,        false",
    })
    void isCellEditableWhenProjectIsRunning(Integer column, Boolean expected) throws ProjectException {
        Project project = new Project();
        project.start();
        assertTrue(project.isRunning());
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        ProjectTableModel projectTableModel = new ProjectTableModel(projects);

        Boolean isEditable = projectTableModel.isCellEditable(0, column);

        assertEquals(isEditable, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0,        false",
            "1,        true",
            "4,        true",
            "5,        true",
            "6,        true",
            "7,        false",
    })
    void isCellEditableWhenProjectIsNotRunning(Integer column, Boolean expected) {
        Project project = new Project();
        assertFalse(project.isRunning());
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        ProjectTableModel projectTableModel = new ProjectTableModel(projects);

        Boolean isEditable = projectTableModel.isCellEditable(0, column);

        assertEquals(isEditable, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "1",
            "4",
            "5",
            "6",
            "7",
    })
    void isCellEditableShouldThrowException(Integer column) {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            projectTableModel.isCellEditable(0, column);
        });
    }
}