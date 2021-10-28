public class AddNewElement {

    public static void main(String[] args) {
        AddNewElement ae = new AddNewElement();
        ae.doit();
    }
    private void doit() {
        final String dir = "src/main/resources/";
        final String outputDir = "target/";
        final String filename = "addNewElement";
        final String xmlFile = dir + filename + ".xml";
        final String xslFile = dir + filename + ".xsl";
        final String outputXmlFile = outputDir + filename + ".xml";

        XsltTransformer.transform(xmlFile, xslFile, outputXmlFile);
    }
}
