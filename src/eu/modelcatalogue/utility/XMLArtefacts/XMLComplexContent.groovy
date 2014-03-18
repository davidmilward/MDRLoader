package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 01/03/2014.
 */
class XMLComplexContent extends XMLElement {

    XMLComplexType complexElement
    XMLRestriction xmlRestriction

    void addRestriction(XMLRestriction xmlRest){
        this.xmlRestriction = xmlRest
    }

    XMLRestriction getRestriction(){
        return this.xmlRestriction
    }

    void addComplexElement(XMLComplexType ce){
        this.complexElement = ce
    }

    void addContent(String newContent){
        if (xmlRestriction.validateContent(newContent)){
            elementContent.append(newContent)
        }
    }

}
