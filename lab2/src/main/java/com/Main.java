package com;

import com.model.Page;
import com.parsers.PageDOMParser;
import com.parsers.PageSAXParser;
import com.parsers.PageStAXParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        ArrayList<Page> pageList = PageDOMParser.parse("site.xml", "site.xsd");
        for (var page : pageList){
            System.out.println(page);
        }

        ArrayList<Page> pageList2 = PageSAXParser.parse("site.xml", "site.xsd");
        for (var page : pageList2){
            System.out.println(page);
        }


        ArrayList<Page> pageList3 = PageStAXParser.parse("site.xml", "site.xsd");
        for (var page : pageList3){
            System.out.println(page);
        }
    }
}
