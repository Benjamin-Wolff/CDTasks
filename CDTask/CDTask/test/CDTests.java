import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import CD.MyCD;

/**
 * Tests for MyCD class.
 */
public class CDTests {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  public void testNullValues() {
    String[] arguments = null;
    MyCD mcd = new MyCD();
    mcd.main(arguments);
    assertEquals("Error: Invalid Inputs", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testOneValue() {
    String[] arguments = {"."};
    MyCD mcd = new MyCD();
    mcd.main(arguments);
    assertEquals("Error: Invalid Inputs", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testInvalidStart() {
    String[] arguments = {"./ab/gh", "."};
    MyCD mcd = new MyCD();
    mcd.main(arguments);
    assertEquals("Error: Invalid starting directory", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testTooManyPeriods() {
    String[] arguments = {"./ab/cd", "./...."};
    MyCD mcd = new MyCD();
    mcd.main(arguments);
    assertEquals("Error: Invalid result path", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testInvalidResultDirectory() {
    String[] arguments = {"./ab/", "./cd/ef/ij"};
    MyCD mcd = new MyCD();
    mcd.main(arguments);
    assertEquals("Error: Invalid result directory", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testValidValues() {

    String[] arguments = {"./ab", "./cd"};
    MyCD mcd = new MyCD();
    mcd.main(arguments);

    assertEquals("./ab/cd/", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testValidPeriodInputs() {

    String[] arguments = {"./ab/cd/ef/", "./..."};
    MyCD mcd = new MyCD();
    mcd.main(arguments);

    assertEquals("./ab/", outputStreamCaptor.toString().trim());
  }

  @Test
  public void testValidPeriodInputSwitch() {

    String[] arguments = {"./ab/cd/", "./../../gh"};
    MyCD mcd = new MyCD();
    mcd.main(arguments);

    assertEquals("./gh/", outputStreamCaptor.toString().trim());
  }

}