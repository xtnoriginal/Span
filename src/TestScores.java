import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestScores {


    @Test
    public void getScoreTest(){
        assertEquals(4,Main.getScore("Chelsea FC 4"));
        assertEquals(10000,Main.getScore("Chelsea FC 10000"));
        assertEquals(0,Main.getScore("Bob 0"));
    }

    @Test
    public void getTeamTest(){
        assertEquals("Chelsea FC", Main.getTeamName("Chelsea FC 4", 4));
        assertEquals("Bob", Main.getTeamName("Bob 0", 1));
        assertEquals("Bob", Main.getTeamName("Bob 10000", 10000));
    }

    @Test
    public  void getCheckScores(){
        Scores scores = new Scores();
        scores.addTeams("Chelsea FC","Liverpool", 2,2 );
        assertEquals(1,scores.getTeamPoints("Chelsea FC"));

        scores.addTeams("Asernal","Wolves", 3,0 );
        assertEquals(3,scores.getTeamPoints("Asernal"));

        scores.addTeams("Chelsea FC","Wolves", 2,1 );
        assertEquals(0,scores.getTeamPoints("Wolves"));
        assertEquals(4,scores.getTeamPoints("Chelsea FC"));
        assertEquals(1,scores.getTeamPoints("Liverpool"));
    }
}
