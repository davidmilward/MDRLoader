package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 01/03/2014.
 */
class XMLSequence extends XMLComplexElement{

    Integer noInSeq = 0
    //Map<Integer, XMLElement> mSequence
    List<XMLElement> lSequence

    XMLSequence(){
        lSequence = new ArrayList<XMLElement>()
    }

    void addElementToSequence(XMLElement element){
        lSequence.add(element)
        noInSeq++
    }

    XMLElement getSeqElement(Integer i){
       return  lSequence.get(i)
    }

}
