package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 27/02/2014.
 */
class XMLRestrictionTest extends GroovyTestCase {

    XMLRestriction xmlRestriction
    XMLComplexType xmlBase1
    XMLComplexType xmlBase2
    XMLComplexContent xmlContent

    void setUp(String restrict){
        setUp()
        switch (restrict) {
            case "enum":
                List<String> enumList = new ArrayList()
                enumList.add("Mph")
                enumList.add("Knots")
                enumList.add("Kph")
                xmlRestriction.enumerations = enumList
                break
            case "max":
                xmlRestriction.setMax("22".toInteger())
                break
            case "min":
                xmlRestriction.setMin("2".toInteger())
                break;
            case "maxlen":
                xmlRestriction.setMaxlen("33".toInteger())
                break;
            case "minlen":
                xmlRestriction.setMinlen("3".toInteger())
                break;
            case "maxexclusive":
                xmlRestriction.setMaxExclusive("100".toInteger())
                break;
            case "minexclusive":
                xmlRestriction.setMinExclusive("5".toInteger())
                break;
            case "maxinclusive":
                xmlRestriction.setMaxInclusive("60".toInteger())
                break;
            case "mininclusive":
                xmlRestriction.setMinInclusive("7".toInteger())
                break;
            case "pattern":
                xmlRestriction.setPattern("*")
                break;
            case "totaldigits":
                xmlRestriction.setTotalDigits(12)
                break;
            case "fractiondigits":
                xmlRestriction.setFractionDigits(2)
                break;
            default:
                se.addNameSpace(key, value)
                break;

        }
        xmlContent.addRestriction(xmlRestriction)
        xmlContent.addComplexElement(xmlBase1)
        xmlBase2.addElement(xmlContent)

    }

    void setUp() {
        super.setUp()
        //Set up base element
        xmlBase1 = new XMLComplexType()
        xmlBase1.setName("base1")
        XMLElement elemA = new XMLElement();
        elemA.setName("aaa")
        elemA.setsType("xs:integer")
        XMLElement elemB = new XMLElement();
        elemB.setName("bbb")
        elemA.setsType("xs:string")

        XMLSequence anonSeq = new XMLSequence()
        anonSeq.addElementToSequence(elemA)
        anonSeq.addElementToSequence(elemB)

        xmlBase1.addElement(anonSeq)
        //Now set up a restriction on that base element
        xmlBase2 = new XMLComplexType()
        xmlContent = new XMLComplexContent()
        xmlRestriction = new XMLRestriction()
        xmlRestriction.setBaseName("base1")
        xmlRestriction.setSimpleBase(true)
        xmlRestriction.setBaseComponent(xmlBase1)
    }

    void testSimpleBase(){
        xmlRestriction.setSimpleBase(false)

    }

    void testGetBase() {
        assert xmlRestriction.getBaseName() == "base1"
    }

    void testSetBase() {
        xmlRestriction.setBaseName("base2")
        assert xmlRestriction.getBaseName() == "base2"
    }

    void testGetMax() {
       setUp("max")
       assert xmlRestriction.getMax() == 22
    }

    void testGetBaseComponent(){
        assert xmlRestriction.getBaseComponent().getName() == "base1"
    }

    void testSetMax() {
        setUp("max")
        xmlRestriction.setMax(35)
        assert xmlRestriction.getMax() == 35
    }

    void testGetMin() {
        setUp("min")
        assert xmlRestriction.getMin() == 2
    }

    void testSetMin() {
        setUp("min")
        xmlRestriction.setBaseComponent(xmlBase1)
        xmlRestriction.setMax("3".toInteger())
        assert xmlRestriction.getMax() == 3
    }

    void testGetMinlen() {
        setUp("minlen")
        assert xmlRestriction.getMinlen() == 3
    }

    void testSetMinlen() {
        setUp("minlen")
        xmlRestriction.setMinlen("5".toInteger())
        assert xmlRestriction.getMinlen() == 5
    }

    void testGetMaxlen() {
        setUp("maxlen")
        assert xmlRestriction.getMaxlen() == 33
    }

    void testSetMaxlen() {
        setUp("maxlen")
        xmlRestriction.setMaxlen("66".toInteger())
        assert xmlRestriction.getMaxlen() == 66
    }

    void testGetMinInclusive() {
        setUp("mininclusive")
        assert xmlRestriction.getMinInclusive() == 7
    }

    void testSetMinInclusive() {
        setUp("mininclusive")
        xmlRestriction.setMinInclusive("8".toInteger())
        assert xmlRestriction.getMinInclusive() == 8
    }

    void testGetMaxInclusive() {
        setUp("maxinclusive")
        assert xmlRestriction.getMaxInclusive() == 60
    }

    void testSetMaxInclusive() {
        setUp("maxinclusive")
        xmlRestriction.setMaxInclusive("13".toInteger())
        assert xmlRestriction.getMaxInclusive() == 13
    }

    void testGetMinExclusive() {
        setUp("minexclusive")
        assert xmlRestriction.getMinExclusive() == 5
    }

    void testSetMinExclusive() {
        setUp("minexclusive")
        xmlRestriction.setMinExclusive("8".toInteger())
        assert xmlRestriction.getMinExclusive() == 8
    }

    void testGetMaxExclusive() {
        setUp("maxexclusive")
        assert xmlRestriction.getMaxExclusive() == 100
    }

    void testSetMaxExclusive() {
        setUp("minexclusive")
        xmlRestriction.setMaxExclusive("8".toInteger())
        assert xmlRestriction.getMaxExclusive() == 8
    }

    void testGetTotalDigits() {
        setUp("totaldigits")
        assert xmlRestriction.getTotalDigits() == 12
    }

    void testSetTotalDigits() {
        setUp("totaldigits")
        xmlRestriction.setTotalDigits("5".toInteger())
        assert xmlRestriction.getTotalDigits() == 5
    }

    void testGetFractionDigits() {
        setUp("fractiondigits")
        assert xmlRestriction.getFractionDigits() == 2
    }

    void testSetFractionDigits() {
        setUp("fractiondigits")
        xmlRestriction.setFractionDigits("3".toInteger())
        assert xmlRestriction.getFractionDigits() == 3
    }

    void testGetEnumerations() {
        setUp("enum")
        ArrayList<String> enumlist = xmlRestriction.getEnumerations()
        assert enumlist[0] == "Mph"
        assert enumlist[1] == "Knots"
        assert enumlist[2] == "Kph"
    }

    void testSetEnumerations() {
        setUp("enum")
        List<String> enumList = new ArrayList()
        enumList.add("Java")
        enumList.add("C++")
        enumList.add("C#")
        xmlRestriction.enumerations = enumList
        ArrayList<String> enumlist1 = xmlRestriction.getEnumerations()
        assert enumlist1[0] == "Java"
        assert enumlist1[1] == "C++"
        assert enumlist1[2] == "C#"
    }

    void testGetPattern() {
        setUp("pattern")
        assert xmlRestriction.getPattern() == "*"
    }

    void testSetPattern() {
        setUp("pattern")
        xmlRestriction.setPattern("!")
        assert xmlRestriction.getPattern() == "!"
    }

    //@TODO whitespace tests
}
