/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Movie;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class MySqlMovieDaoTest
{
    
    public MySqlMovieDaoTest()
    {
    }

    /**
     * Test of findMovieByMovieId method, of class MySqlMovieDao.
     */
    @Test
    public void testFindMovieByMovieId() throws Exception
    {
        System.out.println("findMovieByMovieId");
        int mId = 14;
        MySqlMovieDao instance = new MySqlMovieDao();
        Movie expResult = new Movie(14, "iron man", "Marvel, Action, Adventure, Sci-Fi", "Jon Favreau", "126 min", "Tony Stark. Genius, billionaire, playboy, philanthropist. Son of legendary inventor and weapons contractor Howard Stark. When Tony Stark is assigned to give a weapons presentation to an Iraqi unit led by Lt. Col. James Rhodes, he's given a ride on enemy lines. That ride ends badly when Stark's Humvee that he's riding in is attacked by enemy combatants. He survives - barely - with a chest full of shrapnel and a car battery attached to his heart. In order to survive he comes up with a way to miniaturize the battery and figures out that the battery can power something else. Thus Iron Man is born. He uses the primitive device to escape from the cave in Iraq. Once back home, he then begins work on perfecting the Iron Man suit. But the man who was put in charge of Stark Industries has plans of his own to take over Tony's technology for other matters.", "", "","PG-13", "DVD", "2008", "Robert Downey Jr., Terrence Howard, Jeff Bridges, Gwyneth Paltrow", 3, "", "8.8");
        Movie result = instance.findMovieByMovieId(mId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }


    /**
     * Test of findMovieByMovieTitle method, of class MySqlMovieDao.
     */
    @Test
    public void testFindMovieByMovieTitle() throws Exception
    {
        System.out.println("findMovieByMovieTitle");
        String mTitle = "iron man";
        MySqlMovieDao instance = new MySqlMovieDao();
        Movie expResult = new Movie(14, "iron man", "Marvel, Action, Adventure, Sci-Fi", "Jon Favreau", "126 min", "Tony Stark. Genius, billionaire, playboy, philanthropist. Son of legendary inventor and weapons contractor Howard Stark. When Tony Stark is assigned to give a weapons presentation to an Iraqi unit led by Lt. Col. James Rhodes, he's given a ride on enemy lines. That ride ends badly when Stark's Humvee that he's riding in is attacked by enemy combatants. He survives - barely - with a chest full of shrapnel and a car battery attached to his heart. In order to survive he comes up with a way to miniaturize the battery and figures out that the battery can power something else. Thus Iron Man is born. He uses the primitive device to escape from the cave in Iraq. Once back home, he then begins work on perfecting the Iron Man suit. But the man who was put in charge of Stark Industries has plans of his own to take over Tony's technology for other matters.", "", "","PG-13", "DVD", "2008", "Robert Downey Jr., Terrence Howard, Jeff Bridges, Gwyneth Paltrow", 3, "", "8.8");
        Movie result = instance.findMovieByMovieTitle(mTitle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
