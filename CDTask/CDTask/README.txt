CDTask - Created by Benjamin Wolff

This task using java to mimic the usage of the cd command in unix. Along with the source code,
which implements this functionality using a stack, is a test file which uses various JUnit tests
and the various sample directories provided with the files (ab, cd, ef, gh) to test out the code.

This project contains:
  - MyCD java class with main function that runs operations
  - CDTests java class with the unit tests
  - sample directories for the tests/practice operations
  - CDTask.jar file to run the program at the command line/terminal
  - this README.txt file

To run the code in the command line:
  - move the CDTask.jar file to whichever directory/location is convenient
  - use the cd to be in the same directory as the jar file (or just use the path to the jar when calling it)
  - run the command with java, with the CDTask.jar as the -jar flag and the starting and ending directory following

SAMPLE COMMAND LINE INPUT (from within the CDTask directory):
  #java -jar CDTask.jar ./ab ./cd
OUTPUT FROM SAMPLE:
  ./ab/cd/