# Movie and Actors Information Viewer

This Java Swing application retrieve the information about movies and actors and displays it to the user.

## Description
Our program receives information through API in the form of a Json string and extracts information from it and it uses Java Swing to create a graphic environment.
This project includes 3 classes:
**Movie class** This class retrieve movie information using the OMDB API and  fetch details such as IMDB votes, ratings, actor lists, and runtime for a given movie title.
This class includes these methods:
  1. getMovieData(String title): Retrieves data for the specified movie.
  2. getImdbVotesViaApi(String moviesInfoJson): Returns the IMDB votes as an integer.
  3. getRatingViaApi(String moviesInfoJson): Returns the IMDB rating.
  4. getActorListViaApi(String movieInfoJson): Populates the actorsList with the list of actors.
  5. getRuntimeViaApi(String movieInfoJson): Returns the movie runtime.
This class has 2 constructors with different inputs.

**Actor class** If we want to get more information about the movie actors, we use the Actor class.
this class retrieve information about actors using the API-Ninjas API and fetch details such as net worth, alive status, date of death, and occupations for a given actor.
This class includes these methods:
   1. getActorData(String name): Retrieves data for the specified actor.
   2. getNetWorthViaApi(String actorsInfoJson): Returns the net worth of the actor
   3. isAlive(String actorsInfoJson): Returns true if the actor is alive; otherwise, returns false.
   4. getDateOfDeathViaApi(String actorsInfoJson): Returns the date of death if the actor is deceased.
   5. getOccupationViaApi(String actorsInfoJson): Returns a list of occupations for the actor.
This class has 3 constructors with different inputs.


**Main class** In this class, the program is executed and the graphic display of information is managed.
This class includes these methods:
   1. main(String[] args): Main entry point for the application.
   2. chooseInfoOption():choose the information option.
   3. runMenu(): Runs the main menu loop for movie and actor information retrieval.
jOptionPane() class is used for graphic display.

## Challenges
1. This was my first experience in working with Json strings and specially I had trouble extracting JsonArrays.

2. This was my first experience using the java swing library and I had to research its methods.

3. We didn't have keys "is_live" and "death" in the Jason string related to some actors and I managed this issue.

4. Sometimes I had problems casting types to each other (for example there was commas in a string in one of the functions and it could not be directly converted to a number)

5. Due to the incompleteness of the constructors, at first I could not use the Main class to run the program,so I had to open a separate file to test the methods.

6. The type of output of the getNetWorthViaApi() was written incorrectly and I corrected it

### Dependencies

* Java Development Kit (JDK) installed
* Package manager (Gradle) installed
* import javax.swing
* import org.json

### Installing
1. Clone the repository
2. Set Gradle as the package manager
3. Run the project

### Executing program
1. Launch the application, and a welcome message will appear.
2. Enter the name of the movie you are interested in. To exit, enter 1.
3. Choose from the information options:
      * Actors
      * IMDb Votes
      * Rating
      * Run Time
      * Return (to go back to movie selection)
4. Explore additional information about actors if you select the "Actors" option.
5. Exit the program by entering 1 during movie selection.
* At each stage you can go back to the previous stage

## Authors

ex. Amir Hossein Ziaeifar (amirsalam2004@gmail.com)

## Version History

* 0.1
    * Initial Release


## Acknowledgments
   Thanks!!!