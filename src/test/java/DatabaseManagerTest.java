

import static org.junit.jupiter.api.Assertions.*;

import com.example.DatabaseHandling.DatabaseManager;
import com.example.UserHandling.Score;
import com.example.UserHandling.User;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

class DatabaseManagerTest {

    private DatabaseManager dbManager;

    @BeforeEach
    void setUp() {
        // Use in-memory database for testing
        dbManager = new DatabaseManager(":memory:");
    }

    @Test
    void testAddPlayerAndGetPlayers() {
        boolean added = dbManager.addPlayer("testUser");
        assertTrue(added, "Player should be added successfully");

        ArrayList<String> players = dbManager.getPlayers();
        assertEquals(1, players.size(), "There should be one player in the database");
        assertEquals("testUser", players.get(0), "Player username should match");
    }

    @Test
    void testAddDuplicatePlayer() {
        dbManager.addPlayer("duplicateUser");
        boolean secondAdd = dbManager.addPlayer("duplicateUser");

        assertFalse(secondAdd, "Adding a duplicate username should fail");

        ArrayList<String> players = dbManager.getPlayers();
        assertEquals(1, players.size(), "There should still be only one player");
    }

    @Test
    void testLoadPlayer() {
        dbManager.addPlayer("loadUser");

        User loadedUser = dbManager.loadPlayer("loadUser");
        assertNotNull(loadedUser, "User should be loaded successfully");
        assertEquals("loadUser", loadedUser.getUsername(), "Loaded username should match");
        assertEquals(0, loadedUser.getPoints(), "Default points should be 0");
        assertNotNull(loadedUser.getCurrScore(), "User should have a Score object");
        assertEquals(0, loadedUser.getCurrScore().getScoreValue(), "Default score should be 0");
    }

    @Test
    void testDeletePlayer() {
        dbManager.addPlayer("deleteUser");
        dbManager.deletePlayer("deleteUser");

        ArrayList<String> players = dbManager.getPlayers();
        assertTrue(players.isEmpty(), "Player list should be empty after deletion");

        User user = dbManager.loadPlayer("deleteUser");
        assertNull(user, "Deleted user should not be found");
    }

    @Test
    void testAddScoreEntry() {
        dbManager.addPlayer("scoreUser");
        User user = dbManager.loadPlayer("scoreUser");

        user.setCurrScore(new Score(150, new Date(System.currentTimeMillis())));
        dbManager.addScoreEntry(user);

        // Leaderboard should now include this score
        List<String> leaderboard = dbManager.getTopFiveLeaderboard();
        assertFalse(leaderboard.isEmpty(), "Leaderboard should not be empty");
        assertTrue(leaderboard.get(0).contains("scoreUser"), "Leaderboard should contain scoreUser");
    }

    @Test
    void testUpdatePoints() {
        dbManager.addPlayer("pointsUser");
        User user = dbManager.loadPlayer("pointsUser");

        user.setPoints(500);
        dbManager.updatePoints(user);

        User updatedUser = dbManager.loadPlayer("pointsUser");
        assertEquals(500, updatedUser.getPoints(), "User points should be updated to 500");
    }

    @Test
    void testGetTopFiveLeaderboardOrder() {
        dbManager.addPlayer("player1");
        dbManager.addPlayer("player2");
        dbManager.addPlayer("player3");

        User p1 = dbManager.loadPlayer("player1");
        User p2 = dbManager.loadPlayer("player2");
        User p3 = dbManager.loadPlayer("player3");

        p1.setCurrScore(new Score(100, new Date(System.currentTimeMillis())));
        p2.setCurrScore(new Score(300, new Date(System.currentTimeMillis())));
        p3.setCurrScore(new Score(200, new Date(System.currentTimeMillis())));

        dbManager.addScoreEntry(p1);
        dbManager.addScoreEntry(p2);
        dbManager.addScoreEntry(p3);

        List<String> leaderboard = dbManager.getTopFiveLeaderboard();

        assertEquals(3, leaderboard.size(), "Leaderboard should have three entries");
        assertTrue(leaderboard.get(0).contains("player2"), "Top player should be player2 with highest score");
        assertTrue(leaderboard.get(1).contains("player3"), "Second should be player3");
        assertTrue(leaderboard.get(2).contains("player1"), "Third should be player1");
    }
}

