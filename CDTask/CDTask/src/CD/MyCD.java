package CD;
import java.io.File;
import java.util.Stack;

/**
 * Class representing the CD unix command to be used at the command line.
 *    Returns the name of the directory indicated, or an error if that directory does not exist.
 */
public class MyCD {

  /**
   * Main function that takes in the current and target directories as args. Checks for validity,
   *      and outputs the name of the new directory if valid, or an error message if invalid.
   */
  public static void main(String[] args) {
    File currentDirectory, nextDirectory;
    String inputCurrDir, inputNextDir, temp;
    boolean validInputs, errorFound;
    Stack<String> resultDir = new Stack<String>();
    String[] splitCurrent, splitNext;
    StringBuilder result = new StringBuilder();

    // initialing necessary values
    inputCurrDir = "";
    inputNextDir = "";
    validInputs = true;
    errorFound = false;

    // checks if the inputs given are valid (2 strings for current and next directory)
    if (args == null || args.length < 2) {
      System.out.println("Error: Invalid Inputs");
      validInputs = false;
    }

    else {
      // checks that input for current directory is valid.
      inputCurrDir = args[0];
      inputNextDir = args[1];

      currentDirectory = new File(inputCurrDir);

      if (!(currentDirectory.exists() && currentDirectory.isDirectory())) {
        System.out.println("Error: Invalid starting directory");
        validInputs = false;
      }
    }

    // only runs if the two inputs are valid as determined above
    if (validInputs) {

      // splits the strings by / to separate directory names or periods
      splitCurrent = inputCurrDir.split("/");
      splitNext = inputNextDir.split("/");

      // runs if the resulting directory involves the starting directory
      if (inputNextDir.startsWith(".")) {

        // iterates through current location to set up Stack
        for (int i = 1; i < splitCurrent.length; i++) {
          temp = splitCurrent[i];

          // does nothing if the String at the index is empty (which occurs from multiple /'s)
          if (!(temp.equals(""))) {

            // checks for values that start with .
            if (temp.startsWith(".")) {

              // pops the last item in stack if the value equals ..
              if (temp.startsWith("..")) {
                // calls an error if the directory cannot go back farther
                if (resultDir.empty()) {
                  System.out.println("Error: Invalid result path");
                  i = splitCurrent.length;
                  errorFound = true;
                }
                else {
                  resultDir.pop();

                  // continues with possibility that there are more than 2 periods in a row
                  splitCurrent[i] = temp.substring(1);
                  i--;
                }
              }

              // if the temp is not . it is pushed, otherwise nothing happens
              // (since . remains in same directory)
              else if (!(temp.equals("."))) {
                resultDir.push(temp);
              }
            }

            // otherwise, simply pushes next String to the Stack
            else {
              resultDir.push(temp);
            }
          }
        }
      }

      // sends out error message if path does not start with a . or /
      else if (!(inputNextDir.startsWith("/"))) {
        System.out.println("Error: Invalid result path");
        errorFound = true;
      }

      // skips if an error was found in the creation of the Stack
      if (!errorFound) {

        // iterates through destination to add to Stack
        for (int i = 0; i < splitNext.length; i++) {
          temp = splitNext[i];

          // does nothing if the String at the index is empty (which occurs from multiple /'s)
          if (!(temp.equals(""))) {

            // checks for values that start with .
            if (temp.startsWith(".")) {

              // pops the last item in stack if the value equals ..
              if (temp.startsWith("..")) {
                // calls an error if the directory cannot go back farther
                if (resultDir.empty()) {
                  System.out.println("Error: Invalid result path");
                  i = splitNext.length;
                  errorFound = true;
                }
                else {
                  resultDir.pop();

                  // continues with possibility that there are more than 2 periods in a row
                  splitNext[i] = temp.substring(1);
                  i--;
                }
              }

              // if the temp is not . it is pushed, otherwise nothing happens
              // (since . remains in same directory)
              else if (!(temp.equals("."))) {
                resultDir.push(temp);
              }
            }
            // otherwise, simply pushes next String to the Stack
            else {
              resultDir.push(temp);
            }
          }
        }
      }

      if (!errorFound) {
        // adds / to the end
        result.append("/");

        // creates StringBuilder representing the path of the resulting directory
        while (!(resultDir.empty())) {
          result.insert(0, resultDir.pop());
          result.insert(0, "/");
        }

        // add . to beginning if necessary (since skipped over in for loops)
        if (inputNextDir.startsWith(".")) {
          result.insert(0, ".");
        }

        // sets value to determine if it is a valid directory
        nextDirectory = new File(result.toString());

        // check if invalid directory, returns error message if so
        if (!(nextDirectory.exists() && nextDirectory.isDirectory())) {
          System.out.println("Error: Invalid result directory");
          errorFound = true;
        }
      }

      // output if valid directory
      if (!errorFound) {
        System.out.println(result.toString());
      }

    }
  }
}

