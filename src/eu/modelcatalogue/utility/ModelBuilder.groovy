package eu.modelcatalogue.utility

import eu.modelcatalogue.core.DataElement
import eu.modelcatalogue.core.Model
import eu.modelcatalogue.core.ValueDomain
import eu.modelcatalogue.utility.XMLArtefacts.XMLElement
import eu.modelcatalogue.utility.XMLArtefacts.XMLSchema

/**
 * Created by davidmilward on 27/02/2014.
 */
class ModelBuilder {

    XMLSchema currentSchema
    ArrayList<Model> models

    ModelBuilder(XMLSchema sch){
        currentSchema = sch
        models = new ArrayList<Model>()
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


    }

    Model getModel(XMLElement element){
        Model model = new Model()
        Integer noContained = element.getContainedElements()
        if(noContained == 0){
            //we have a data element rather than a model
            DataElement de1 = new DataElement()
            de1.setName(element.getName())
            de1.id = 1920
            ValueDomain vd1 = new ValueDomain()

            println "DATA ELEMENT name=" + element.getName()
            Map attributes = element.schemaAttributeMap
            attributes.each {
               // println "....attributes = " +it
            }
            println "VALUE DOMAIN name=" + element.getName()
            println "VALUE DOMAIN type=" + element.getsType()




        }else{

            List containedElements = element.getElementList()
            containedElements.each{ model = getModel(it)}

        }
        return model
    }


}
