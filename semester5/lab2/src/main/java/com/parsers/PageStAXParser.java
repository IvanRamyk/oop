package com.parsers;

import com.model.Page;
import com.model.PageChars;
import com.model.PageType;
import com.model.PollsAvailabilityType;
import com.validator.PageXSDValidator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class PageStAXParser{
    private static final XMLInputFactory factory = XMLInputFactory.newInstance();

    public static ArrayList<Page> parse(String XMLPath, String XSDPath) throws XMLStreamException, IOException {
        if (XSDPath != null)
            if (!PageXSDValidator.validateXMLSchema(XSDPath, XMLPath))
                return new ArrayList<>();

        InputStream s = Files.newInputStream(Path.of(XMLPath));
        XMLStreamReader reader = factory.createXMLStreamReader(s);
        ArrayList<Page> pages = new ArrayList<>();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLEvent.START_ELEMENT && reader.getLocalName().equals("page")) {
                pages.add(getPage(reader));
            }
        }
        return pages;
    }

    private static Page getPage(XMLStreamReader reader) throws XMLStreamException {
        PageChars pageChars = new PageChars(false, false, false, PollsAvailabilityType.none);
        int id = id = Integer.parseInt(reader.getAttributeValue(reader.getNamespaceURI(),"id"));;
        String title = "";
        boolean requiredAuth = false;
        PageType pageType = PageType.advertising;
        int event = 0;
        String current = "";
        boolean flag = true;
        do {
            event = reader.next();
            if (event == XMLEvent.START_ELEMENT) {
                current = reader.getLocalName();
            }
            if(event == XMLEvent.CHARACTERS) {
                String information = reader.getText();
                switch (current) {
                    case "title":
                        title = information;
                        current = "";
                        break;
                    case "authorize":
                        requiredAuth = information.equals("true");
                        current = "";
                        break;
                    case "type":
                        switch (information) {
                            case "advertising" -> {
                                pageType = PageType.advertising;
                                current = "";
                            }
                            case "mirror" -> {
                                pageType = PageType.mirror;
                                current = "";
                            }
                            case "portal" -> {
                                pageType = PageType.portal;
                                current = "";
                            }
                            case "news" -> {
                                pageType = PageType.news;
                                current = "";
                            }
                        }
                        break;
                    case "archiveAvailability":
                        pageChars.setArchiveAvailability(information.equals("true"));
                        current = "";
                        break;
                    case "emailAvailability":
                        pageChars.setEmailAvailability(information.equals("true"));
                        current = "";
                        break;
                    case "newsAvailability":
                        pageChars.setNewsAvailability(information.equals("true"));
                        current = "";
                        break;
                    case "pollsAvailability":
                        switch (information) {
                            case "none" -> {
                                pageChars.setPollsAvailability(PollsAvailabilityType.none);
                                current = "";
                            }
                            case "anonymous" -> {
                                pageChars.setPollsAvailability(PollsAvailabilityType.anonymous);
                                current = "";
                            }
                            case "auth" -> {
                                pageChars.setPollsAvailability(PollsAvailabilityType.auth);
                                current = "";
                            }
                            case "purchases" -> {
                                pageChars.setPollsAvailability(PollsAvailabilityType.purchases);
                                current = "";
                            }
                        }
                        break;
                }
            }
            if (event != XMLEvent.CHARACTERS) {
                if ((reader.getLocalName().equals("page") && event == XMLEvent.END_ELEMENT)) flag = false;
            }
        } while (flag);
        return new Page(id, title, pageType, requiredAuth, pageChars);
    }
}