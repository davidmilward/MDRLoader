package eu.modelcatalogue.utility

import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory
import org.xml.sax.helpers.DefaultHandler
import org.xml.sax.*

/**
 * Created by davidmilward on 25/02/2014.
 */
public class MDRLoader extends org.xml.sax.helpers.DefaultHandler{
    def messages = []
    def currentMessage
    def countryFlag = false
    void startElement(String ns, String localName, String qName, Attributes atts) {
        switch (qName) {
            case 'xs:schema':
                xmlSchema = setSchemaAttributes(attrs);
                currentElement = xmlSchema;
            case 'xs:simpletype':
                countryFlag = true; break
            case 'xs:restriction':
                currentMessage += atts.getValue('type') + ' record'; break
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
                messages << currentMessage; break
            case 'xs:simpletype':
                currentMessage += ' has a '; countryFlag = false; break
        }
    }


    XMLSchemamc setSchemaAttributes(attr){



    }


}

