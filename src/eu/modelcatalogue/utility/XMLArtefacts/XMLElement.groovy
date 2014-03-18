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
    /**
     *   Element content : this is the (text) content of the element
     */
    StringBuffer elementContent
    /**
     *   Constructor : sets up a new content buffer
     */
    XMLElement(){
        elementContent = new StringBuffer()
    }
    /**
     *    clearContent
     *    @description : replaces old content StringBuffer() with new StringBuffer object and in
     *    effect clears the content buffer
     *
     *    @return : void
     */
    void clearContent(){
        elementContent = new StringBuffer()
    }
    /**
     *    addContent
     *    @description : adds new String content to the content buffer
     *
     *    @return : void
     */
    void addContent(String newContent){
        elementContent.append(newContent)
    }
    /**
     *    getContent
     *    @description : returns the content buffer contents
     *
     *    @return : String
     */
    String getContent(){
        return elementContent.toString()
    }

}
