package BusinessObjects;

import DTOs.Movie;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try
        {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket, and open new socket

            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort());
            
            System.out.println("Client: This Client is running and has connected to the server");
            
            System.out.println("[Commands: \"findMoviesWatchedByUserId\", \"insertWatchedEntry\", \"getMovieById\", \"findMovieByMovieTitle\", \"addMovie\", \"deleteMovie\", \"updateMovieUser_Rating\" , \"RecommendMovieWatchedByUserId\"]");
            System.out.println("Please enter your command: ");
            
            String command = in.nextLine();
       
            OutputStream os = socket.getOutputStream();
            
            PrintWriter socketWriter = new PrintWriter(os, true);
            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());
            String input;

            if (command.startsWith("findMoviesWatchedByUserId")) // we expect the server to return a time (in milliseconds)
            {
                input = socketReader.nextLine();
                System.out.println("Client: Response from server: \"" + input + "\"");
            }
            else if (command.startsWith("insertWatchedEntry")) // we expect the server to return a time (in milliseconds)
            {
                input = socketReader.nextLine();
                System.out.println("Client: Response from server: \"" + input + "\"");
            }
            else if (command.startsWith("insertWatchedEntry")) // we expect the server to return a time (in milliseconds)
            {
                input = socketReader.nextLine();
                System.out.println("Client: Response from server: \"" + input + "\"");
            }
            else if (command.startsWith("getMovieById")) // we expect the server to return a time (in milliseconds)
            {
                input = socketReader.nextLine();
                System.out.println("Client: Response from server: \"" + input + "\"");
            }
            else if (command.startsWith("findMovieByMovieTitle"))
            {
                input = socketReader.nextLine();
                System.out.println("Client: Response from server: \"" + input + "\"");
            }
            else if (command.startsWith("addMovie"))
            {
                input = socketReader.nextLine();
                System.out.println("Client: Response from server: \"" + input + "\"");
            }   
            else if (command.startsWith("deleteMovie"))
            {
                input = socketReader.nextLine();
                System.out.println("Client: Response from server: \"" + input + "\"");
            }
            else if(command.startsWith("RecommendMovieWatchedByUserId"))  
            {
                input = socketReader.nextLine();
                System.out.println("Client: Response from server: \"" + input + "\"");
            } 
            else
            {
                input = socketReader.nextLine();
                System.out.println("Client: Response from server: \"" + input + "\"");
            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e)
        {
            System.out.println("Client message: IOException: " + e);
        }
    }
        
}
