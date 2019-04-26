package DTOs;

import Exceptions.DaoException;
import java.util.Objects;

/**
 * Feb 2019
 *
 * This POJO (Plain Old Java Object) is called the Data Transfer Object (DTO).
 * (or, alternatively, the Model Object or the Value Object). It is used to
 * transfer data between the DAO and the Business Objects. Here, it represents a
 * row of data from the Movie database table. The DAO fills a Movie object (DTO)
 * with data retrieved from the resultSet and passes the Movie object to the
 * Business Layer. Collections of DTOs( e.g. ArrayList<User> ) may also be
 * passed between the Data Access Layer (Daos) and the Business Layer objects.
 */
public class Movie
{

    private int id;
    private String title;
    private String genre;
    private String director;
    private String runtime;
    private String plot;
    private String location;
    private String poster;
    private String rating;
    private String format;
    private String year;
    private String starring;
    private int copies;
    private String barcode;
    private String user_rating;

//    private int id;
//    private String firstName;
//    private String lastName;
//    private String username;
//    private String password;
//
//    public Movie(int userId, String firstName, String lastName, String username, String password) 
//    {
//        this.id = userId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        this.password = password;
//    }
//    
//    public Movie( String firstName, String lastName, String username, String password) 
//    {
//        this.id = 0;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        this.password = password;
//    }
//    
//    public Movie() 
//    {
//    }
//
//    public int getId() 
//    {
//        return id;
//    }
//
//    public void setId(int id) 
//    {
//        this.id = id;
//    }
//
//    public String getFirstName() 
//    {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) 
//    {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() 
//    {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) 
//    {
//        this.lastName = lastName;
//    }
//
//    public String getUsername() 
//    {
//        return username;
//    }
//
//    public void setUsername(String username) 
//    {
//        this.username = username;
//    }
//
//    public String getPassword() 
//    {
//        return password;
//    }
//
//    public void setPassword(String password) 
//    {
//        this.password = password;
//    }	
//
//    @Override
//    public String toString()
//    {
//        return "Movie{" + "id=" + id + ", firstName=" + firstName + ", lastName=" +
//                lastName + ", username=" + username + ", password=" + password + '}';
//    }
    public Movie(int id, String title, String genre, String director, String runtime, String plot, String location, String poster, String rating, String format, String year, String starring, int copies, String barcode, String user_rating)
    {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = copies;
        this.barcode = barcode;
        this.user_rating = user_rating;
    }

    public Movie(String title, String genre, String director, String runtime, String plot, String location, String poster, String rating, String format, String year, String starring, String barcode, String user_rating)
    {
        this.id = 0;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = 0;
        this.barcode = barcode;
        this.user_rating = user_rating;
    }

    public Movie()
    {

    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getDirector()
    {
        return director;
    }

    public String getRuntime()
    {
        return runtime;
    }

    public String getPlot()
    {
        return plot;
    }

    public String getLocation()
    {
        return location;
    }

    public String getPoster()
    {
        return poster;
    }

    public String getRating()
    {
        return rating;
    }

    public String getFormat()
    {
        return format;
    }

    public String getYear()
    {
        return year;
    }

    public String getStarring()
    {
        return starring;
    }

    public int getCopies()
    {
        return copies;
    }

    public String getBarcode()
    {
        return barcode;
    }

    public String getUser_rating()
    {
        return user_rating;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public void setRuntime(String runtime)
    {
        this.runtime = runtime;
    }

    public void setPlot(String plot)
    {
        this.plot = plot;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setPoster(String poster)
    {
        this.poster = poster;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public void setStarring(String starring)
    {
        this.starring = starring;
    }

    public void setCopies(int copies)
    {
        this.copies = copies;
    }

    public void setBarcode(String barcode)
    {
        this.barcode = barcode;
    }

    public void setUser_rating(String user_rating)
    {
        this.user_rating = user_rating;
    }

    @Override
    public String toString()
    {
        return "Movie{" + "id=" + id + ", title=" + title + ", genre=" + genre + ", director=" + director + ", runtime=" + runtime + ", plot=" + plot + ", location=" + location + ", poster=" + poster + ", rating=" + rating + ", format=" + format + ", year=" + year + ", starring=" + starring + ", copies=" + copies + ", barcode=" + barcode + ", user_rating=" + user_rating + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.title);
        hash = 73 * hash + Objects.hashCode(this.genre);
        hash = 73 * hash + Objects.hashCode(this.director);
        hash = 73 * hash + Objects.hashCode(this.runtime);
        hash = 73 * hash + Objects.hashCode(this.plot);
        hash = 73 * hash + Objects.hashCode(this.location);
        hash = 73 * hash + Objects.hashCode(this.poster);
        hash = 73 * hash + Objects.hashCode(this.rating);
        hash = 73 * hash + Objects.hashCode(this.format);
        hash = 73 * hash + Objects.hashCode(this.year);
        hash = 73 * hash + Objects.hashCode(this.starring);
        hash = 73 * hash + this.copies;
        hash = 73 * hash + Objects.hashCode(this.barcode);
        hash = 73 * hash + Objects.hashCode(this.user_rating);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.id != other.id)
        {
            return false;
        }
        if (this.copies != other.copies)
        {
            return false;
        }
        if (!Objects.equals(this.title, other.title))
        {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre))
        {
            return false;
        }
        if (!Objects.equals(this.director, other.director))
        {
            return false;
        }
        if (!Objects.equals(this.runtime, other.runtime))
        {
            return false;
        }
        if (!Objects.equals(this.plot, other.plot))
        {
            return false;
        }
        if (!Objects.equals(this.location, other.location))
        {
            return false;
        }
        if (!Objects.equals(this.poster, other.poster))
        {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating))
        {
            return false;
        }
        if (!Objects.equals(this.format, other.format))
        {
            return false;
        }
        if (!Objects.equals(this.year, other.year))
        {
            return false;
        }
        if (!Objects.equals(this.starring, other.starring))
        {
            return false;
        }
        if (!Objects.equals(this.barcode, other.barcode))
        {
            return false;
        }
        if (!Objects.equals(this.user_rating, other.user_rating))
        {
            return false;
        }
        return true;
    }
    
    

    public String toJson() throws DaoException
    {
        String json = " { \"id\" : " + id + ","
                + "\"title\" : \"" + title + "\","
                + "\"genre\" : \"" + genre + "\","
                + "\"director\" : \"" + director + "\","
                + "\"runtime\" : \"" + runtime + "\","
                + "\"plot\" : \"" + plot + "\","
                + "\"location\" :\" " + location + "\","
                + "\"poster\" : \"" + poster + "\","
                + "\"rating\" : \"" + rating + "\","
                + "\"format\" : \"" + format + "\","
                + "\"year\" : \"" + year + "\","
                + "\"starring\" : \"" + starring + "\","
                + "\"copies\" : " + copies + ","
                + "\"barcode\" : \"" + barcode + "\","
                + "\"user_rating\" : \"" + user_rating
                + "\"}";

        return json;
    }

}
