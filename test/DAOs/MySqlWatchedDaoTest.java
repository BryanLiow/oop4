/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Movie;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class MySqlWatchedDaoTest
{
    
    public MySqlWatchedDaoTest()
    {
    }

    /**
     * Test of mostCommonElement method, of class MySqlWatchedDao.
     */
    @Test
    public void testMostCommonElement()
    {
        System.out.println("mostCommonElement");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        list.add("c");
        list.add("d");
        
        MySqlWatchedDao instance = new MySqlWatchedDao();
        String expResult = "c";
        String result = instance.mostCommonElement(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
