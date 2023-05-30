Instructions:
'src' folder contains the main java file for the string encoder. 
Open the file and run the program. 
Input any string into the terminal and the output will be the individual characters in the input string, followed by the encoded characters, and then the whole encoded text.
The program will also decode the encoded text to obtain the original input text.

THIS ENCODER HAS AN OFFSET CHARACTER 'U'.

Example output:
Enter the string to encode: hello world
inputChar: H
encodedChar: 5
inputChar: E
encodedChar: 2
inputChar: L
encodedChar: 9
inputChar: L
encodedChar: 9
inputChar: O
encodedChar: *
inputChar:
encodedChar:
inputChar: W
encodedChar: C
inputChar: O
encodedChar: *
inputChar: R
encodedChar: -
inputChar: L
encodedChar: 9
inputChar: D
encodedChar: 1
Input string: hello world
Encoded string: 5299* C*-91
encodedChar: 5
decodedChar: H
encodedChar: 2
decodedChar: E
encodedChar: 9
decodedChar: L
encodedChar: 9
decodedChar: L
encodedChar: *
decodedChar: O
encodedChar:
decodedChar:
encodedChar: C
decodedChar: W
encodedChar: *
decodedChar: O
encodedChar: -
decodedChar: R
encodedChar: 9
decodedChar: L
encodedChar: 1
decodedChar: D
Encoded string: 5299* C*-91
Decoded string: HELLO WORLD


