package eu.modelcatalogue.utility.XMLArtefacts

/**
 *  This class models a Simple Type in the XMLSchema specification
 *  Simple types constrain the data which may appear in an element or attribute
 *  A simple type can only have a name if its parent is a SCHEMA
 *
 *  Created by davidmilward on 03/03/2014.
 */
class XMLSimpleType extends XMLElement {

    enum parent {
        ATTRIBUTE, ELEMENT, LIST, RESTRICTION, SCHEMA, UNION
    }

    String parentType
    Integer id
    Map<String, String> attributes
    XMLRestriction restriction
    List<XMLElement> elementList
    List<XMLRestriction> restrictionList
    List<XMLAttribute> elementAttributeList
    Integer reference
    Integer containedElements

    XMLSimpleType(){
        this.attributes = new HashMap<String, String>()
        elementList = new ArrayList<XMLElement>()
        restrictionList = new ArrayList<XMLRestriction>()
        elementAttributeList = new ArrayList<XMLAttribute>()
        containedElements = 0
    }

    void setSimpleName(String nm){

        switch(this.parentType){
            case "SCHEMA":
                this.name = nm
                break;
            default:
                name = ""
                break;
        }
    }
    void addAttribute(String key, String value){
        this.attributes.put(key,value)
    }
    String getAttribute(String key){
        return this.attributes.get(key)
    }
    void setRestriction(XMLRestriction rest){
        this.restriction = rest
    }
    XMLRestriction getRestriction(){
        return this.restriction
    }
    void setParentSchema(){
        parentType = XMLSimpleType.parent.SCHEMA
    }
    void setParentAttribute(){
        parentType = XMLSimpleType.parent.ATTRIBUTE
    }
    void setParentElement(){
        parentType = XMLSimpleType.parent.ELEMENT
    }
    void setParentList(){
        parentType = XMLSimpleType.parent.LIST
    }
    void setParentRestriction(){
        parentType = XMLSimpleType.parent.RESTRICTION
    }
    void setParentUnion(){
        parentType = XMLSimpleType.parent.UNION
    }

    void addSchemaAttribute(String name, String value){
        schemaAttributeMap.put(name, value)
        if(name == "name"){name = value }
        if(name == "type")(sType = value)
    }

    String getSchemaAttribute(String key){
        return schemaAttributeMap.get(key)
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
