package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 25/02/2014.
 */
class XMLSchema extends XMLElement {

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


}
