package de.dominik_geyer.jtimesched.project;
import org.junit.jupiter.api.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
