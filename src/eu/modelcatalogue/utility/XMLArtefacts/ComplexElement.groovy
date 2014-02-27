package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 26/02/2014.
 */
class ComplexElement extends SimpleElement{

    boolean bAbstract = false;
    boolean bMixed = false;
    Map attributeStore
    List simpleElements
    List typeEnumerations
    Integer reference


    ComplexElement(){
        attributeStore = new HashMap()
        typeEnumerations = new ArrayList()
        reference = 0
        content = new StringBuffer()
        simpleElements = new ArrayList()
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
