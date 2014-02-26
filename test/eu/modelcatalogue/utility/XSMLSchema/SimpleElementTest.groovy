package eu.modelcatalogue.utility.XSMLSchema

/**
 * Created by davidmilward on 26/02/2014.
 */
class SimpleElementTest extends GroovyTestCase {
    SimpleElement simpleElement

    void setUp() {
        super.setUp()
        this.simpleElement = new SimpleElement()
        simpleElement.addAttribute("TESTATTRIBUTE1","TESTATTCONTENT1")
        simpleElement.addAttribute("TESTNAME1","TESTATTCONTENTNAME1")
        simpleElement.addAttribute("TESTTYPE1","TESTATTCONTENTTYPE1")
        simpleElement.addEnumItem("Y")
        simpleElement.addEnumItem("N")
        simpleElement.addEnumItem("X")
        simpleElement.setReference(12)
        simpleElement.setsType("TESTTYPE")
        simpleElement.setName("TESTNAME")
        simpleElement.addContent("NEW Content1")
        simpleElement.addContent("NEW Content2")
        simpleElement.setRestrictionBase("TEST BASE")
    }

    void testAddAttribute() {
        //Check the starting point
        int sizeAttr = simpleElement.attributeStore.size()
        //Add an attribute
        simpleElement.addAttribute("TESTATTRIBUTE2","TESTATTCONTENT2")
        assert simpleElement.attributeStore.size() == (sizeAttr + 1)
        String sValue = simpleElement.getNamedAttribute("TESTATTRIBUTE2")
        assert sValue == "TESTATTCONTENT2"
    }



    void testGetNamedAttribute() {

        String sValue = simpleElement.getNamedAttribute("TESTTYPE1")
        assert sValue == "TESTATTCONTENTTYPE1"

    }

    void testAddEnumItem() {

        int tpSize = simpleElement.getTypeEnumerations().size()

        simpleElement.addEnumItem("Y")

        assert simpleElement.getTypeEnumerations().size() == (tpSize + 1)

    }

    void testGetAttributeStore() {

        Map dlAttrib = simpleElement.getAttributeStore()
        String testValue1 = dlAttrib.get("TESTNAME1")
        assert testValue1 == "TESTATTCONTENTNAME1"

    }

    void testSetAttributeStore() {

        Map newStore = new HashMap()
        newStore.put("ASTest1","ASTestValue")
        simpleElement.setAttributeStore(newStore)
        String testValue = simpleElement.getNamedAttribute("ASTest1")
        assert testValue == "ASTestValue"

    }

    void testGetTypeEnumerations() {

        List testEnums = simpleElement.getTypeEnumerations()
        assert "Y" == testEnums[0]
        assert "N" == testEnums[1]
        assert "X" == testEnums[2]

    }

    void testSetTypeEnumerations() {

        List testEnums = new ArrayList()
        testEnums.add("A")
        testEnums.add("B")
        testEnums.add("C")
        simpleElement.setTypeEnumerations(testEnums)
        List testEnums2 = simpleElement.getTypeEnumerations()
        assert "A" == testEnums2[0]
        assert "B" == testEnums2[1]
        assert "C" == testEnums2[2]

    }

    void testGetReference() {

        int ref = simpleElement.getReference()
        assert ref == 12
    }

    void testSetReference() {
        simpleElement.setReference(99)
        int ref = simpleElement.getReference()
        assert ref == 99
    }

    void testGetsType() {

        String sType = simpleElement.getsType()
        assert sType == "TESTTYPE"
    }

    void testSetsType() {

        simpleElement.setsType("ABC")
        String sType = simpleElement.getsType()
        assert sType == "ABC"

    }

    void testGetName() {
        String name = simpleElement.getName()
        assert name == "TESTNAME"
    }

    void testSetName() {
        simpleElement.setName("NewName")
        String name = simpleElement.getName()
        assert name == "NewName"
    }

    void testGetRestrictionBase() {
        String rb = simpleElement.getRestrictionBase()
        assert rb == "TEST BASE"
    }

    void testSetRestrictionBase() {
        String nb = "NewBase"
        simpleElement.setRestrictionBase(nb)
        assert "NewBase" == simpleElement.getRestrictionBase()
    }

    void testGetContent() {
        String cont = simpleElement.getContent()
        assert cont == "NEW Content1NEW Content2"
    }

    void testSetContent() {
        StringBuffer newContent = new StringBuffer()
        newContent.append("NewStuff")
        simpleElement.setContent(newContent)
        String newContentString = simpleElement.getContent()
        assert newContentString == "NewStuff"

    }
}
