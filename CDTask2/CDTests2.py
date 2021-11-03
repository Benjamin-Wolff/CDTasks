import unittest
from io import StringIO
from unittest.mock import patch

import MyCD


class CDTests2(unittest.TestCase):
    """
        Test class for the MyCD in python. Checks the possible error messages and outputs fro the program.
    """

    def test_none_inputs(self):
        with patch('sys.stdout', new=StringIO()) as fake_out:
            MyCD.main(input_start_directory=None, input_next_directory=None)
            self.assertEqual("Error: Invalid inputs for directories\n", fake_out.getvalue())

    def test_one_none_inputs(self):
        with patch('sys.stdout', new=StringIO()) as fake_out:
            MyCD.main(input_start_directory=".", input_next_directory=None)
            self.assertEqual("Error: Invalid inputs for directories\n", fake_out.getvalue())

    def test_invalid_start(self):
        with patch('sys.stdout', new=StringIO()) as fake_out:
            MyCD.main(input_start_directory="./noneABC", input_next_directory="./cd")
            self.assertEqual("Error: Invalid start directory\n", fake_out.getvalue())

    def test_too_many_periods(self):
        with patch('sys.stdout', new=StringIO()) as fake_out:
            MyCD.main(input_start_directory="./ab/cd", input_next_directory="./....")
            self.assertEqual("Error: Invalid result path\n", fake_out.getvalue())

    def test_invalid_result_path(self):
        with patch('sys.stdout', new=StringIO()) as fake_out:
            MyCD.main(input_start_directory="./ab/cd", input_next_directory="ef/cd")
            self.assertEqual("Error: Invalid result path\n", fake_out.getvalue())

    def test_invalid_result_directory(self):
        with patch('sys.stdout', new=StringIO()) as fake_out:
            MyCD.main(input_start_directory="./ab/cd", input_next_directory="./lm")
            self.assertEqual("Error: Invalid result directory\n", fake_out.getvalue())

    def test_valid_inputs(self):
        with patch('sys.stdout', new=StringIO()) as fake_out:
            MyCD.main(input_start_directory="./ab", input_next_directory="./cd")
            self.assertEqual("./ab/cd/\n", fake_out.getvalue())

    def test_valid_period_inputs(self):
        with patch('sys.stdout', new=StringIO()) as fake_out:
            MyCD.main(input_start_directory="./gh/ij", input_next_directory="././.../ab/./cd/../ef")
            self.assertEqual("./ab/ef/\n", fake_out.getvalue())


if __name__ == '__main__':
    unittest.main()
