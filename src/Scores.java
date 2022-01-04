import java.util.*;

public class Scores {

    private HashMap<String, Integer> scores;

    public Scores(){
        scores = new HashMap<>();
    }

    /***
     * Get current points of the team
     * @param team
     * @return
     */
    public int getTeamPoints(String team){
        return scores.get(team);
    }


    /***
     * Display league ranking  by soring using a MaxHeap
     * the  code is O(N) + O(nlogn)
     */
    public  void displayScores(){
        PriorityQueue<TeamScore> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        //Iterate through hashmap and add to maxHeap for sorting
        Iterator<Map.Entry<String,Integer>> iterator = scores.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Integer> temp = iterator.next();
            maxHeap.add(new TeamScore(temp.getKey(), temp.getValue()));
        }

        //Display contains of the heap
        int count=1;
        while (!maxHeap.isEmpty()) System.out.println(count++ + ". "+maxHeap.remove());

    }


    public void addTeams(String teamA, String teamB, int scoreA , int scoreB){
        //add teams to hashmap withrelevant scores
        if(scoreA>scoreB){
            int temp = scores.getOrDefault(teamA,0) + 3;
            scores.put(teamA,temp);
            scores.putIfAbsent(teamB,0);
        }else if(scoreA<scoreB){
            int temp = scores.getOrDefault(teamB,0) + 3;
            scores.put(teamB,temp);
            scores.putIfAbsent(teamA,0);
        }else{

            scores.put(teamA, scores.getOrDefault(teamA,0)+1);
            scores.put(teamB, scores.getOrDefault(teamB,0)+1);
        }
    }


    /***
     * @class TeamScore
     */
    class TeamScore implements  Comparable<TeamScore>{

        private String teamName ;
        private int score;
        TeamScore(String teamName, int score){
            this.teamName = teamName;
            this.score = score;
        }


        @Override
        public String toString() {

            if(this.score==1){
                return   teamName + "  " + score +" pt";
            }

            return   teamName + "  " + score+ " pts";
        }


        @Override
        public int compareTo(TeamScore o) {
            int temp  = Integer.compare(this.score,o.score);
            return (temp==0)? teamName.compareTo(o.teamName): temp;
        }
    }

}
