import sys
from pathlib import Path

def main(input_start_directory=None, input_next_directory=None):
    """
        Main function that implements the cd unix command.
        Inputs String values of start directory and resulting directory.
        Outputs the directory, or an error message if invalid
    """
    # list utilized as a stack for storing words
    # using append instead of push and the list pop as the stack pop
    result_dir_stack = []

    # general boolean used to determine when program should stop if an error is found
    error_found = False

    # checks that 2 string inputs are properly provided
    if input_start_directory is None or input_next_directory is None:
        print("Error: Invalid inputs for directories")
        error_found = True

    # otherwise creates path to be checked
    else:
        start_directory_path = Path(input_start_directory)

    # checks if the path exists and is a directory for the start directory
    if not error_found and not (start_directory_path.exists() and start_directory_path.is_dir()):
        print("Error: Invalid start directory")
        error_found = True

    # otherwise splits the input strings into array
    if not error_found:
        split_current = input_start_directory.split("/")
        split_next = input_next_directory.split("/")

        # runs if resulting directory involves the starting directory
        if input_next_directory[0] == ".":
            i = 1
            # iterates through the strings in the split, which are potential directory names
            while i < len(split_current):
                temp = split_current[i]

                # does nothing if string at index is empty
                if not temp == "":

                    # check strings that start with .
                    if temp[0] == ".":

                        # checks for when the string starts with .. and should be moved back
                        if temp[0:2] == "..":

                            # if the stack is empty then the .. is an invalid operation
                            if len(result_dir_stack) == 0:
                                print("Error: Invalid result path")
                                i = len(split_current)
                                error_found = True

                            # otherwise the last element is popped
                            else:
                                result_dir_stack.pop()

                                # continues with the possibility that there are more than 2 periods in a row
                                split_current[i] = split_current[i][1:]
                                i -= 1

                        # otherwise pushes the string iif
                        elif not temp == ".":
                            result_dir_stack.append(temp)

                    else:
                        result_dir_stack.append(temp)

                # iteration for while loop
                i += 1

        # prints error if the next directory path does not start with . or /
        elif not input_next_directory[0] == "/":
            print("Error: Invalid result path")
            error_found = True

    # checks for error in past operations to avoid unnecessary or duplicate outputs
    if not error_found:
        i = 0
        # iterates through the strings in the split, which are potential directory names
        while i < len(split_next):
            temp = split_next[i]

            # does nothing if string at index is empty
            if not temp == "":

                # check strings that start with .
                if temp[0] == ".":

                    # checks for when the string starts with .. and should be moved back
                    if temp[0:2] == "..":

                        # if the stack is empty then the .. is an invalid operation
                        if len(result_dir_stack) == 0:
                            print("Error: Invalid result path")
                            i = len(split_next)
                            error_found = True

                        # otherwise the last element is popped
                        else:
                            result_dir_stack.pop()

                            # continues with the possibility that there are more than 2 periods in a row
                            split_next[i] = split_next[i][1:]
                            i -= 1

                    # otherwise pushes the string iif
                    elif not temp == ".":
                        result_dir_stack.append(temp)

                else:
                    result_dir_stack.append(temp)

            # iteration for while loop
            i += 1

    # again checks for errors to avoid unnecessary operations
    if not error_found:
        if input_next_directory[0] == ".":
            result = "./"
        else:
            result = "/"

        for directory in result_dir_stack:
            result += directory + "/"

        result_directory_path = Path(result)

        # checks if the path exists and is a directory for the resulting directory
        if result_directory_path.exists() and result_directory_path.is_dir():
            print(result)

        # otherwise prints an error
        else:
            print("Error: Invalid result directory")

if __name__ == "__main__":
    # small check that inputs are correct
    if len(sys.argv) != 3:
        print("Error: Invalid number of directory inputs")

    else:
        main(input_start_directory=sys.argv[1], input_next_directory=sys.argv[2])


