package eu.modelcatalogue.utility

import eu.modelcatalogue.core.DataElement
import eu.modelcatalogue.core.Model
import eu.modelcatalogue.core.ValueDomain
import eu.modelcatalogue.utility.XSMLSchema.ComplexElement

import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory
import org.xml.sax.helpers.DefaultHandler
import org.xml.sax.*
import eu.modelcatalogue.utility.XSMLSchema.XMLSchemamc
import eu.modelcatalogue.utility.XSMLSchema.SimpleElement
import eu.modelcatalogue.utility.XSMLSchema.ComplexElement
import eu.modelcatalogue.utility.XSMLSchema.Restriction

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

       XMLSchemamc schema =  this.currentSchema
       List sList = schema.getSimpleElements()
       List elements = sList.collect{ makeDEVD(it)  }

       def models = []
        ModelBuilder  mb = ModelBuilder(schema)
        models = mb.getMDRModels()



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
                XMLSchemamc xmlSchema = setSchemaAttributes(attrs)
                currentSchema = xmlSchema
                currentElement = xmlSchema
                stack.push(xmlSchema)
                break
            case 'xs:element':
                SimpleElement element = setSimpleElementAttributes(attrs)
                currentElement.addElement(element)
                currentElement = element
                stack.push(element)

                break
            case 'xs:simpletype':
                SimpleElement stDE = setSimpleElementAttributes(attrs)
                currentElement.addElement(stDE)
                currentElement = stDE
                stack.push(stDE)
                break
            case 'xs:restriction':
                SimpleElement restDE = new SimpleElement()
                currentElement.addElement(restDE)
                currentElement = restDE
                stack.push(restDE)
                break
            case 'xs:include':
                SimpleElement includeDE = setSimpleElementAttributes(attrs)
                currentElement.addElement(includeDE)
                currentElement = includeDE
                stack.push(includeDE)
                break
            case 'xs:complexType':
                ComplexElement ctDE = setComplexAttributes(attrs)
                currentElement.addElement(ctDE)
                currentElement = ctDE
                stack.push(ctDE)

                break
            case 'xs:sequence':
                SimpleElement seqDE = setSimpleElementAttributes(attrs)
                currentElement.addElement(seqDE)
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
            default :
                break
        }
    }


    XMLSchemamc setSchemaAttributes(Attributes attrs){
        elemCntr++;
        XMLSchemamc se = new XMLSchemamc()
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
                        se.setTargetNameSpace(value);
                        break;
                    case "elementFormDefault":
                        se.setElementFormDefault(value);
                        break;
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

    public SimpleElement setSimpleElementAttributes( Attributes attrs){
        elemCntr++;
        SimpleElement se = new SimpleElement();
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
            }
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry)ith.next();
                println "SIMPLE ELEMENT key=" + me.getKey() + ":value=" + me.getValue();
            }
            se.setAttributeStore(attrsMap)
        }
        return se;
    }

    public SimpleElement setAttributes( Attributes attrs){
        elemCntr++;
        SimpleElement se = new SimpleElement();
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
            }
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                Map.Entry me = (Map.Entry)ith.next();
                //o.println("key=" + me.getKey() + ":value=" + me.getValue());
            }
            se.setAttributeStore(attrsMap);
        }
        return se;
    }

    public ComplexElement setComplexAttributes( Attributes attrs){
        elemCntr++;
        ComplexElement ce = new ComplexElement();
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
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
            ce.setAttributeStore(attrsMap);
        }
        return ce;
    }

    public Restriction setRestrictionAttributes( Attributes attrs){
        elemCntr++;
        Restriction re = new Restriction();
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
            }
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry)ith.next();
                //o.println("key=" + me.getKey() + ":value=" + me.getValue());
            }
            re.setAttributeStore(attrsMap);
        }
        return re;

    }


}

