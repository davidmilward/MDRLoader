package eu.modelcatalogue.utility

import eu.modelcatalogue.core.DataElement
import eu.modelcatalogue.core.DataType
import eu.modelcatalogue.core.Model
import eu.modelcatalogue.core.ValueDomain
import eu.modelcatalogue.utility.XMLArtefacts.XMLElement
import eu.modelcatalogue.utility.XMLArtefacts.XMLSchema
import eu.modelcatalogue.utility.XMLArtefacts.XMLSequence

/**
 * Created by davidmilward on 27/02/2014.
 */
class ModelBuilder {

    XMLSchema currentSchema
    ArrayList<Model> models
    Map<String,DataType> xmlDataTypes
    Random random = new Random()
    Map<String, String> ModelElements

    ModelBuilder(XMLSchema sch){
        currentSchema = sch
        models = new ArrayList<Model>()
        xmlDataTypes = new HashMap<String,DataType>()
        ModelElements = new HashMap<String,String>()
        buildXMLDataTypes()
    }



    ArrayList<Model> getMDRModelsFromSchema(){

        List elements = currentSchema.getComplexElements()
        if(elements!=null){
            List<Model> modelz = new ArrayList<Model>()
            elements.each {
                modelz.add(getModel(it))
            }
        }


        elements = currentSchema.getElementList()
        if(elements!=null){
            List<Model> modelz = new ArrayList<Model>()
            elements.each {
                modelz.add(getModel(it))
            }
        }

        def importedSchemas = currentSchema.getImportLocations()
        importedSchemas.each {
            println "MODEL will need the following imported schemas: " + it
        }


    }

    Model getModel(XMLElement element){
        Model model = new Model()
        Integer noContained = element.getContainedElements()
        if(noContained == 0){
            //we may have a data element rather than a model - so we can add the data elements to the model
            if(element.getsType() =~ "xs:" ){
                //we have a data element
                DataElement de1 = new DataElement()
                de1.setName(element.getName())
                de1.id = random.nextInt(10 ** 6)
                ValueDomain vd1 = new ValueDomain()
                println "DATA ELEMENT name=" + element.getName()
                Map attributes = element.schemaAttributeMap
                attributes.each {
                    println "....attributes = " +it
                }
                vd1.setName(element.getName())
                vd1.setDataType(xmlDataTypes.get(element.getsType()))
                println "VALUE DOMAIN name=" + vd1.getName()
                println "VALUE DOMAIN type=" + vd1.getDataType().toString()
                //de1.addToInstantiatedBy(vd1)
                //model.addToContains(de1)
            }else{
                //We have a model so ....
                Model newModel = new Model()
                String name = element.getName()
                String stype = element.getsType()
                if(element.class == XMLSequence){
                    newModel.setName("model container: xml Sequence")
                }else if ((name != null | !name.equals("") )&&(stype != null | !stype.equals("") )){
                    println "Sub-model name=" + name
                    println "Sub-model type=" + stype
                    newModel.setName(name)
                    newModel.setDescription(stype)
                }
                if(stype != null){
                    ModelElements.put(name, stype)
                }
                model.addToModelContainer(newModel)
            }
        }else{
            List containedElements = element.getElementList()
            containedElements.each{
                model = getModel(it)
                model.addToModelContainer(model)}
        }
        return model
    }

    void buildXMLDataTypes(){

        DataType decimalXS = new DataType()
        xmlDataTypes.put("xs:decimal",decimalXS)
        DataType integerXS = new DataType()
        xmlDataTypes.put("xs:integer",integerXS)
        DataType byteXS = new DataType()
        xmlDataTypes.put("xs:byte",byteXS)
        DataType intXS = new DataType()
        xmlDataTypes.put("xs:int",intXS)
        DataType longXS = new DataType()
        xmlDataTypes.put("xs:long",longXS)
        DataType negativeIntegerXS = new DataType()
        xmlDataTypes.put("xs:negativeinteger",negativeIntegerXS)
        DataType nonNegativeIntegerXS = new DataType()
        xmlDataTypes.put("xs:nonnegativeinteger",nonNegativeIntegerXS)
        DataType nonPositiveIntegerXS = new DataType()
        xmlDataTypes.put("xs:nonpositiveinteger",nonPositiveIntegerXS)
        DataType positiveIntegerXS = new DataType()
        xmlDataTypes.put("xs:positiveinteger",positiveIntegerXS)
        DataType shortXS = new DataType()
        xmlDataTypes.put("xs:short",shortXS)
        DataType unsignedLongXS = new DataType()
        xmlDataTypes.put("xs:unsignedlong",unsignedLongXS)
        DataType unsignedIntXS = new DataType()
        xmlDataTypes.put("xs:unsignedint",unsignedIntXS)
        DataType unsignedShortXS = new DataType()
        xmlDataTypes.put("xs:unsignedshort",unsignedShortXS)
        DataType unsignedByteXS = new DataType()
        xmlDataTypes.put("xs:unsignedbyte",unsignedByteXS)

        DataType dateXS = new DataType()
        xmlDataTypes.put("xs:date",dateXS)
        DataType timeXS = new DataType()
        xmlDataTypes.put("xs:time",timeXS)
        DataType dateTimeXS = new DataType()
        xmlDataTypes.put("xs:datetime",dateTimeXS)
        DataType durationXS = new DataType()
        xmlDataTypes.put("xs:duration",durationXS)

        DataType stringXS = new DataType()
        xmlDataTypes.put("xs:string",stringXS)
        DataType normalizedStringXS = new DataType()
        xmlDataTypes.put("xs:normalizedstring",normalizedStringXS)
        DataType tokenXS = new DataType()
        xmlDataTypes.put("xs:token",tokenXS)
        DataType entitiesXS = new DataType()
        xmlDataTypes.put("xs:ENTITIES",entitiesXS)
        DataType entityXS = new DataType()
        xmlDataTypes.put("xs:ENTITY",entityXS)
        DataType idXS = new DataType()
        xmlDataTypes.put("xs:ID",idXS)
        DataType idrefXS = new DataType()
        xmlDataTypes.put("xs:IDREF",idrefXS)
        DataType languageXS = new DataType()
        xmlDataTypes.put("xs:language",languageXS)
        DataType nameXS = new DataType()
        xmlDataTypes.put("xs:name",nameXS)
        DataType mcNameXS = new DataType()
        xmlDataTypes.put("xs:MCNAME",mcNameXS)
        DataType nmTokenXS = new DataType()
        xmlDataTypes.put("xs:NMTOKEN",nmTokenXS)
        DataType nmTokensXS = new DataType()
        xmlDataTypes.put("xs:NMTOKENS",nmTokensXS)

        DataType booleanXS = new DataType();
        xmlDataTypes.put("xs:boolean",booleanXS)
        DataType binaryHexXS = new DataType()
        xmlDataTypes.put("xs:binaryhex",binaryHexXS)
        DataType binaryBase64 = new DataType()
        xmlDataTypes.put("xs:binarybase64",binaryBase64)

        DataType floatXS = new DataType()
        xmlDataTypes.put("xs:float",floatXS)
        DataType doubleXS = new DataType()
        xmlDataTypes.put("xs:double",doubleXS)
        DataType anyUriXS = new DataType()
        xmlDataTypes.put("xs:anyURI",anyUriXS)
        DataType qNameXS = new DataType()
        xmlDataTypes.put("xs:QNAME",qNameXS)
        DataType notationXS = new DataType()
        xmlDataTypes.put("xs:notation",notationXS)

    }



}
