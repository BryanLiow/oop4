package DAOs;

import DTOs.Movie;
import Exceptions.DaoException;
import java.util.List;

public interface MovieDaoInterface 
{
    public List<Movie> findAllMovies() throws DaoException;
    public Movie findMovieByMovieId(int mId) throws DaoException;
    public Movie findMovieByMovieTitle(String mTitle) throws DaoException;
    public void addMovie(String mTitle, String mGenre, String mDirector, String mRuntime, String mPlot, String mLocation, String mPoster, String mRating, String mFormat, String mYear, String mStarring, int mCopies, String mBarcode, String mUser_rating) throws DaoException;
    public void deleteMovie(int mId) throws DaoException;
    public void updateMovieUser_Rating(int mId, String mUser_Rating) throws DaoException;
    public List<Movie> jsonFindMovieByMovieTitle(String mTitle) throws DaoException;
    public List<Movie> findMoviesByDirector(String d) throws DaoException; 
}
