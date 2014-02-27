package eu.modelcatalogue.utility.XMLArtefacts

/**
 * XMLElement : Class representing an an XMLSchema element as designated by the 'xs:element'
 * tag in the Schema. This maybe a Complex or a Simple element, depending on the definition
 *
 * Created by davidmilward on 27/02/2014.
 */
class XMLElement {

    /**
     *   Element name
     */
    String name
    /**
     *   Element type : this could refer to another complex element defined in the
     *   same Schema
     */
    String sType

    String restrictionBase
    boolean bAbstract = false
    boolean bMixed = false
    boolean bFixed = false
    boolean bDefault = false
    Map schemaAttributeMap
    List<XMLElement> elementList
    List<XMLRestriction> restrictionList
    List<XMLAttribute> elementAttributeList
    List typeEnumerations
    Integer reference
    Integer containedElements
    StringBuffer content
    /**
     * Constructor for XMLElement, the constructor build an element list
     * a restriction list and an attribute list, as well as a list of
     * type enumerations
     *
     * @param addressee Name for returned salutation to be addressed to.
     * @return "Hello!"
     */
    XMLElement(){
        schemaAttributeMap = new HashMap()
        typeEnumerations = new ArrayList()
        reference = 0
        containedElements = 0
        content = new StringBuffer()
        elementList = new ArrayList<XMLElement>()
        restrictionList = new ArrayList<XMLRestriction>()
        elementAttributeList = new ArrayList<XMLAttribute>()
    }
    /**
     * Add the content to the content buffer. By content we mean the text found
     * between the tag <xs:element>Content</xs:element>. In most Schemas this
     * will be empty.
     *
     * @param newContent
     * @return void
     */
    void addContent(String newContent){
        content.append(newContent)
    }
    String getContent(){
        return content.toString()
    }

    void addSchemaAttribute(String name, String value){
        schemaAttributeMap.put(name, value)
        if(name == "name"){name = value }
        if(name == "type")(sType = value)
    }

    String getSchemaAttribute(String key){
        return schemaAttributeMap.get(key)
    }

    void addEnumItem(String item){
        typeEnumerations.add(item)
    }
    void addElement(XMLElement element){
        elementList.add(element)
        containedElements++
    }
    void addRestriction(XMLRestriction element){
        restrictionList.add(element)
        containedElements++
    }
    void addElementAttribute(XMLAttribute element){
        elementAttributeList.add(element)
        containedElements++
    }
}
