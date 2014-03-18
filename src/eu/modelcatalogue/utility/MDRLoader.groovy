package eu.modelcatalogue.utility

import eu.modelcatalogue.core.DataElement
import eu.modelcatalogue.core.Model
import eu.modelcatalogue.core.ValueDomain
import eu.modelcatalogue.utility.XMLArtefacts.XMLAnnotation
import eu.modelcatalogue.utility.XMLArtefacts.XMLAttribute
import eu.modelcatalogue.utility.XMLArtefacts.XMLComplexElement
import eu.modelcatalogue.utility.XMLArtefacts.XMLComplexType
import eu.modelcatalogue.utility.XMLArtefacts.XMLElement
import eu.modelcatalogue.utility.XMLArtefacts.XMLSequence
import eu.modelcatalogue.utility.XMLArtefacts.XMLSimpleType
import org.xml.sax.*
import eu.modelcatalogue.utility.XMLArtefacts.XMLSchema

import eu.modelcatalogue.utility.XMLArtefacts.XMLRestriction

/**
 * Created by davidmilward on 25/02/2014.
 */
public class MDRLoader extends org.xml.sax.helpers.DefaultHandler{
    def models = []
    def currentMessage
    def countryFlag = false
    def currentSchema = new XMLSchema()
    def currentElement = new XMLComplexElement()
    def currentContent = new String()

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

    void makeDEVD(XMLComplexElement se){
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
                XMLComplexElement element = setElementAttributes(attrs)
                currentElement.addElement(element)
                currentElement = element
                stack.push(element)
                break
            case 'xs:simpleType':
                XMLSimpleType stDE = setSimpleTypeAttributes(attrs)
                currentElement.addElement(stDE)
                currentElement = stDE
                stack.push(stDE)
                break
            case 'xs:restriction':
                XMLRestriction restDE = setRestrictionAttributes(attrs)
                currentElement.setRestriction(restDE)
                currentElement = restDE
                stack.push(restDE)
                break
            case 'xs:include':
                XMLComplexElement includeDE = setIncludeAttributes(attrs)
                break
            case 'xs:complexType':
                XMLComplexType ctDE = setComplexAttributes(attrs)
                currentElement.addElement(ctDE)
                currentElement = ctDE
                stack.push(ctDE)
                break
            case 'xs:sequence':
                XMLSequence seqDE = setSequenceAttributes(attrs)
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
            case 'xs:annotation':
                XMLAnnotation seqDE = getAttribute(attrs)
                currentElement.addElementAttribute(seqDE)
                currentElement = seqDE
                stack.push(seqDE)
                break
            case 'xs:documentation':
                XMLAttribute seqDE = getAttribute(attrs)
                currentElement.addElementAttribute(seqDE)
                currentElement = seqDE
                stack.push(seqDE)
                break
            case 'xs:whiteSpace':
                XMLAttribute seqDE = getAttribute(attrs)
                currentElement.addElementAttribute(seqDE)
                currentElement = seqDE
                stack.push(seqDE)
                break
            case 'xs:appinfo':
                XMLAttribute seqDE = getAttribute(attrs)
                currentElement.addElementAttribute(seqDE)
                currentElement = seqDE
                stack.push(seqDE)
                break
            case 'hfp:hasFacet':
                XMLAttribute seqDE = getAttribute(attrs)
                currentElement.addElementAttribute(seqDE)
                currentElement = seqDE
                stack.push(seqDE)
                break
            case 'hfp:hasProperty':
                XMLAttribute seqDE = getAttribute(attrs)
                currentElement.addElementAttribute(seqDE)
                currentElement = seqDE
                stack.push(seqDE)
                break
            case 'xs:list':
                XMLAttribute seqDE = getAttribute(attrs)
                currentElement.addElementAttribute(seqDE)
                currentElement = seqDE
                stack.push(seqDE)
                break
            case 'xs:minlength':
                XMLAttribute seqDE = getAttribute(attrs)
                currentElement.addElementAttribute(seqDE)
                currentElement = seqDE
                stack.push(seqDE)
                break
            case 'xs:maxlength':
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
        currentContent = String.copyValueOf(chars, offset, length).trim();
    }

    void endElement(String ns, String localName, String qName) {
        switch (qName) {
            case 'xs:schema':
               // messages << currentMessage;
                break
            case 'xs:element':
                currentElement.addContent(currentContent)
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:simpleType':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:restriction':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:include':
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
            case 'xs:annotation':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:documentation':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:whiteSpace':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:appinfo':
                stack.pop()
                currentElement = stack.last()
                break
            case 'hfp:hasFacet':
                stack.pop()
                currentElement = stack.last()
                break
            case 'hfp:hasProperty':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:list':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:minlength':
                stack.pop()
                currentElement = stack.last()
                break
            case 'xs:maxlength':
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
                        if(se.getName() == null){se.setName(value)}
                        break;
                    case "name":
                        se.setName(value)
                        break;
                    default:
                        se.addNameSpace(key, value)
                        break;

                }
            }
        }
        return se;
    }

    public XMLComplexElement setElementAttributes( Attributes attrs){
        elemCntr++;
        XMLComplexType se = new XMLComplexType()
        if(isParentSchema()){
           se.setParentSchema()
        }
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.length
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
                se.addSchemaAttribute(attrs.getLocalName(i), attrs.getValue(i))
                String localName = attrs.getLocalName(i)
                if(localName.equalsIgnoreCase("name")){
                    se.setName(attrs.getValue(i))
                }
                if(localName.equalsIgnoreCase("type")){
                    se.setsType(attrs.getValue(i))
                }
            }
            //DEBUG CODE//
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry)ith.next();
                println "ELEMENT key=" + me.getKey() + ":value=" + me.getValue();
            }
            //DEBUG CODE//
        }
        return se;
    }

    public XMLSimpleType setSimpleTypeAttributes( Attributes attrs){
        elemCntr++;
        XMLSimpleType st = new XMLSimpleType()
        if(isParentSchema()){
            st.setParentSchema()
        }
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            for (int i = 0; i < attrs.length; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
                st.addAttribute(attrs.getLocalName(i), attrs.getValue(i))
                String localName = attrs.getLocalName(i)
                if(localName.equalsIgnoreCase("name")){
                    st.setSimpleName(attrs.getValue(i))
                }
                if(localName.equalsIgnoreCase("type")){
                    st.setsType(attrs.getValue(i))
                }
            }
            //DEBUG CODE//
            Set<?> hmSet = attrsMap.entrySet();
            Iterator<?> ith = hmSet.iterator();
            while(ith.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry)ith.next();
                println "SimpleType key=" + me.getKey() + ":value=" + me.getValue();
            }
            //DEBUG CODE//
        }
        return st;
    }




    public XMLSequence setSequenceAttributes( Attributes attrs){
        elemCntr++;
        XMLSequence se = new XMLSequence();
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
        }
        return se;
    }

    public void setIncludeAttributes( Attributes attrs){
        elemCntr++;
        XMLElement se = new XMLElement();
        Map attrsMap = new HashMap<String, String>();
        if (attrs != null) {
            int len = attrs.getLength();
            for (int i = 0; i < len; i++) {
                attrsMap.put(attrs.getLocalName(i), attrs.getValue(i));
                if(attrs.getLocalName(i) == "schemaLocation"){
                    currentSchema.addImportLocation(attrs.getValue(i))
                }
            }
        }
    }

    public XMLAttribute getAttribute( Attributes attrs){
        elemCntr++;
        XMLAttribute se = new XMLElement();
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

    public XMLComplexType setComplexAttributes( Attributes attrs){
        elemCntr++;
        XMLComplexType ce = new XMLComplexType();
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
                println("key=" + me.getKey() + ":value=" + me.getValue());
            }
            //re.setAttributeStore(attrsMap);
        }
        return re;

    }

    boolean isParentSchema(){
        Object stackElement = stack.get(stack.size() - 1)
        if (stackElement instanceof eu.modelcatalogue.utility.XMLArtefacts.XMLSchema ){
            return true
        }
        return false
    }



}

