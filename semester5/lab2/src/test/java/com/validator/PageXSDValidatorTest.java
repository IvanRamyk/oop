package com.validator;

import junit.framework.TestCase;

public class PageXSDValidatorTest extends TestCase {

    public void testValidateXMLSchema() {
        assertTrue(PageXSDValidator.validateXMLSchema("site.xsd","site.xml"));

        assertTrue(PageXSDValidator.validateXMLSchema("site.xsd","test.xml"));

        assertFalse(PageXSDValidator.validateXMLSchema("site.xsd","test_false.xml"));
    }
}