package DAOs;

import DTOs.Movie;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySqlMovieDao extends MySqlDao implements MovieDaoInterface
{
    @Override
    public List<Movie> findAllMovies() throws DaoException 
    {
    	Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList<>();
        
        try 
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM MOVIES";
            ps = con.prepareStatement(query);
            
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                int copies = rs.getInt("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                movies.add(m);
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("findAllMovies() " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("findAllMovies() " + e.getMessage());
            }
        }
        return movies;
    }

    @Override
    public Movie findMovieByMovieId(int mId) throws DaoException 
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM MOVIES WHERE ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, mId);
            
            rs = ps.executeQuery();
            if (rs.next()) 
            {
            	int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                int copies = rs.getInt("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                
                m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("findMovieByMovieId() " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("findMovieByMovieId() " + e.getMessage());
            }
        }
        return m;     // u may be null 
    }  
    
    @Override
    public List<Movie> jsonFindMovieByMovieTitle(String mTitle) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        List<Movie> movies = new ArrayList<>();
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM MOVIES WHERE TITLE LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + mTitle + "%");
            
            rs = ps.executeQuery();
            while (rs.next()) 
            {
            	int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                int copies = rs.getInt("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                
                m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
            
            movies.add(m);
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("jsonFindMovieByMovieTitle() " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("jsonFindMovieByMovieTitle() " + e.getMessage());
            }
        }
        return movies;
    }
    
    @Override
    public Movie findMovieByMovieTitle(String mTitle) throws DaoException 
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM MOVIES WHERE TITLE = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, mTitle);
            
            rs = ps.executeQuery();
            if (rs.next()) 
            {
            	int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                int copies = rs.getInt("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                
                m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("findMovieByMovieTitle() " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("findMovieByMovieTitle() " + e.getMessage());
            }
        }
        return m;     // u may be null 
    }  
    
    @Override
    public void addMovie(String mTitle, String mGenre, String mDirector, String mRuntime, String mPlot, String mLocation, String mPoster, String mRating, String mFormat, String mYear, String mStarring, int mCopies, String mBarcode, String mUser_rating) throws DaoException 
    {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = this.getConnection();
            
            String query = "INSERT INTO MOVIES (title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);

            ps.setString(1, mTitle);
            ps.setString(2, mGenre);
            ps.setString(3, mDirector);
            ps.setString(4, mRuntime);
            ps.setString(5, mPlot);
            ps.setString(6, mLocation);
            ps.setString(7, mPoster);
            ps.setString(8, mRating);
            ps.setString(9, mFormat);
            ps.setString(10, mYear);
            ps.setString(11, mStarring);
            ps.setInt(12, mCopies);
            ps.setString(13, mBarcode);
            ps.setString(14, mUser_rating);
            
            ps.executeUpdate();
            con.close();
        } 
        catch (SQLException e) 
        {
            System.err.println("Got an exception");
            System.err.println(e.getMessage());
        }        
    }  
    
    @Override
    public void deleteMovie(int mId) throws DaoException 
    {
        Connection con = null;
        PreparedStatement ps = null;     

        try {
            con = this.getConnection();
            
            String query = "DELETE FROM MOVIES WHERE ID= ?;";
            ps = con.prepareStatement(query);
            ps.setInt(1, mId);
            
            ps.executeUpdate();
            con.close();
        } 
        catch (SQLException e) 
        {
            System.err.println("Got an exception");
            System.err.println(e.getMessage());
        }        
    }  
    
    @Override
    public void updateMovieUser_Rating(int mId, String mUser_Rating) throws DaoException 
    {
        Connection con = null;
        PreparedStatement ps = null;     

        try {
            con = this.getConnection();
            
            String query = "UPDATE MOVIES SET user_rating = ? WHERE id = ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, mUser_Rating);
            ps.setInt(2, mId);
            
            ps.executeUpdate();
            con.close();
        } 
        catch (SQLException e) 
        {
            System.err.println("Got an exception");
            System.err.println(e.getMessage());
        }        
    }
    
    @Override
    public List<Movie> findMoviesByDirector(String d) throws DaoException 
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        List<Movie> movies = new ArrayList<>();
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM MOVIES WHERE DIRECTOR = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, d);
            
            rs = ps.executeQuery();
            while (rs.next()) 
            {
            	int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                int copies = rs.getInt("COPIES");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                
                m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                movies.add(m);
            }
        } 
        catch (SQLException e) 
        {
            throw new DaoException("findMoviesByDirector() " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    freeConnection(con);
                }
            } 
            catch (SQLException e) 
            {
                throw new DaoException("findMoviesByDirector() " + e.getMessage());
            }
        }
        return movies;
    }    
    
}