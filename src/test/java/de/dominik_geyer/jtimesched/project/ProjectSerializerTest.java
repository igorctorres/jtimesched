package de.dominik_geyer.jtimesched.project;
import org.junit.jupiter.api.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectSerializerTest {

    @Test
    void xml() throws TransformerConfigurationException, IOException, SAXException, ParserConfigurationException {
        Project project = new Project();
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        project.setTitle("Test");
        ProjectSerializer serializer = new ProjectSerializer("file-test");
        serializer.writeXml(projects);

        ArrayList<Project> readProjects = serializer.readXml();

        assertEquals(projects.toString(), readProjects.toString());
    }
}
