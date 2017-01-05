package br.com.authentication.business.component.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class EncryptionComponentImplTest {

    @InjectMocks
    private EncryptionComponentImpl encryptionComponent;
    
    
    public EncryptionComponentImplTest(){}
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConvertToHex(){
    	String senha = "123456";
    	String result = encryptionComponent.convertToHex(senha);
    	
    	assertEquals("7C4A8D09CA3762AF61E59520943DC26494F8941B", result);
    }
    
}
