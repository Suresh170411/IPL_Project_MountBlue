import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MatchPlayed {
    public static void main(String[] args) {
        
        // Paths for the CSV files
        String matchPath = "/home/suresh/Downloads/matches.csv";
        String deliveriesPath = "/home/suresh/Downloads/deliveries.csv";


        // Number of matches played per year of all the years in IPL.
        getAllMatchesPlayedByYear(matchPath);

        System.out.println("\n");
        //================================================================================================================

        // Number of matches won of all teams over all the years of IPL.
        countMatchesWonByTeam(matchPath);

        System.out.println("\n");
        //================================================================================================================

        //For the year 2016 get the extra runs conceded per team.
        extraRunForTheYear(matchPath , deliveriesPath);

        System.out.println("\n");
        //================================================================================================================
        
        economicBowler(matchPath, deliveriesPath);

    }

    /**
     * The function `getAllMatchesPlayedByYear` reads a CSV file containing match data, counts the
     * number of matches played in each year, and prints the results.
     * 
     * @param path The path parameter is a string that represents the file path of the CSV file
     * containing the matches data.
     */
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


    /**
     * The function `countMatchesWonByTeam` reads a CSV file containing match data, counts the number
     * of matches won by each team, and prints the results.
     * 
     * @param path The path parameter is a string that represents the file path of the input file. This
     * file should contain data about matches, with each line representing a match and each field
     * separated by commas. The 11th field in each line represents the team that won the match.
     */
    public static void countMatchesWonByTeam(String path) {

        HashMap<String, Integer> matchesWonByTeam = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] matches = line.split(",");
                matchesWonByTeam.put(matches[10], matchesWonByTeam.getOrDefault(matches[10], 0) + 1);

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

    
    /**
     * The function `extraRunForTheYear` reads data from two CSV files (`matches.csv` and
     * `deliveries.csv`), filters the matches for the year 2016, and calculates the total extra runs
     * for that year.
     * 
     * @param matchPath The file path of the matches.csv file. This file contains information about
     * cricket matches, including the match ID and the year of the match.
     * @param deliveriesPath The path to the deliveries.csv file, which contains information about each
     * delivery in a match. This file should have columns like match_id, inning, batting_team,
     * bowling_team, etc.
     */
    public static void extraRunForTheYear(String matchPath , String deliveriesPath){

        // This section will read the matches.csv file

        List<Integer> matchIdList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(matchPath))) {
            
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] matches = line.split(",");
                
                int year = Integer.parseInt(matches[1]);

                // Mapping year of match file with match_id of deliveries file and adding ids in the matchIdList
                if (year == 2016){
                    matchIdList.add(Integer.parseInt(matches[0]));
                }

            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        // This section will read the deliveries.csv file

        List<Integer> runsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(deliveriesPath))) {
            
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] deliveries = line.split(",");

                for (int i=0; i<matchIdList.size(); i++){

                    if (Integer.parseInt(deliveries[0]) == matchIdList.get(i)){  // This will make sure only selected items store in runs list based on match id
                        runsList.add(Integer.parseInt(deliveries[17]));
                    }

                }

            }
            
            // This will print the total extra runs for the selected year

            System.out.println("Extra Runs Per Year");
            System.out.println("=============================================");
            System.out.println("Total extra runs for the year 2016 is : "+runsList.stream().mapToInt(i -> i).sum());

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void economicBowler(String path1, String path2){
        
        List<Integer> matchIdList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
            
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] matches = line.split(",");
                
                int year = Integer.parseInt(matches[1]);

                // Mapping year of match file with match_id of deliveries file and adding ids in the matchIdList
                if (year == 2015){
                    matchIdList.add(Integer.parseInt(matches[0]));
                }

            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String> bowlerList = new ArrayList<>();

        List<Integer> totalRunsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
            
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] deliveries = line.split(",");

                for (int i=0; i<matchIdList.size(); i++){

                    if (Integer.parseInt(deliveries[0]) == matchIdList.get(i)){  // This will make sure only selected items store in runs list based on match id
                        bowlerList.add(deliveries[8]);
                        totalRunsList.add(Integer.parseInt(deliveries[17]));
                    }

                }

            }
            
            // This will print the total extra runs for the selected year

            System.out.println("Extra Runs Per Year");
            System.out.println("=============================================");
            

            HashMap<String, Integer> bowlerWithTotalRuns = new HashMap<>();

            for (int i=0; i<bowlerList.size(); i++){
                bowlerWithTotalRuns.put(bowlerList.get(i), bowlerWithTotalRuns.getOrDefault(bowlerList.get(i),0)+totalRunsList.get(i));
            }

            HashMap<String, Integer> overs = new HashMap<>();

            for (String s : bowlerList){
                overs.put(s, overs.getOrDefault(s, 0)+1);
            }

            bowlerList.clear();
            
            for (String s : overs.keySet()){
                bowlerList.add(s);
            }

            // System.out.println(bowlerList);

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");


            List<Integer> bowlerTotalRunsList = new ArrayList<>(bowlerWithTotalRuns.values());
            List<Integer> bowlerTotalOversList = new ArrayList<>(overs.values());

            List<Double> averages = new ArrayList<>();

            for (int i=0; i<bowlerTotalRunsList.size(); i++){
                averages.add((double)bowlerTotalRunsList.get(i)/bowlerTotalOversList.get(i));
            }

            HashMap<String, Double> players = new HashMap<>();

            for (int i=0; i<averages.size(); i++){
                players.put(bowlerList.get(i), averages.get(i));
            }

            System.out.println(players);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
