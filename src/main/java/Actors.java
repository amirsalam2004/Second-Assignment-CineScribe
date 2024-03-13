import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class Actors {
    public static final String API_KEY = "JFBvTTeuMdHyTL1+db88MA==aa4RM9amNQDus5Ad";   // TODO --> add your api key about Actors here
    double netWorth;
    Boolean isAlive;
    String dateOfDeath;
    String actorData;
    ArrayList<String> occupationList;

    public Actors(String dateOfDeath, boolean isAlive){
        //TODO --> (Write a proper constructor using the get_from_api functions)
        this.dateOfDeath=dateOfDeath;
        this.isAlive=isAlive;
        occupationList=new ArrayList<>();
    }
    public Actors(){
        occupationList=new ArrayList<>();
    }
    public Actors(String actorName){
        this.actorData=getActorData(actorName);
        occupationList=new ArrayList<>();
    }
    @SuppressWarnings({"deprecation"})
    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name="+
                    name.replace(" ", "+")+"&apikey="+API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Double getNetWorthViaApi(String actorsInfoJson){
        //TODO --> (This function must return the "NetWorth")
        JSONArray getinformation=new JSONArray(actorsInfoJson);
        JSONObject getNetWorth=(JSONObject) getinformation.get(0);
        if(getNetWorth.has("net_worth"))
            this.netWorth=getNetWorth.getDouble("net_worth");
        else
            this.netWorth=1000;
        return this.netWorth;
    }

    public boolean isAlive(String actorsInfoJson) {
        //TODO --> (If your chosen actor is alive it must return true otherwise it must return false)
        JSONArray getinformation = new JSONArray(actorsInfoJson);
        JSONObject getIsAlive = (JSONObject) getinformation.get(0);
        if (getIsAlive.has("is_alive")) {
            this.isAlive = getIsAlive.getBoolean("is_alive");
            return this.isAlive;
        }
        return true;
    }

    public String getDateOfDeathViaApi(String actorsInfoJson){
        //TODO --> (If your chosen actor is deceased it must return the date of death)  -->
        JSONArray getinformation=new JSONArray(actorsInfoJson);
        JSONObject getDateOfDeath=(JSONObject) getinformation.get(0);
        if(getDateOfDeath.has("death")){
            this.dateOfDeath=getDateOfDeath.getString("death");
            return this.dateOfDeath;
        }
        return "There is no information to display";
    }
    public ArrayList<String> getOccupationViaApi(String actorsInfoJson){
        //created by myself
        this.occupationList.clear();
        JSONArray getinformation=new JSONArray(actorsInfoJson);
        JSONObject getOccupation=(JSONObject) getinformation.get(0);
        if(getOccupation.has("occupation")) {
            JSONArray occupationArrays = getOccupation.getJSONArray("occupation");
            for (int i = 0; i < occupationArrays.length(); i++)
                this.occupationList.add(occupationArrays.getString(i));
        }
        return this.occupationList;
    }
}