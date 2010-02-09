package com.beans;

import junit.framework.TestCase;

public class HomeBeanTests extends TestCase {

    public void testGetObjectNameSearch() throws Exception{
    	HomeBean hb = new HomeBean();
    	hb.setObjectNameSearch("Testing Home Bean method");
    	assertEquals("Testing Home Bean method", hb.getObjectNameSearch());
    	

    }
}