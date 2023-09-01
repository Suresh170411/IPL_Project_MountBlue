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

    // For the year 2016 get the extra runs conceded per team.
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

    

}
