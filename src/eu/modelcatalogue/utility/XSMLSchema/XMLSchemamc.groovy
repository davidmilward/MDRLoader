package eu.modelcatalogue.utility.XSMLSchema

/**
 * Created by davidmilward on 25/02/2014.
 */
class XMLSchemamc extends SimpleElement {

    Map nameSpaces
    List complexElements

    String targetNameSpace
    String elementFormDefault
    String attributeFormDefault
    String blockDefault
    String finalDefault
    String schemaVersion
    String schemaId



    void addNameSpace(String key,String value){
        nameSpaces.put(key, value)
    }
    String getSpecificNamespace(String key){
        return nameSpaces.get(key)
    }
    Map getAllNamespaces(){
        return nameSpaces
    }

    void addSimpleElement(SimpleElement se){
        simpleElements.add(se)
    }
    List getSimpleElements(){
        return simpleElements
    }

    void addSimpleElement(ComplexElement se){
        complexElements.add(se)
    }
    List getComplexElements(){
        return complexElements
    }







}
