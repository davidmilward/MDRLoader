package eu.modelcatalogue.utility.XMLArtefacts

/**
 * This is an extension of the simple element which only allows for a name, a type and content.
 * If an element contains more that this, or if the type is not a registered XML Schema data type
 * them an XMLComplexType is used to represent the XSD element
 *
 * Created by davidmilward on 02/03/2014.
 */
class XMLComplexElement extends XMLSimpleType {

    String restrictionBase
    boolean bAbstract = false
    boolean bMixed = false
    boolean bFixed = false
    boolean bDefault = false
    Map schemaAttributeMap
    List typeEnumerations

    /**
     * Constructor for XMLComplexElement, the constructor build an element list
     * a restriction list and an attribute list, as well as a list of
     * type enumerations
     */
    XMLComplexElement(){
        schemaAttributeMap = new HashMap()
        typeEnumerations = new ArrayList()
        reference = 0
        containedElements = 0
        this.elementContent = new StringBuffer()

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
        elementContent.append(newContent)
    }
    String getContent(){
        return elementContent.toString()
    }
    void addEnumItem(String item){
        typeEnumerations.add(item)
    }



}
