import org.json.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
public class Movie {
    public static final String API_KEY = "fa9e05cf";   // TODO --> add your api key about Movie here
    int ImdbVotes;
    ArrayList<String> actorsList=new ArrayList<>();
    String rating;

//    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
//        //TODO --> (Write a proper constructor using the get_from_api functions)
//    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) throws IOException {
        URL url = new URL("https://www.omdbapi.com/?t="+title+"&apikey="+API_KEY);
        URLConnection Url = url.openConnection();
        Url.setRequestProperty("Authorization", "Key" + API_KEY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine())!=null) {
            stringBuilder.append(line);
        }
        reader.close();
        //handle an error if the chosen movie is not found
        return stringBuilder.toString();
    }
    public int getImdbVotesViaApi(String moviesInfoJson){
        //TODO --> (This function must change and return the "ImdbVotes" as an Integer)
        // NOTICE :: you are not permitted to convert this function to return a String instead of an int !!!
        JSONObject getImdbVotes=new JSONObject(moviesInfoJson);
        String ImdbVotes=getImdbVotes.getString("imdbVotes");
        ImdbVotes=ImdbVotes.replace(",","");
        this.ImdbVotes= Integer.parseInt(ImdbVotes);
        return (this.ImdbVotes);
    }

    public String getRatingViaApi(String moviesInfoJson){
        //TODO --> (This function must return the rating in the "Ratings" part
        // where the source is "Internet Movie Database")  -->
        JSONObject rate=new JSONObject(moviesInfoJson);
        JSONArray ratingArrays=rate.getJSONArray("Ratings");
        JSONObject IMDBrating=(JSONObject) ratingArrays.get(0);
        this.rating=IMDBrating.getString("Value");
        return this.rating;
    }

    public void getActorListViaApi(String movieInfoJson){
        //TODO --> (This function must return the "Actors" in actorsList)
        JSONObject getActorList=new JSONObject(movieInfoJson);
        String actors=getActorList.getString("Actors");
        String[] list=actors.split(",");
        for(int i=0;i< list.length;i++){
            actorsList.add(list[i]);
        }
    }
}