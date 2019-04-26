package DAOs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import DTOs.Movie;
import Exceptions.DaoException;
import com.google.gson.Gson;
import com.sun.istack.internal.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import java.util.logging.*;

public class Server
{

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
    private static final Level LOG_LEVEL = Level.INFO;
    private FileHandler logFile = null;

    static DAOs.MovieDaoInterface IMovieDao = new DAOs.MySqlMovieDao();
    static DAOs.WatchedDaoInterface IWatchedDao = new DAOs.MySqlWatchedDao();

    public static void main(String[] args)
    {
        LOGGER.setLevel(LOG_LEVEL);
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            logFile = new FileHandler("Server.log", true);  // true = append to end of file on restart
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        logFile.setFormatter(new SimpleFormatter());  // simple formatting (just text)
        LOGGER.addHandler(logFile);

        LOGGER.info("Server starting ...");

        try
        {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection, 
                // and open a new socket to communicate with the client

                LOGGER.info("A Client has connected from: " + socket.getInetAddress());

                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            LOGGER.warning("IOException caught");
            
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {

        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing 

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            HashMap<String, Movie> cache = new HashMap<>();
            HashMap<String, List<Movie>> Listcache = new HashMap<>();
            String message;
            
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    LOGGER.info("Command received from client: " + message);
                    String[] token = message.split(" ");
                    if (message.startsWith("findMoviesWatchedByUserId"))
                    {
                        try
                        {
                            int id = Integer.parseInt(token[1]);
                            List<Integer> movieIds = IWatchedDao.findMoviesWatchedByUserId(id);
                            List<Movie> movies = IWatchedDao.getMoviesByMovieIds(movieIds);
                            if (movies != null)
                            {
                                socketWriter.println(movies);
                            }
                            else
                            {
                                socketWriter.println("Movies watched by " + token[1] + " not found!");
                            }
                        } catch (DaoException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (message.startsWith("insertWatchedEntry"))
                    {
                        try
                        {
                            int uId = Integer.parseInt(token[1]);
                            int mId = Integer.parseInt(token[2]);

                            IWatchedDao.insertWatchedEntry(uId, mId);
                            socketWriter.println("Movie(id) watched by " + token[1] + " added: " + token[2]);
                            cache.clear();
                        } catch (DaoException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (message.startsWith("getMovieById"))
                    {
                        if (!cache.containsKey("getMovieById"))
                        {
                            try
                            {
                                int id = Integer.parseInt(token[1]);
                                Movie movie = IMovieDao.findMovieByMovieId(id);
                                if (movie != null)
                                {
                                    socketWriter.println("Movie found : " + toStringMovie(movie));
                                }
                                else
                                {
                                    socketWriter.println("Movie with that id NOT FOUND");
                                }
                                cache.put(message, movie);

                            } catch (DaoException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            socketWriter.println("Movie found : " + cache.get(message));
                        }
                    }
                    else if (message.startsWith("findMovieByMovieTitle"))
                    {
                        if (!cache.containsKey("getMovieById"))
                        {
                            try
                            {
                                Movie movie = IMovieDao.findMovieByMovieTitle(token[1]);
                                if (movie != null)
                                {
                                    socketWriter.println("Movie found : " + toStringMovie(movie));
                                }
                                else
                                {
                                    socketWriter.println("Movie with that title NOT FOUND");
                                }
                                cache.put(message, movie);
                            } catch (DaoException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            socketWriter.println("Movie found : " + cache.get(message));
                        }
                    }
                    else if (message.startsWith("addMovie"))
                    {
                        try
                        {
                            Gson gson = new Gson();
                            Movie movie = gson.fromJson(token[1], Movie.class);
                            String title = movie.getTitle();
                            String genre = movie.getGenre();
                            String director = movie.getDirector();
                            String runtime = movie.getRuntime();
                            String plot = movie.getPlot();
                            String location = movie.getLocation();
                            String poster = movie.getPoster();
                            String rating = movie.getRating();
                            String format = movie.getFormat();
                            String year = movie.getYear();
                            String starring = movie.getStarring();
                            int copies = movie.getCopies();
                            String barcode = movie.getBarcode();
                            String user_rating = movie.getUser_rating();

                            IMovieDao.addMovie(title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                            if (movie != null)
                            {
                                socketWriter.println("Movie found : " + toStringMovie(movie));
                            }
                            else
                            {
                                socketWriter.println("Movie with that title NOT FOUND");
                            }
                            cache.clear();
                        } catch (DaoException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (message.startsWith("deleteMovie"))
                    {
                        try
                        {
                            int id = Integer.parseInt(token[1]);
                            IMovieDao.deleteMovie(id);
                            socketWriter.println("Movie with id " + id + " has been deleted.");
                            cache.clear();
                        } catch (DaoException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (message.startsWith("RecommendMovieWatchedByUserId"))
                    {
                        try
                        {
                            int id = Integer.parseInt(token[1]);
                            List<Integer> movieIds = IWatchedDao.findMoviesWatchedByUserId(id);
                            List<Movie> movies = IWatchedDao.getMoviesByMovieIds(movieIds);
                            List<Movie> recommend = IWatchedDao.recommendMovie(movies);

                            Server obj = new Server();

                            int numberOfElements = 2;

                            socketWriter.println(getRandomElement(recommend, numberOfElements));

                        } catch (DaoException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }
                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

    public static String toStringMovie(Movie movie)
    {
        String jsonString = " { \" movies \" : [";

        jsonString += "{"
                + "\"id\":" + movie.getId() + ","
                + "\"title\":\"" + movie.getTitle() + "\","
                + "\"genre\":\"" + movie.getGenre() + "\","
                + "\"director\":\"" + movie.getDirector() + "\","
                + "\"runtime\":\"" + movie.getRuntime() + "\","
                + "\"plot\":\"" + movie.getPlot() + "\","
                + "\"location\":\"" + movie.getLocation() + "\","
                + "\"poster\":\"" + movie.getPoster() + "\","
                + "\"rating\":\"" + movie.getRating() + "\","
                + "\"format\":\"" + movie.getFormat() + "\","
                + "\"year\":" + movie.getYear() + ","
                + "\"starring\":\"" + movie.getStarring() + "\","
                + "\"copies\":" + movie.getCopies() + ","
                + "\"barcode\":" + movie.getBarcode() + ","
                + "\"userRating\":" + movie.getUser_rating()
                + "}";

        jsonString += "]"
                + "}\n";

        return jsonString;
    }

    public static String toStringMovies(List<Movie> m)
    {
        String jsonString = " { \" movies \" : [";

        for (Movie movie : m)
        {
            jsonString += "{"
                    + "\"id\":" + movie.getId() + ","
                    + "\"title\":\"" + movie.getTitle() + "\","
                    + "\"genre\":\"" + movie.getGenre() + "\","
                    + "\"director\":\"" + movie.getDirector() + "\","
                    + "\"runtime\":\"" + movie.getRuntime() + "\","
                    + "\"plot\":\"" + movie.getPlot() + "\","
                    + "\"location\":\"" + movie.getLocation() + "\","
                    + "\"poster\":\"" + movie.getPoster() + "\","
                    + "\"rating\":\"" + movie.getRating() + "\","
                    + "\"format\":\"" + movie.getFormat() + "\","
                    + "\"year\":" + movie.getYear() + ","
                    + "\"starring\":\"" + movie.getStarring() + "\","
                    + "\"copies\":" + movie.getCopies() + ","
                    + "\"barcode\":" + movie.getBarcode() + ","
                    + "\"userRating\":" + movie.getUser_rating()
                    + "}";

            jsonString += "]"
                    + "}\n";
        }
        return jsonString;
    }

    public static List<Movie> getRandomElement(List<Movie> list,
            int totalItems)
    {
        Random rand = new Random();

        // create a temporary list for storing 
        // selected element 
        List<Movie> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++)
        {

            // take a raundom index between 0 to size  
            // of given List 
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list 
            newList.add(list.get(randomIndex));

            // Remove selected element from orginal list 
            list.remove(randomIndex);
        }
        return newList;
    }

}
