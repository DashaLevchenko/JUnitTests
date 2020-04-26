
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;


import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUnit5Tests {
    static final Logger logger = LogManager.getLogger();
    @AfterAll
   static void printLastMessage(){
        logger.info("See! These are my tests, cool, aren't they?");
    }
    @BeforeEach
    void printMessage() {
        logger.info("I'm test! JUnit 5 execute me!");
    }

    @Test
    @Order(1)
    public void transformKeepStringsShorterThant4Characters() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("My", "is", "Doe");
        assertEquals(expected, FilterCollection.transform(collection));
    }

    @Test
    @Order(2)
    public void transformShouldFlattenCollection() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> expected = asList("Viktor", "Farcic", "John", "Doe", "Third");
        assertEquals(expected, FlatCollection.transform(collection));
    }

    @Test
    @Order(3)
    public void partitionAdultsShouldSeparateKidsFromAdults() {
        Person sara = new Person("Sara", 4, "Norwegian");
        Person viktor = new Person("Viktor", 40, "Serbian");
        Person eva = new Person("Eva", 42, "Norwegian");
        List<Person> collection = asList(sara, eva, viktor);
        Map<String, List<Person>> result = Grouping.groupByNationality(collection);

        assertEquals(asList(sara, eva), result.get("Norwegian"));
        assertEquals(Collections.singletonList(viktor), result.get("Serbian"));
    }
}
