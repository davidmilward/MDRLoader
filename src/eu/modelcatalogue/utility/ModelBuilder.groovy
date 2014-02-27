package eu.modelcatalogue.utility

import eu.modelcatalogue.core.Model
import eu.modelcatalogue.utility.XSMLSchema.XMLSchemamc

/**
 * Created by davidmilward on 27/02/2014.
 */
class ModelBuilder {

    XMLSchemamc schemamc
    ArrayList<Model> models

    ModelBuilder(XMLSchemamc sch){
        schemamc = sch
        models = new ArrayList<Model>()
    }

    



}
