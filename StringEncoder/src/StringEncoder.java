import java.util.Scanner;

public class StringEncoder {
    static char[] reference = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9','(',')','*','+',',','-','.','/'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the string to encode: ");
        String inputString = scanner.nextLine();
        
        scanner.close();
        
        // Encoding the input string
        String encodedString = encode(inputString);
        System.out.println("Input string: " + inputString);
        System.out.println("Encoded string: " + encodedString);

        // Decoding the encoded string 
        String decodedString = decode(encodedString);
        System.out.println("Encoded string: " + encodedString);
        System.out.println("Decoded string: " + decodedString);
    }


    public static String encode(String plainText){
        String encodedWord = "";
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            c = Character.isLowerCase(c) ? Character.toUpperCase(c):c;

            System.out.println("inputChar: "+c);
            
            for (int j = 0; j < reference.length; j++) {
                if (reference[j] == c) {
                    int charID = j;
                    int encCharID = 0;
    
                    if (charID >= 20){
                        encCharID = charID - 20;
                    } else{
                        encCharID = 44-(20-charID);
                    }
                    
                    char newChar = reference[encCharID];
                    encodedWord = encodedWord + newChar;
    
                    System.out.println("encodedChar: "+ newChar );
                    break;
                } else {
                    if (c == ' '){
                        encodedWord = encodedWord + ' ';
                        System.out.println("encodedChar: ");
                        break;
                    }else if (j == 42){
                        encodedWord = encodedWord + '#';
                        System.out.println("char not in reference list");
                    }
                }
            }
            
        }
        return encodedWord;
    }

    public static String decode(String encodedText){
        String decodedWord = "";
        for (int i = 0; i < encodedText.length(); i++) {
            char c = encodedText.charAt(i);
            //c = Character.isLowerCase(c) ? Character.toUpperCase(c):c;

            System.out.println("encodedChar: "+c);
            
            for (int j = 0; j < reference.length; j++) {
                if (reference[j] == c) {
                    int encCharID = j;
                    int decCharID = 0;
    
                    if (encCharID >= 24){
                        decCharID = encCharID-24;
                    } else{
                        decCharID = 44-(24-encCharID);
                    }
                    
                    char newChar = reference[decCharID];
                    decodedWord = decodedWord + newChar;
    
                    System.out.println("decodedChar: "+ newChar );
                    break;
                } else {
                    if (c == ' '){
                        decodedWord = decodedWord + ' ';
                        System.out.println("decodedChar: ");
                        break;
                    }else if (j == 42){
                        decodedWord = decodedWord + '#';
                        System.out.println("char not in reference list");
                    }
                }
            }
            
        }
        return decodedWord;
    }
    

}
    


