package xpath;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XPathThingiesTest {

    final String filename = "src/test/resources/contactInfo.xml";

    @Test
    void happyPath() {
        XPathThingies xPathThingies = new XPathThingies();
        String expression = "/info/user/name";
        try {
            InputStream inputStream = xPathThingies.getFileContent(filename);
            Document document = xPathThingies.getDocument(inputStream);
            String valueFromPath = xPathThingies.getValueFromPath(document, expression);

            assertEquals("Kim", valueFromPath);
        } catch (ParserConfigurationException | IOException
                | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenWrongXPath_thenGetEmptyString() {
        XPathThingies xPathThingies = new XPathThingies();
        String expression = "/info/user/age";
        try {
            InputStream inputStream = xPathThingies.getFileContent(filename);
            Document document = xPathThingies.getDocument(inputStream);
            String valueFromPath = xPathThingies.getValueFromPath(document, expression);

            assertEquals("", valueFromPath);
        } catch (ParserConfigurationException | IOException
                | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}