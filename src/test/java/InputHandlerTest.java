
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.InputHandler;

class InputHandlerTest {

    private final InputStream systemIn = System.in;
    private InputHandler inputHandler;

    @AfterEach
    void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    void testEnterInt_ValidInputWithinOptionSize() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("2\n".getBytes()));
        inputHandler = new InputHandler(scanner);

        int result = inputHandler.enterInt(5, true, true);
        assertEquals(2, result);
    }

    @Test
    void testEnterInt_BackOption() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("0\n".getBytes()));
        inputHandler = new InputHandler(scanner);

        int result = inputHandler.enterInt(5, true, true);
        assertEquals(0, result);
    }

    @Test
    void testEnterInt_MenuOption() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("99\n".getBytes()));
        inputHandler = new InputHandler(scanner);

        int result = inputHandler.enterInt(5, true, true);
        assertEquals(99, result);
    }

    @Test
    void testEnterInt_InvalidThenValid() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("10\n2\n".getBytes()));
        inputHandler = new InputHandler(scanner);

        int result = inputHandler.enterInt(5, true, true);
        assertEquals(2, result, "Should retry and accept valid input after invalid");
    }

    @Test
    void testGetAlphaNumString_ValidInput() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("User123\n".getBytes()));
        inputHandler = new InputHandler(scanner);

        String result = inputHandler.getAlphaNumString();
        assertEquals("User123", result);
    }

    @Test
    void testGetAlphaNumString_InvalidThenValid() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("!@#\nabc123\n".getBytes()));
        inputHandler = new InputHandler(scanner);

        String result = inputHandler.getAlphaNumString();
        assertEquals("abc123", result);
    }

    @Test
    void testGetAlphaNumString_GoBackOption() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("-1\n".getBytes()));
        inputHandler = new InputHandler(scanner);

        String result = inputHandler.getAlphaNumString();
        assertEquals("-1", result);
    }

    @Test
    void testGetLetterOrHint() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("h\n".getBytes()));
        inputHandler = new InputHandler(scanner);

        String result = inputHandler.getLetterOrHint();
        assertEquals("h", result);
    }

    @Test
    void testGetStringInt() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("1234\n".getBytes()));
        inputHandler = new InputHandler(scanner);

        String result = inputHandler.getStringInt();
        assertEquals("1234", result);
    }

    @Test
    void testWaitForAnyKey() throws IOException {
        // Simulate pressing "Enter" key
        System.setIn(new ByteArrayInputStream("\n".getBytes()));
        inputHandler = new InputHandler(new Scanner(System.in));

        assertDoesNotThrow(() -> inputHandler.waitForAnyKey());
    }
}

