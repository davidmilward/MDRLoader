package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 25/02/2014.
 */
class XMLSchema extends XMLElement {

    Map nameSpaces
    List complexElements
    List simpletypes
    List imports

    String targetNameSpace
    String elementFormDefault
    String attributeFormDefault
    String blockDefault
    String finalDefault
    String schemaVersion
    String schemaId

    XMLSchema(){
        imports = new ArrayList<String>()
        complexElements = new ArrayList<XMLElement>()
        simpletypes = new ArrayList<XMLElement>()
        nameSpaces = new HashMap<String, String>()
    }

    void addElement(XMLComplexElement ce){
        complexElements.add(ce)
    }

    void addElement(XMLSimpleType st){
        simpletypes.add(st)
    }

    void addImportLocation(String sloc){
        imports.add(sloc)
    }
    List getImportLocations(){
        return imports
    }

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
