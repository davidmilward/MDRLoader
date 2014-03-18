package eu.modelcatalogue.utility

import eu.modelcatalogue.utility.XMLArtefacts.XMLComplexElement
import eu.modelcatalogue.utility.XMLArtefacts.XMLComplexType
import eu.modelcatalogue.utility.XMLArtefacts.XMLElement
import eu.modelcatalogue.utility.XMLArtefacts.XMLSchema
import eu.modelcatalogue.utility.XMLArtefacts.XMLSequence
import eu.modelcatalogue.utility.XMLArtefacts.XMLSimpleType

import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

/**
 *
 *  Each test parses a slightly different xsd file, set up for the relevant test
 *  and rather than test each method separately we test the parse method and each time
 *  examine a different aspect of the schema which has been built.  The relevant
 *  methods being tested are listed in each test, if we were to test each method
 *  separately we would have to mock the Attribute, and this seemed an easier way
 *  of doing things
 *
 * Created by davidmilward on 27/02/2014.
 */
class MDRLoaderTest extends GroovyTestCase {
    XMLSchema schema
    MDRLoader handler
    SAXParser reader
    void setUp() {
        super.setUp()
        handler = new eu.modelcatalogue.utility.MDRLoader()
        reader = SAXParserFactory.newInstance().newSAXParser()
    }

    void testSetSchemaAttributes() {
        /** this is testing loader.setSchemaAttributes(Attrs)**/
        reader.parse(new File("COSDSCHEMATEST.xsd"),handler)
        schema = handler.getCurrentSchema()
        Map namespaces = schema.getNameSpaces()

        assert namespaces.get("xmlns:fn") == "http://www.w3.org/2005/xpath-functions"
        assert namespaces.get("xmlns:xdt") == "http://www.w3.org/2005/xpath-datatypes"
        assert namespaces.get("xmlns:UML") == "omg.org/UML1.3"
        assert namespaces.get("xmlns") == "http://www.datadictionary.nhs.uk/messages/COSD-v1-0"
        assert namespaces.get("xmlns:xs") == "http://www.w3.org/2001/XMLSchema"
        assert namespaces.get("xmlns:COSD") == "http://www.datadictionary.nhs.uk/messages/COSD-v1-0"

        assert schema.getTargetNameSpace() == "http://www.datadictionary.nhs.uk/messages/COSD-v1-0"
        assert schema.getElementFormDefault() == "unqualified"
        assert schema.getAttributeFormDefault() == "unqualified"
        assert schema.getBlockDefault() == "#all"
        assert schema.getFinalDefault() == "extension"

        assert schema.getName() == "COSDBreastContent_XMLSchema-v1-0"
        assert schema.schemaId == "COSDBreastContent_XMLSchema-v1-0"
        assert schema.schemaVersion == "1-0"

    }

    void testSetSimpleTypeAttributes(){

        /** this is testing loader.setElementAttributes(Attrs)**/
        reader.parse(new File("COSDSIMPLETYPETEST.xsd"),handler)
        schema = handler.getCurrentSchema()
        List simlements = schema.getSimpletypes()
        XMLSimpleType testElement1 = simlements.get(0)
        assert testElement1.getName().equals("postalCodeType")
        XMLSimpleType testElement2 = simlements.get(1)
        assert testElement2.getName().equalsIgnoreCase("postal_type")
        //@ToDo We need to test to see if this has registered the restrictions embedded in the schema

    }

    void testSetElementAttributes() {
        /** this is testing loader.setElementAttributes(Attrs)
         * <xs:element name="TestSimpleElement2" type="xs:string">TESTDATA2</xs:element>**/

        reader.parse(new File("COSDELEMENTTEST.xsd"),handler)
        schema = handler.getCurrentSchema()
        List celements = schema.getComplexElements()
        if(celements.size() >= 4){
            XMLComplexElement testElement1 = celements.get(0)

            assert testElement1.elementContent.toString() == "TESTDATA2"
            assert testElement1.getName() == "TestSimpleElement2"
            assert testElement1.getsType() == "xs:string"

            XMLComplexElement testElement2 = celements.get(1)

            assert testElement2.elementContent.toString() == ""
            assert testElement2.getName() == "TestSimpleElement1"
            assert testElement2.getsType() == "xs:string"

            XMLComplexElement testElement3 = celements.get(2)
            assert testElement3.getName() == "TestComplexElement1"
            assert testElement3.getsType() == "specialName"

            XMLComplexElement testElement4 = celements.get(3)
            List comElements = testElement4.elementList
            XMLComplexType testComplexType4 = comElements.get(0)
            List seqElements = testComplexType4.elementList
            XMLSequence testSequence4 = seqElements.get(0)
            List simElements = testSequence4.elementList

            XMLElement testsElement1 = simElements.get(0)
            XMLElement testsElement2 = simElements.get(1)

            assert testsElement1.elementContent.toString() == ""
            assert testsElement2.elementContent.toString() == ""
            assert testsElement1.getName() == "firstname"
            assert testsElement2.getName() == "lastname"
            assert testsElement1.getsType() == "xs:string"
            assert testsElement2.getsType() == "xs:string"
        }

    }

}
