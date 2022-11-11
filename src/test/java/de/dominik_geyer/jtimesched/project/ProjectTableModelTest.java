package de.dominik_geyer.jtimesched.project;

import de.dominik_geyer.jtimesched.JTimeSchedApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTableModelTest {

    @BeforeEach
    public void setUp() {
        JTimeSchedApp.initLogger();
    }

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

    @Test
    void valueAtColumnCheck() {
        Project project = new Project();
        project.setChecked(false);
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        projectTableModel.addProject(project);
        projectTableModel.setValueAt(true, 0, ProjectTableModel.COLUMN_CHECK);
        assertEquals(true, projectTableModel.getValueAt(0, ProjectTableModel.COLUMN_CHECK));
    }

    @Test
    void valueAtColumnTitle() {
        Project project = new Project("Test");
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        projectTableModel.addProject(project);
        projectTableModel.setValueAt("Edited", 0, ProjectTableModel.COLUMN_TITLE);
        assertEquals("Edited", projectTableModel.getValueAt(0, ProjectTableModel.COLUMN_TITLE));
    }

    @Test
    void valueAtColumnColor() {
        Project project = new Project();
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        projectTableModel.addProject(project);
        Color color = new Color(0,0,0);
        projectTableModel.setValueAt(color, 0, ProjectTableModel.COLUMN_COLOR);
        assertEquals(color, projectTableModel.getValueAt(0, ProjectTableModel.COLUMN_COLOR));
    }

    @Test
    void valueAtColumnCreated() {
        Project project = new Project();
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        projectTableModel.addProject(project);
        Date date = new Date();
        projectTableModel.setValueAt(date, 0, ProjectTableModel.COLUMN_CREATED);
        assertEquals(date, projectTableModel.getValueAt(0, ProjectTableModel.COLUMN_CREATED));
    }

    @Test
    void valueAtColumnTimeOverall() {
        Project project = new Project();
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        projectTableModel.addProject(project);
        projectTableModel.setValueAt(100, 0, ProjectTableModel.COLUMN_TIMEOVERALL);
        assertEquals(100, projectTableModel.getValueAt(0, ProjectTableModel.COLUMN_TIMEOVERALL));
    }

    @Test
    void valueAtColumnTimeToday() {
        Project project = new Project();
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        projectTableModel.addProject(project);
        projectTableModel.setValueAt(100, 0, ProjectTableModel.COLUMN_TIMETODAY);
        assertEquals(100, projectTableModel.getValueAt(0, ProjectTableModel.COLUMN_TIMETODAY));
    }

    @Test
    void removeProject() {
        Project project = new Project();
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        projectTableModel.addProject(project);
        assertEquals(1, projectTableModel.getRowCount());
        projectTableModel.removeProject(0);
        assertEquals(0, projectTableModel.getRowCount());
    }

    @Test
    void columnCount() {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(8, projectTableModel.getColumnCount());
    }

    @ParameterizedTest
    @CsvSource({
            "0, ",
            "1, ",
            "2, Title",
            "3, ",
            "4, Created",
            "5, Time Overall",
            "6, Time Today",
            "7, ",
    })
    void columnName(Integer col, String expected) {
        if (expected == null) {
            expected = "";
        }
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(expected, projectTableModel.getColumnName(col));
    }

    @Test
    void columnClassColor() {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(Color.class, projectTableModel.getColumnClass(ProjectTableModel.COLUMN_COLOR));
    }

    @Test
    void columnClassCreated() {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(Date.class, projectTableModel.getColumnClass(ProjectTableModel.COLUMN_CREATED));
    }

    @Test
    void columnClassTimeOverall() {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(Integer.class, projectTableModel.getColumnClass(ProjectTableModel.COLUMN_TIMEOVERALL));
    }

    @Test
    void columnClassTimeToday() {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(Integer.class, projectTableModel.getColumnClass(ProjectTableModel.COLUMN_TIMETODAY));
    }

    @Test
    void columnClassCheck() {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(Boolean.class, projectTableModel.getColumnClass(ProjectTableModel.COLUMN_CHECK));
    }

    @Test
    void columnClassActionDelete() {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(Boolean.class, projectTableModel.getColumnClass(ProjectTableModel.COLUMN_ACTION_DELETE));
    }

    @Test
    void columnClassActionStartPause() {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(Boolean.class, projectTableModel.getColumnClass(ProjectTableModel.COLUMN_ACTION_STARTPAUSE));
    }

    @Test
    void columnClassOther() {
        ProjectTableModel projectTableModel = new ProjectTableModel(new ArrayList<>());
        assertEquals(String.class, projectTableModel.getColumnClass(10));
    }
}