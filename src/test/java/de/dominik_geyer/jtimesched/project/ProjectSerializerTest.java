package de.dominik_geyer.jtimesched.project;
import org.junit.jupiter.api.*;
import org.w3c.dom.Attr;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectSerializerTest {

    @Test
    void xml() throws TransformerConfigurationException, IOException, SAXException, ParserConfigurationException {
        Project project = new Project();
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        project.setTitle("Test");
        project.setNotes("some notes");
        project.setSecondsToday(100);
        project.setSecondsOverall(200);
        project.setQuotaToday(300);
        project.setQuotaOverall(400);
        project.setColor(Color.RED);
        project.setChecked(true);
        //project.setRunning(true);
        long timeCreated = 345435342;
        Date dateCreated = new Date();
        dateCreated.setTime(timeCreated);
        project.setTimeCreated(dateCreated);
        long timeStarted = 456456456;
        Date dateStarted = new Date();
        dateStarted.setTime(timeStarted);
        project.setTimeStart(dateStarted);
        ProjectSerializer serializer = new ProjectSerializer("file-test");
        serializer.writeXml(projects);

        ArrayList<Project> readProjects = serializer.readXml();

        assertEquals(projects.toString(), readProjects.toString());
    }

    @Test
    void xmlNoTitle() throws TransformerConfigurationException, IOException, SAXException, ParserConfigurationException {
        Project project = new Project();
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        project.setTitle("");
        ProjectSerializer serializer = new ProjectSerializer("file-test");
        serializer.writeXml(projects);

        ArrayList<Project> readProjects = serializer.readXml();

        assertEquals(projects.toString(), readProjects.toString());
    }

    @Test
    void xmlTitleNull() throws TransformerConfigurationException, IOException, SAXException, ParserConfigurationException {
        Project project = new Project();
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        project.setTitle(null);
        ProjectSerializer serializer = new ProjectSerializer("file-test");
        serializer.writeXml(projects);

        ArrayList<Project> readProjects = serializer.readXml();
        String projectString = "[Project [title=, running=no, secondsOverall=0, secondsToday=0, checked=no]]";

        assertEquals(projectString, readProjects.toString());
    }

    @Test
    void xmlColorNull() throws TransformerConfigurationException, IOException, SAXException, ParserConfigurationException {
        Project project = new Project();
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        project.setColor(null);
        ProjectSerializer serializer = new ProjectSerializer("file-test");
        serializer.writeXml(projects);

        ArrayList<Project> readProjects = serializer.readXml();

        assertEquals(projects.toString(), readProjects.toString());
    }

}
