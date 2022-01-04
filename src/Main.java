

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    /**
     * Read data from the file line by line
     * @param filename
     * @return
     */
    public static Scores readFile(String filename){

        Scores scores = new Scores();
        Scanner scanner;
        try{
            scanner = new Scanner(new File(filename));//Open file and read contents
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] teams = line.split(",");
                teams[0] =teams[0].strip();
                teams[1] =teams[1].strip();

                int scoreA = getScore(teams[0]);
                int scoreB = getScore(teams[1]);

                String teamA = getTeamName(teams[0],scoreA);
                String teamB = getTeamName(teams[1],scoreB);
                scores.addTeams(teamA,teamB,scoreA,scoreB);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return scores;
    }

    public static String getTeamName(String team ,int score){
        if(score == 0){
            return team.substring(0,team.length()-2);
        }
        return team.substring(0, team.length()- ((int)(Math.log10(score))+2)).strip() ;
    }

    /***
     * Return the score from the team
     * @param team
     * @return
     */
    public static   int getScore(String team){

        String temp[] = team.split(" ");
        return  Integer.parseInt(temp[temp.length-1]);
    }

    /***
     * Read inputs from terminal line by line
     * @return
     */
    public static  Scores readTerminal(){

        Scanner scanner =new Scanner(System.in);
        Scores scores = new Scores();
        String line = "";
        while ((line = scanner.nextLine()) != ""){

            String[] teams = line.split(",");
            teams[0] =teams[0].strip();
            teams[1] =teams[1].strip();

            //Get team scores
            int scoreA = getScore(teams[0]);
            int scoreB = getScore(teams[1]);

            //Get Team names
            String teamA = getTeamName(teams[0],scoreA);
            String teamB = getTeamName(teams[1],scoreB);
            scores.addTeams(teamA,teamB,scoreA,scoreB);

        }
        return  scores;
    }


    public static void main(String args[]){
        Scores score = null;

        //Check if user inputs filename
        if(args.length==0){
            score = readTerminal();
        }else{
            score = readFile(args[0]);
        }

        score.displayScores();
    }


}
