import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName) {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies() {
    return movies;
  }

  public void menu() {
    String menuOption = "";

    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");

    while (!menuOption.equals("q")) {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();

      if (menuOption.equals("t")) {
        searchTitles();
      } else if (menuOption.equals("c")) {
        searchCast();
      } else if (menuOption.equals("k")) {
        searchKeywords();
      } else if (menuOption.equals("g")) {
        listGenres();
      } else if (menuOption.equals("r")) {
        listHighestRated();
      } else if (menuOption.equals("h")) {
        listHighestRevenue();
      } else if (menuOption.equals("q")) {
        System.out.println("Goodbye!");
      } else {
        System.out.println("Invalid choice!");
      }
    }
  }

  private void importMovieList(String fileName) {
    try {
      movies = new ArrayList<Movie>();
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();

      while ((line = bufferedReader.readLine()) != null) {
        // get data from the columns in the current row and split into an array
        String[] movieFromCSV = line.split(",");

        /* TASK 1: FINISH THE CODE BELOW */
        // using the movieFromCSV array,
        // obtain the title, cast, director, tagline,
        // keywords, overview, runtime (int), genres,
        // user rating (double), year (int), and revenue (int)


        // create a Movie object with the row data:


        // add the Movie to movies:

        String title = movieFromCSV[0];
        String cast = movieFromCSV[1];
        String director = movieFromCSV[2];
        String tagline = movieFromCSV[3];
        String keywords = movieFromCSV[4];
        String overview = movieFromCSV[5];
        int runtime = Integer.parseInt(movieFromCSV[6]);
        String genres = movieFromCSV[7];
        Double userRating = Double.parseDouble(movieFromCSV[8]);
        int year = Integer.parseInt(movieFromCSV[9]);
        int revenue = Integer.parseInt(movieFromCSV[10]);

        Movie tempMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);

        movies.add(tempMovie);
      }
      bufferedReader.close();
    } catch(IOException exception) {
      System.out.println("Unable to access " + exception.getMessage());
    }
  }

  private void searchTitles() {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++) {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();

      if (movieTitle.indexOf(searchTerm) != -1) {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    if (results.size() > 0) {
      // sort the results by title
      sortResults(results);

      // now, display them all to the user
      for (int i = 0; i < results.size(); i++) {
        String title = results.get(i).getTitle();

        // this will print index 0 as choice 1 in the results list; better for user!
        int choiceNum = i + 1;
        System.out.println("" + choiceNum + ". " + title);
      }

      System.out.println("Which movie would you like to learn more about?");
      System.out.print("Enter number: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      Movie selectedMovie = results.get(choice - 1);
      displayMovieInfo(selectedMovie);
      System.out.println("\n ** Press Enter to Return to Main Menu **");
      scanner.nextLine();
    } else {
      System.out.println("\nNo movie titles match that search term!");
      System.out.println("** Press Enter to Return to Main Menu **");
      scanner.nextLine();
    }
  }

  private void sortResults(ArrayList<Movie> listToSort) {
    for (int j = 1; j < listToSort.size(); j++) {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();

      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0) {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }
  
  private void displayMovieInfo(Movie movie) {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }

  private void searchKeywords() {
    System.out.print("Enter a keyword search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++) {
      String actors = movies.get(i).getCast();
      System.out.println(actors);
      actors = actors.toLowerCase();

      if (actors.indexOf(searchTerm) != -1) {
        //add the Movie object to the results list
        results.add(movies.get(i));
      }
    }

    if (results.size() > 0) {
      // sort the results by title
      sortResults(results);

      // now, display them all to the user
      for (int i = 0; i < results.size(); i++) {
        String title = results.get(i).getTitle();

        // this will print index 0 as choice 1 in the results list; better for user!
        int choiceNum = i + 1;
        System.out.println("" + choiceNum + ". " + title);
      }

      System.out.println("Which movie would you like to learn more about?");
      System.out.print("Enter number: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      Movie selectedMovie = results.get(choice - 1);
      displayMovieInfo(selectedMovie);
      System.out.println("\n ** Press Enter to Return to Main Menu **");
      scanner.nextLine();
    } else {
      System.out.println("\nNo movie titles match that keyword term!");
      System.out.println("** Press Enter to Return to Main Menu **");
      scanner.nextLine();
    }
  }


  private void searchCast() {
    System.out.print("Enter a person to search for (first or last name): ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    String searchTerm1 = searchTerm.toLowerCase();
    String searchTerm2 = searchTerm.toUpperCase();
    String searchTerm3 = searchTerm.substring(0, 1).toUpperCase() + searchTerm.substring(1).toLowerCase();

    // arraylist to hold search results
    ArrayList<String> results = new ArrayList<String>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++) {
      String actors = movies.get(i).getCast();
      String[] actorList = actors.split("\\|");
      for (int z = 0; z < actorList.length; z++) {
        if (!results.contains(actorList[z]) && ((actorList[z].indexOf(searchTerm1) != -1) || (actorList[z].indexOf(searchTerm2) != -1) || (actorList[z].indexOf(searchTerm3) != -1))) {
          results.add(actorList[z]);
        }
      }
    }
    sortCast(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++) {
      String actor = results.get(i);

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + actor);
    }

    System.out.println("Which would you like to see all movies for?");
    System.out.print("Enter number: ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    choice--;

    ArrayList<Movie> movieList = new ArrayList<Movie>();
    for (int i = 0; i < movies.size(); i++) {
      if (movies.get(i).getCast().contains(results.get(choice))) {
        movieList.add(movies.get(i));
      }
    }

    for (int i = 0; i < movieList.size(); i++) {
      Movie movie = movieList.get(i);
      sortResults(movieList);

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + movie.getTitle());
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");
    int choice2 = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = movieList.get(choice2 - 1);
    displayMovieInfo(selectedMovie);
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void sortCast(ArrayList<String> listToSort) {
    for (int j = 1; j < listToSort.size(); j++) {
      String temp = listToSort.get(j);

      int possibleIndex = j;
      while (possibleIndex > 0 && temp.compareTo(listToSort.get(possibleIndex - 1)) < 0) {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }

  private void listGenres() {
    /* TASK 5: IMPLEMENT ME */
  }
  
  private void listHighestRated() {
    /* TASK 6: IMPLEMENT ME */
  }
  
  private void listHighestRevenue() {
    /* TASK 6: IMPLEMENT ME */
  }
}