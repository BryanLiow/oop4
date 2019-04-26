package DAOs;

import DTOs.Movie;
import Exceptions.DaoException;
import java.util.List;


public interface WatchedDaoInterface
{
    public List<Integer> findMoviesWatchedByUserId(int user_id) throws DaoException;
    public void insertWatchedEntry(int uId, int mId) throws DaoException;
    public List<Movie> getMoviesByMovieIds(List<Integer> mId) throws DaoException;
    public List<Movie> recommendMovie(List<Movie> movies) throws DaoException;
}
