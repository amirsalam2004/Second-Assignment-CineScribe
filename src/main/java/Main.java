import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // TODO --> complete main function
        JOptionPane.showMessageDialog(null,"Hello\nWelcome to our program\n" +
                "We provide you with your favorite information about movies and actors");
        try {
            runMenu
                    ();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String chooseInfoOption(){
        Object[] options={"actors","IMDB vots","Rating","Run time","BACK"};
        Object selection=JOptionPane.showInputDialog(null,"Choose an option",
                "Information Options",JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
        return(selection.toString());
    }
    private static String chooseActor(ArrayList<String> actorsList){
        actorsList.add("BACK");
        Object[] actors=actorsList.toArray();
        actorsList.remove(actorsList.size()-1);
        Object selection=JOptionPane.showInputDialog(null,"Actors list : "+actorsList+
                        "\n\nIf you want more information about one of the actors, select from the list"
                +"\nAlso, select \"BACK\" to return to the previous window",
                "Actors List",JOptionPane.INFORMATION_MESSAGE,null,actors,actors[0]);
        return selection.toString();
    }
    public static void runMenu() throws IOException {
        // TODO
        Movie movie=new Movie();
        String movieName;
        while(true) {
            movieName = JOptionPane.showInputDialog(null,
                    "Enter the name of the movie\nYou can exit the program by entering 1",
                    "Movie selection", JOptionPane.QUESTION_MESSAGE);
            if(!movieName.equals("1")) {
                String selectedOption = "0";
                String movieInfo = movie.getMovieData(movieName);
                if(movieInfo.equals("{\"Response\":\"False\",\"Error\":\"Movie not found!\"}")) {
                    JOptionPane.showMessageDialog(null, "There are no movies with this name");
                    continue;
                }
                else
                    while (true) {
                        String choose=chooseInfoOption();
                        if (choose.equals("IMDB vots")) {
                            JOptionPane.showMessageDialog(null, "IMDB vots : " + movie.getImdbVotesViaApi(movieInfo));
                        } else if (choose.equals("Run time")) {
                            JOptionPane.showMessageDialog(null, "Run time : " + movie.getRuntimeViaApi(movieInfo));
                        } else if (choose.equals("Rating")) {
                            JOptionPane.showMessageDialog(null, "Rating : " + movie.getRatingViaApi(movieInfo));
                        } else if (choose.equals("actors")) {
                            movie.getActorListViaApi(movieInfo);
                            while (true) {
                                String input =chooseActor(movie.actorsList);
                                System.out.println(input);
                                if (input.equals("BACK")) {
                                    break;
                                } else {
                                    Actors actors=new Actors(input.substring(1));
                                    String actorInfo = actors.actorData;
                                    double netWorth=actors.getNetWorthViaApi(actorInfo)/1000;
                                    JOptionPane.showMessageDialog(null, "actor name : " + input
                                            + "\nOccupations : " + actors.getOccupationViaApi(actorInfo)
                                            + "\nNet worth : " + Double.toString(netWorth).replace(".0","")+"000  $"
                                            + "\nIs live : " + actors.isAlive(actorInfo)
                                            + "\nDeath date : " + actors.getDateOfDeathViaApi(actorInfo));
                                }
                            }
                        } else if (choose.equals("BACK")) {
                            break;
                        }
                    }
            }
            else
                break;
        }
    }
}