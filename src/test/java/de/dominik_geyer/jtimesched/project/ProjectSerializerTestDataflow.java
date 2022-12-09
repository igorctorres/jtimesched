package de.dominik_geyer.jtimesched.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectSerializerTestDataflow {
    TransformerHandler hd;
    AttributesImpl atts;
    ByteArrayOutputStream stream;
    OutputStreamWriter out;
    StreamResult streamResult;

    @BeforeEach
    void setup() throws TransformerConfigurationException {
        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        hd = tf.newTransformerHandler();

        atts = new AttributesImpl();

        stream = new ByteArrayOutputStream();
        out = new OutputStreamWriter(stream);
        streamResult = new StreamResult(out);
        hd.setResult(streamResult);
    }

    @Test
    void addXmlElementAttsNotNullDataNull() throws SAXException, IOException {
        ProjectSerializer.addXmlElement(hd, "test-element", atts, null);

        out.flush();
        String result = stream.toString();

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><test-element/>";

        assertEquals(expected, result);
    }
    @Test
    void addXmlElementAttsNullDataNull() throws SAXException, IOException {
        ProjectSerializer.addXmlElement(hd, "test-element", null, null);

        out.flush();
        String result = stream.toString();

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><test-element/>";

        assertEquals(expected, result);
    }
    @Test
    void addXmlElementAttsNotNullDataNotNull() throws SAXException, IOException {
        String data = "test-data";

        ProjectSerializer.addXmlElement(hd, "test-element", atts, data);

        out.flush();
        String result = stream.toString();

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><test-element>test-data</test-element>";

        assertEquals(expected, result);
    }

    @Test
    void startXmlElementWithAttsNull() throws SAXException, IOException {

        ProjectSerializer.startXmlElement(hd, "start", null);

        out.flush();
        String result = stream.toString();

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><start";

        assertEquals(expected, result);
    }
}
