public class Course2Student {

    public static void main(String[] args) {
        Course2Student c2s = new Course2Student();
        c2s.doit();
    }

    private void doit() {
        final String dir = "src/main/resources/";
        final String outputDir = "target/";
        final String xmlFile = dir + "courses.xml";
        final String xslFile = dir + "coursesXml2studentsXml.xsl";
        final String outputXmlFile = outputDir + "students.xml";

        XsltTransformer.transform(xmlFile, xslFile, outputXmlFile);
    }
}
