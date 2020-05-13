import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AnagramsTest {

    private Set<String> dictionary;
    private Anagrams anagrams;

    @BeforeEach
    public void setupThis() throws FileNotFoundException {
        dictionary = AnagramMain.getDictionary();
        anagrams = new Anagrams(dictionary);
    }
    @Test
    public void emptyConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Anagrams(null);
        });
    }

    @Test
    public void getWordsTest() throws FileNotFoundException {

        // Test for IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            anagrams.getWords(null);
        });

        // Test for proper Set creation
        Set<String> actual = anagrams.getWords("Barbara Bush");
        Set<String> expected = Set.of("abash", "aura", "bar", "barb", "brush", "bus", "hub", "rub", "shrub", "sub");

        assertEquals(expected, actual);
    }

    @Test
    public void printTest() {

        // Test for IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            anagrams.getWords(null);
        });

        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        String expected = getExpectedString("hairbrush.txt");
        // action
        anagrams.print("hairbrush");

        // assertion
        assertEquals(expected, bos.toString());

        // undo the binding in System
        System.setOut(originalOut);

    }

    private String getExpectedString(String filename) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

}