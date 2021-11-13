package xpath;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class XPathThingiesTest {

    final String filename = "src/test/resources/contactInfo.xml";

    @Test
    void happyPath() {
        XPathThingies xPathThingies = new XPathThingies();
        String expression = "/info/user/name";
        try (InputStream inputStream = xPathThingies.getFileContent(filename)) {
            final Document document = xPathThingies.getDocument(inputStream);
            final Optional<String> valueFromPath =
                    xPathThingies.getValueFromPath(document, expression);

            assertEquals("Kim", valueFromPath.orElse("NoFound"));

            ContactUser contactUser = new ContactUser();
            contactUser.setName("Maria");
            contactUser.setName(valueFromPath.orElseGet(contactUser::getName));

            assertEquals("Kim", contactUser.getName());
        } catch (ParserConfigurationException | IOException
                | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenWrongXPath_thenGetEmptyString() {
        final XPathThingies xPathThingies = new XPathThingies();
        final String expression = "/info/user/age";
        try (InputStream inputStream = xPathThingies.getFileContent(filename)){
            final Document document = xPathThingies.getDocument(inputStream);
            final  Optional<String> valueFromPath =
                    xPathThingies.getValueFromPath(document, expression);

            assertEquals("", valueFromPath.orElse("Kalle"));
        } catch (ParserConfigurationException | IOException
                | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void split() {
        final String expression = "/apa/bepa . /apa/cepa";
        String[] splits = expression.split("\\.");
        assertEquals(2, splits.length);
        assertEquals("/apa/bepa", splits[0].trim());
        assertEquals("/apa/cepa", splits[1].trim());
    }
}
