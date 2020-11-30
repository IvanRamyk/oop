package com.parsers;

import com.model.Page;
import com.model.PageChars;
import com.model.PageType;
import com.model.PollsAvailabilityType;
import junit.framework.TestCase;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;

public class PageSAXParserTest extends TestCase {

    public void testParse() throws IOException, XMLStreamException, ParserConfigurationException, SAXException {
        assertEquals(PageSAXParser.parse("test_false.xml", "site.xsd").size(), 0);
        ArrayList<Page> pages = new ArrayList<>();
        pages.add(new Page(4, "Home", PageType.news, true, new PageChars(true, true, true, PollsAvailabilityType.anonymous)));

        var pageList1 = PageStAXParser.parse("test.xml", "site.xsd");
        assertEquals(pageList1.size(), 1);
        assertEquals(pageList1.get(0), pages.get(0));

        var pageList2 = PageSAXParser.parse("site.xml", "site.xsd");
        pages.add(new Page(1, "Shop", PageType.portal, false, new PageChars(false, false, false, PollsAvailabilityType.none)));
        pages.add(new Page(10, "Contacts", PageType.portal, true, new PageChars(false, false, false, PollsAvailabilityType.none)));
        pages.add(new Page(22, "About us", PageType.advertising, true, new PageChars(true, false, false, PollsAvailabilityType.auth)));
        assertEquals(pageList2.size(), 4);
        assertEquals(pageList2.get(0), pages.get(0));
        assertEquals(pageList2.get(1), pages.get(1));
        assertEquals(pageList2.get(2), pages.get(2));
        assertEquals(pageList2.get(3), pages.get(3));
    }
}