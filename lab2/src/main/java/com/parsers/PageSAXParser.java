package com.parsers;

import com.model.Page;
import com.model.PageChars;
import com.model.PageType;
import com.model.PollsAvailabilityType;
import com.validator.PageXSDValidator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PageSAXParser {
    private static final ArrayList<Page> pages = new ArrayList<>();

    public static ArrayList<Page> parse(String XMLPath, String XSDPath) throws IOException, ParserConfigurationException, SAXException {
        if (XSDPath != null)
            if (!PageXSDValidator.validateXMLSchema(XSDPath, XMLPath))
                return new ArrayList<>();


        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(new File(XMLPath), new PageXMLHandler());

        return pages;
    }

    private static class PageXMLHandler extends DefaultHandler {
        private String lastElementName = null;
        private final PageChars pageChars = new PageChars(false, false, false, PollsAvailabilityType.none);
        int id = 0;
        String title = "";
        boolean requiredAuth = false;
        PageType pageType = PageType.advertising;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            lastElementName = qName;
            if (qName.equals("page")) {
                id = Integer.parseInt(attributes.getValue("id"));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();
            if (!information.isEmpty()) {
                switch (lastElementName) {
                    case "title":
                        title = information;
                        break;
                    case "authorize":
                        requiredAuth = information.equals("true");
                        break;
                    case "type":
                        switch (information) {
                            case "advertising" -> pageType = PageType.advertising;
                            case "mirror" -> pageType = PageType.mirror;
                            case "portal" -> pageType = PageType.portal;
                            case "news" -> pageType = PageType.news;
                        }
                        break;
                    case "archiveAvailability":
                        pageChars.setArchiveAvailability(information.equals("true"));
                        break;
                    case "emailAvailability":
                        pageChars.setEmailAvailability(information.equals("true"));
                        break;
                    case "newsAvailability":
                        pageChars.setNewsAvailability(information.equals("true"));
                        break;
                    case "pollsAvailability":
                        switch (information) {
                            case "none" -> pageChars.setPollsAvailability(PollsAvailabilityType.none);
                            case "anonymous" -> pageChars.setPollsAvailability(PollsAvailabilityType.anonymous);
                            case "auth" -> pageChars.setPollsAvailability(PollsAvailabilityType.auth);
                            case "purchases" -> pageChars.setPollsAvailability(PollsAvailabilityType.purchases);
                        }
                        break;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("page")) {
                lastElementName = null;
                PageChars temp = new PageChars(pageChars.isEmailAvailability(), pageChars.isNewsAvailability(),
                        pageChars.isArchiveAvailability(), pageChars.getPollsAvailability());
                pages.add(new Page(id, title, pageType, requiredAuth, temp));
            }
        }
    }

}

