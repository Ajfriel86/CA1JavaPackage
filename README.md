Author: Angus Friel

Student Number: sba22066

Diploma in Applied Software Development

Programming - Object Oriented Approach

CA1 

Assessment Task:

When in operation, the program will be given a file named “students.txt” – this contains the details of (fictitious) students in the following format: 

- Line 1 – First Name Second Name
- Line 2 – Number of classes
- Line 3 – Student number

Your task is to: 
1)	Read the data from the file and check that it is valid. The file may contain more than one student, so an appropriate loop should be used. The data must obey the following rules: 
a)	the first name must be letters only; 
b)	The second name can be letters and/or numbers and must be separated from the first name by a single space; 
c)	the number of classes must be an integer value between 1 and 8 (inclusive), and 
d)	the student “number” must be a minimum of 6 characters with the first 2 characters being numbers, the 3rd  and 4th characters (and possibly 5th ) being a letter, and everything after the last letter character being a number.

2)	If the data is not valid, you should output a useful error message on screen to the user.

3)	If the data is valid, then you have to output the data to a file named status.txt, in the following format:

Student number - Second Name

Workload



Where the "Workload" is determined by the number of classes, as follows:


Number of classes	    Workload
- 1	                    - Very Light
- 2	                    - Light
- 3, 4, or 5	         - Part Time
- 6 or higher	           - Full Time

