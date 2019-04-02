/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_count;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jörg
 */
public class MyQueueTest {
    MyQueue <String> instance = new MyQueue<>(3);
    public MyQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of put method, of class MyQueue.
     */
    @Test
    public void testPut() throws Exception {
        System.out.println("put");
        String newElement = "Element";
        instance.put(newElement);
        // TODO review the generated test code and remove the default call to fail.
        if(instance.data.size()!=1)
        {
            fail("Too much input!");
        }
    }

    /**
     * Test of get method, of class MyQueue.
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        String newElement = "Element";
        instance.put(newElement);
        String expResult = instance.get();
        String result = "Element";
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
