package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 26/02/2014.
 */
class SimpleElement {

    String sType
    String name
    String restrictionBase
    StringBuffer content
    boolean bFixed = false
    boolean bDefault = false;


    void addContent(String newContent){
        content.append(newContent)
    }
    String getContent(){
        return content.toString()
    }


}
