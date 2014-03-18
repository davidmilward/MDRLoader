package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 04/03/2014.
 */
class XMLSimpleTypeTest extends GroovyTestCase {

    XMLSimpleType sType

    void setUp() {
        super.setUp()
        sType = new XMLSimpleType()
        sType.setParentType((String)XMLSimpleType.parent.SCHEMA)
        sType.setId(100)
        sType.addAttribute("name","value")
        sType.addAttribute("use","required")
    }

    void testGetParentType() {
        assert "SCHEMA" == sType.getParentType()
    }

    void testSetParentType() {
        sType.setParentType((String)XMLSimpleType.parent.ATTRIBUTE)
        assert "ATTRIBUTE" == sType.getParentType()
        sType.setParentType((String)XMLSimpleType.parent.ELEMENT)
        assert "ELEMENT" == sType.getParentType()
        sType.setParentType((String)XMLSimpleType.parent.LIST)
        assert "LIST" == sType.getParentType()
        sType.setParentType((String)XMLSimpleType.parent.UNION)
        assert "UNION" == sType.getParentType()
        sType.setParentType((String)XMLSimpleType.parent.RESTRICTION)
        assert "RESTRICTION" == sType.getParentType()
    }

    void testGetId() {
        assert sType.getId() == 100
    }

    void testSetId() {
       sType.setId(121)
       assert sType.getId() == 121
    }

    void testGetAttributes() {
        assert sType.getAttribute("name") == "value"
    }

    void testSetAttributes() {
        assert sType.getAttribute("name") == "value"
        sType.addAttribute("name","joe")
        assert sType.getAttribute("name") == "joe"
    }
}
