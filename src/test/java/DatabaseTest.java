import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private static Connection connection;

    @BeforeAll
    static void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        assertNotNull(connection);
    }

    @AfterAll
    static void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testDatabaseConnection() throws SQLException {
        assertFalse(connection.isClosed());
    }
}
 
