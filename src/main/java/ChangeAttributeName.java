public class ChangeAttributeName {

    public static void main(String[] args) {
        ChangeAttributeName can = new ChangeAttributeName();
        can.doit();
    }
    private void doit() {
        final String dir = "src/main/resources/";
        final String outputDir = "target/";
        final String xmlFile = dir + "changeAttributeName.xml";
        final String xslFile = dir + "changeAttributeName.xsl";
        final String outputXmlFile = outputDir + "modifiedAttributeName.xml";

        XsltTransformer.transform(xmlFile, xslFile, outputXmlFile);
    }
}
