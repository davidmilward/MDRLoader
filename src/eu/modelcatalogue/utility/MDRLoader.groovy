package eu.modelcatalogue.utility

import eu.modelcatalogue.core.DataElement
import eu.modelcatalogue.core.Model
import eu.modelcatalogue.core.ValueDomain
import eu.modelcatalogue.utility.XMLArtefacts.XMLAttribute
import eu.modelcatalogue.utility.XMLArtefacts.XMLElement
import org.xml.sax.*
import eu.modelcatalogue.utility.XMLArtefacts.XMLSchema
import eu.modelcatalogue.utility.XMLArtefacts.SimpleElement
import eu.modelcatalogue.utility.XMLArtefacts.XMLRestriction

/**
 * Created by davidmilward on 25/02/2014.
 */
public class MDRLoader extends org.xml.sax.helpers.DefaultHandler{
    def models = []
    def currentMessage
    def countryFlag = false
    def currentSchema
    def currentElement
    List stack = new ArrayList()
    int elemCntr = 0


    void buildModels(){

       XMLSchema schema =  this.currentSchema
       //List sList = schema.getSimpleElements()
       //List elements = sList.collect{ makeDEVD(it)  }

       def models = []
        ModelBuilder  mb = new ModelBuilder(schema)
        models = mb.getMDRModelsFromSchema()

    }

    void makeDEVD(SimpleElement se){
        Model model = new Model()
        DataElement de = new DataElement()
        ValueDomain vd = new ValueDomain()
        de.setName(se.getName())
        vd.setName(se.getName())
        de.addToInstantiatedBy(vd)

    }


    void startElement(String ns, String localName, String qName, Attributes attrs) {
        switch (qName) {
            case 'xs:schema':
                XMLSchema xmlSchema = setSchemaAttributes(attrs)
                currentSchema = xmlSchema
                currentElement = xmlSchema
                stack.push(xmlSchema)
                break
            case 'xs:element':
                XMLElement element = setElementAttributes(attrs)
                currentElement.addElement(element)
                currentElement = element
                stack.push(element)

                break
            case 'xs:simpletype':
                XMLElement stDE = setElementAttributes(attrs)
                currentElement.addElement(stDE)
                currentElement = stDE
                stack.push(stDE)
                break
            case 'xs:restriction':
                XMLRestriction restDE = setRestrictionAttributes(attrs)
                currentElement.addRestriction(restDE)
                currentElement = restDE
                stack.push(restDE)
                break
            case 'xs:include':
                XMLElement includeDE = setElementAttributes(attrs)
                currentElement.addElement(includeDE)
                currentElement = includeDE
                stack.push(includeDE)
                break
            case 'xs:complexType':
                XMLElement ctDE = setComplexAttributes(attrs)
                currentElement.addElement(ctDE)
                currentElement = ctDE
                stack.push(ctDE)

                break
            case 'xs:sequence':
                XMLElement seqDE = setElementAttributes(attrs)
                currentElement.addElement(seqDE)
                currentElement = seqDE
                stack.push(seqDE)

                break
            case 'xs:attribute':
                XMLAttribute seqDE = getAttribute(attrs)
                currentElement.addElementAttribute(seqDE)
                currentElement = seqDE
                stack.push(seqDE)
                break
            default :
                break

        }
    }
    void characters(char[] chars, int offset, int length) {
        if (countryFlag) {
            currentMessage += new String(chars, offset, length)
        }
    }

    void endElement(String ns, String localName, String qName) {
        switch (qName) {
            case 'xs:schema':
               // messages << currentMessage;
                break
            case 'xs:element':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:simpletype':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:restriction':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:include':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:complexType':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:sequence':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:attribute':
                stack.pop()
                currentElement = stack.last()
                break
            default :
                break
        }
    }


    XMLSchema setSchemaAttributes(Attributes attrs){
        elemCntr++;
        XMLSchema se = new XMLSchema()
        String truncatedKey = "";
        Map hm = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                String attrlocalName = attrs.getLocalName(i);
                String attrValue = attrs.getValue(i);
                hm.put(attrlocalName, attrValue);
            }
            Set<?> hmSet = hm.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry)ith.next();
                String key = (String) me.getKey();
                if(key.length()>4){
                    truncatedKey = key.substring(0, 5);
                }
                String value = (String) me.getValue();
                if(truncatedKey.equals("xlmns")){
                    se.addNameSpace(key, value);
                }
                println("key=" + key + ":value=" + value);

                switch (key) {
                    case "targetNamespace":
                        se.setTargetNameSpace(value)
                        break
                    case "elementFormDefault":
                        se.setElementFormDefault(value)
                        break
                    case "attributeFormDefault":
                        se.setAttributeFormDefault(value);
                        break;
                    case "blockDefault":
                        se.setBlockDefault(value);
                        break;
                    case "finalDefault":
                        se.setFinalDefault(value);
                        break;
                    case "version":
                        se.setSchemaVersion(value);
                        break;
                    case "id":
                        se.setSchemaId(value);
                        break;
                    default:
                        break;

                }
            }
        }
        return se;
    }

    public XMLElement setElementAttributes( Attributes attrs){
        elemCntr++;
        XMLElement se = new XMLElement();
        //se.setsType(XMLTypeConstants.XML_STRING)
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
                se.addSchemaAttribute(attrs.getLocalName(i), attrs.getValue(i))
            }
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry)ith.next();
                println "ELEMENT key=" + me.getKey() + ":value=" + me.getValue();
            }
            Map attributes = se.schemaAttributeMap
            attributes.each {
                println "....attributes = " +it
                if(it.getKey() == "name"){
                    se.setName(it.getValue())
                }else if(it.getKey() == "type"){
                    se.setsType(it.getValue())
                }


            }
        }
        return se;
    }

    public XMLAttribute getAttribute( Attributes attrs){
        elemCntr++;
        XMLAttribute se = new XMLElement();
        //se.setsType(XMLTypeConstants.XML_STRING)
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
                se.addSchemaAttribute(attrs.getLocalName(i), attrs.getValue(i))
            }
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry)ith.next();
                println "ELEMENT key=" + me.getKey() + ":value=" + me.getValue();
            }
            //se.set(attrsMap)
        }
        return se;
    }

    public XMLElement setAttributes( Attributes attrs){
        elemCntr++;
        XMLElement se = new XMLElement();
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
                se.addSchemaAttribute(attrs.getLocalName(i), attrs.getValue(i))
            }
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                Map.Entry me = (Map.Entry)ith.next();
                //o.println("key=" + me.getKey() + ":value=" + me.getValue());
            }
           // se.setAttributeStore(attrsMap);
        }
        return se;
    }

    public XMLElement setComplexAttributes( Attributes attrs){
        elemCntr++;
        XMLElement ce = new XMLElement();
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
                ce.addSchemaAttribute(attrs.getLocalName(i), attrs.getValue(i))
            }
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry)ith.next();
                String key = (String) me.getKey();
                String value = (String) me.getValue();
                println "key=" + key + ":value=" + value
                switch (key) {
                    case "name":
                        ce.setName((String)me.getValue());
                        break;
                    case "abstract":
                        ce.setAbstract((String)me.getValue());
                        break;
                    case "mixed":
                        ce.setMixed((String)me.getValue());
                }
            }
            //ce.setAttributeStore(attrsMap);
        }
        return ce;
    }

    public XMLRestriction setRestrictionAttributes( Attributes attrs){
        elemCntr++;
        XMLRestriction re = new XMLRestriction();
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
                re.addSchemaAttribute(attrs.getLocalName(i), attrs.getValue(i))
            }
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry)ith.next();
                //o.println("key=" + me.getKey() + ":value=" + me.getValue());
            }
            //re.setAttributeStore(attrsMap);
        }
        return re;

    }


}

