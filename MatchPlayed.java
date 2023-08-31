import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class MatchPlayed {
    public static void main(String[] args) {
        
        String matchPath = "/home/suresh/Downloads/matches.csv";
        String deliveriesPath = "/home/suresh/Downloads/deliveries.csv";

        // GroupDocs.Merger m = new Merger("");


        // Number of matches played per year of all the years in IPL.
        getAllMatchesPlayedByYear(matchPath);

        System.out.println("\n");
        //================================================================================================================

        // Number of matches won of all teams over all the years of IPL.
        countMatchesWonByTeam(matchPath);

        System.out.println("\n");
        //================================================================================================================

        //For the year 2016 get the extra runs conceded per team.
        extraRunForTheYear(deliveriesPath);


    }

    public static void getAllMatchesPlayedByYear(String path){
        
        HashMap<String,Integer> matchesByYear = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            br.readLine();

            while((line = br.readLine()) != null){

                String[] match = line.split(",");
                
                matchesByYear.put(match[1], matchesByYear.getOrDefault(match[1], 0)+1);
            }

            System.out.println("Matches Played");
            System.out.println("=================");

            for (String s : matchesByYear.keySet()){
                System.out.println("Year "+s+" : " + matchesByYear.get(s));
                System.out.println("-----------------");
        }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void countMatchesWonByTeam(String path) {

        HashMap<String, Integer> matchesWonByTeam = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                matchesWonByTeam.put(parts[10], matchesWonByTeam.getOrDefault(parts[10], 0) + 1);

            }

            System.out.println("Number Of Matches Won By Teams");
            System.out.println("=====================================================");

            for (String s : matchesWonByTeam.keySet()){
                System.out.println("Team Name : " + s + "  |  Wins : " + matchesWonByTeam.get(s));
                System.out.println("-----------------------------------------------------");
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //For the year 2016 get the extra runs conceded per team.
    public static void extraRunForTheYear(String path){
        HashMap<String, Integer> extraRun = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                
                // int year = 

                extraRun.put(parts[17], extraRun.getOrDefault(parts[17], 0) + 1);

            }

            System.out.println(extraRun);
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
