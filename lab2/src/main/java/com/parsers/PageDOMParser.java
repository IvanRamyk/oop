package com.parsers;

import com.model.Page;
import com.model.PageChars;
import com.model.PageType;
import com.model.PollsAvailabilityType;
import com.validator.PageXSDValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PageDOMParser {

    /// returns list of pages from xml file
    /// returns empty list if xml is not valid
    public static ArrayList<Page> parse(String XMLpath, String XSDpath) throws IOException, SAXException, ParserConfigurationException {
        if (XSDpath != null)
            if (!PageXSDValidator.validateXMLSchema(XSDpath, XMLpath))
                return new ArrayList<>();

        ArrayList<Page> pages = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(XMLpath));

        NodeList pageNodeList = document.getDocumentElement().getElementsByTagName("page");

        for(int i = 0; i < pageNodeList.getLength(); ++i) {
            Node pageNode = pageNodeList.item(i);
            pages.add(nodeToPage(pageNode));
        }
        return pages;
    }

    // returns Page from Node
    private static Page nodeToPage(Node pageNode) {
        NodeList child = pageNode.getChildNodes();
        int id = Integer.parseInt(pageNode.getAttributes().getNamedItem("id").getNodeValue());
        String title = "";
        boolean requiredAuth = false;
        PageType pageType = PageType.advertising;
        PageChars pageChars = null;

        for(int i = 0; i < child.getLength(); ++i)
            switch (child.item(i).getNodeName()) {
                case "title":
                    title = child.item(i).getTextContent();
                    break;
                case "authorize":
                    requiredAuth = child.item(i).getTextContent().equals("true");
                    break;
                case "chars":
                    pageChars = nodeToChars(child.item(i));
                    break;
                case "type":
                    switch (child.item(i).getTextContent()) {
                        case "advertising" -> pageType = PageType.advertising;
                        case "mirror" -> pageType = PageType.mirror;
                        case "portal" -> pageType = PageType.portal;
                        case "news" -> pageType = PageType.news;
                    }
                    break;
            }
        return new Page(id, title, pageType, requiredAuth, pageChars);
    }

    private static PageChars nodeToChars(Node node) {
        PageChars pageChars = new PageChars(false, false, false, PollsAvailabilityType.none);
        NodeList child = node.getChildNodes();
        for(int i = 0; i < child.getLength(); ++i)
            switch (child.item(i).getNodeName()) {
                case "archiveAvailability":
                    pageChars.setArchiveAvailability(child.item(i).getTextContent().equals("true"));
                    break;
                case "emailAvailability":
                    pageChars.setEmailAvailability(child.item(i).getTextContent().equals("true"));
                    break;
                case "newsAvailability":
                    pageChars.setNewsAvailability(child.item(i).getTextContent().equals("true"));
                    break;
                case "pollsAvailability":
                    switch (child.item(i).getTextContent()) {
                        case "none" -> pageChars.setPollsAvailability(PollsAvailabilityType.none);
                        case "anonymous" -> pageChars.setPollsAvailability(PollsAvailabilityType.anonymous);
                        case "auth" -> pageChars.setPollsAvailability(PollsAvailabilityType.auth);
                        case "purchases" -> pageChars.setPollsAvailability(PollsAvailabilityType.purchases);
                    }
                    break;
            }
        return pageChars;
    }
}
