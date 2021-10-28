import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XsltTransformer {
    public static void main(String[] args) {
        XsltTransformer xsltTransformer = new XsltTransformer();
        xsltTransformer.doit();
    }

    private void doit() {
        final String dir = "src/main/resources/";
        final String outputDir = "target/";
        final String xmlFile = dir + "courses.xml";
        final String xslFile = dir + "coursesXml2studentsXml.xsl";
        final String outputXmlFile = outputDir + "students.xml";

        StreamSource xslSource = new StreamSource(xslFile);
        StreamSource xmlSource = new StreamSource(xmlFile);
        StreamResult xmlOutput = new StreamResult(outputXmlFile);

        TransformerFactory transformerFactory =  TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer(xslSource);
            transformer.transform(xmlSource, xmlOutput);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
