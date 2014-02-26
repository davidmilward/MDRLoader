package eu.modelcatalogue.utility.XSMLSchema

/**
 * Created by davidmilward on 26/02/2014.
 */
class SimpleElement {

    Map attributeStore
    List simpleElements
    List typeEnumerations
    Integer reference
    String sType
    String name
    String restrictionBase
    StringBuffer content

    SimpleElement(){
        attributeStore = new HashMap()
        typeEnumerations = new ArrayList()
        reference = 0
        content = new StringBuffer()
        simpleElements = new ArrayList()
    }

    void addContent(String newContent){
        content.append(newContent)
    }

    String getContent(){
        return content.toString()
    }

    void addAttribute(String name, String value){
        attributeStore.put(name, value)
    }

    String getNamedAttribute(String key){
       return attributeStore.get(key)
    }

    void addEnumItem(String item){
        typeEnumerations.add(item)
    }

    void addElement(SimpleElement element){
        simpleElements.add(element)
    }


}
