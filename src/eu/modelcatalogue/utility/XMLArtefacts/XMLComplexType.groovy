package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 27/02/2014.
 */
class XMLComplexType extends XMLComplexElement {

    Integer id
    String name
    boolean bAbstract
    boolean bMixed
    boolean bBlock
    boolean bFinal

    List<XMLSequence> sequences
    List<XMLComplexContent> complexContent




}
