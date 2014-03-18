package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 27/02/2014.
 */
class XMLElementTest extends GroovyTestCase {
    XMLElement element
    void setUp() {
        super.setUp()
        element = new XMLElement()
        element.addContent("TESTContent2")
    }

    void testAddContent() {
        assert element.getContent() != ""
        element.clearContent()
        element.addContent("TESTContent1")
        assert element.getContent() == "TESTContent1"
    }

    void testGetContent() {
        element.clearContent()
        element.addContent("TESTContent2")
        assert element.getContent() == "TESTContent2"
    }

    void testClearContent(){
        assert element.getContent() != ""
        element.clearContent()
        assert element.getContent() == ""
    }


}
