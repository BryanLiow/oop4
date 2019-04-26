package DAOs;

import DTOs.Movie;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlWatchedDao extends MySqlDao implements WatchedDaoInterface
{

    @Override
    public List<Integer> findMoviesWatchedByUserId(int user_id) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> movies = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT movie_id FROM watched WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("movie_id");

                Integer mId = new Integer(id);
                movies.add(mId);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findMoviesWatchedByUserId() " + e.getMessage());
        } finally
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
            } catch (SQLException e)
            {
                throw new DaoException("findMoviesWatchedByUserId() " + e.getMessage());
            }
        }
        return movies;
    }

    @Override
    public void insertWatchedEntry(int uId, int mId) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        try
        {
            con = this.getConnection();

            String query = "INSERT INTO WATCHED (username, movie_watched, time) VALUES (?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, uId);
            ps.setInt(2, mId);
            ps.setTimestamp(3, date);

            ps.executeUpdate();
            con.close();
        } catch (SQLException e)
        {
            System.err.println("Got an exception");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Movie> getMoviesByMovieIds(List<Integer> mId) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        List<Movie> list = new ArrayList<>();

        try
        {
            for (int i = 1; i <= mId.size(); i++)
            {
                int id = mId.get(i - 1);
                con = this.getConnection();

                String query = "SELECT * FROM MOVIES WHERE ID = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);

                //Using a PreparedStatement to execute SQL...
                rs = ps.executeQuery();
                while (rs.next())
                {
                    int movieId = rs.getInt("ID");
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

                    m = new Movie(movieId, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                    list.add(m);
                }
            }
        } catch (SQLException e)
        {
            throw new DaoException("findMoviesWatchedByUserId() " + e.getMessage());
        } finally
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
            } catch (SQLException e)
            {
                throw new DaoException("findMoviesWatchedByUserId() " + e.getMessage());
            }
        }

        return list;
    }

    @Override
    public List<Movie> recommendMovie(List<Movie> movies) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        List<Movie> list = new ArrayList<>();
        List<String> allGenres = new ArrayList<>();
        String mostWatchedGenre = null;

        try
        {
            for (int i = 1; i < movies.size(); i++)
            {
                String genres = movies.get(i-0).getGenre();

                String[] token = genres.split("\\s*,\\s*");

                for (int a = 0; a < token.length; a++)
                {
                    allGenres.add(token[a]);
                }
            }
            mostWatchedGenre = mostCommonElement(allGenres);

            con = this.getConnection();

            String query = "SELECT * FROM MOVIES WHERE GENRE LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + mostWatchedGenre + "%");

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int movieId = rs.getInt("ID");
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

                m = new Movie(movieId, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                list.add(m);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findMoviesWatchedByUserId() " + e.getMessage());
        } finally
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
            } catch (SQLException e)
            {
                throw new DaoException("findMoviesWatchedByUserId() " + e.getMessage());
            }
        }
        return list;

    }

    public String mostCommonElement(List<String> list)
    {

        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < list.size(); i++)
        {

            Integer frequency = map.get(list.get(i));
            if (frequency == null)
            {
                map.put(list.get(i), 1);
            }
            else
            {
                map.put(list.get(i), frequency + 1);
            }
        }

        String mostCommonKey = null;
        int maxValue = -1;
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {

            if (entry.getValue() > maxValue)
            {
                mostCommonKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }

        return mostCommonKey;
    }
}
