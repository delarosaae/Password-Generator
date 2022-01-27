import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        System.out.print("Enter password max length: ");

        String passwordLengthValidation;
        int passwordLength = 0;
        boolean validInput = true;

        // input validation
        // I could have just done a simple checks if it has a nextInt then taking that input out, but I
        // wanted to experiment with regex
        while(validInput)
        {
            // get the next string. get rid of white space
            passwordLengthValidation = in.nextLine().trim();
            // if the string does not have a letter, then we know we got a number, so proceed
            // and make validInput false to so that it won't loop again.
            if(!passwordLengthValidation.matches(".*[a-zA-Z].*")){
                passwordLength = Integer.parseInt(passwordLengthValidation);
                validInput = false;
            }
            // let them know that the input was incorrect and reenter a number
            else{
                System.out.println("Contained a letter. Please enter a number only");
            }
        }



        //System.out.println();
        //System.out.println(passwordLength);


        // make an array of all possible numbers letters and speial characters, not all of them
        // but have it so we have only three with 0 or 1 [letters, numbers, characters]
        // then have an array where users

        String[] options = new String[3];

        int responceCounter = 0;
        String answerInput;
        // this is done so we can make sure that a letter is inputted,
        // and that the inputted letter is a Y, y, N, or n and even Yes and No, make it uppercase so that it wont matter
        // used a while because if the user enters the incorrect input, we will have to ask again, but once
        // all questions are answer correctly, we can exit
        while(responceCounter < 3)
        {
            // check which option we have to ask
            if(responceCounter == 0){
                System.out.print("Letters Included (Y/N/Yes/No): ");
            }
            else if(responceCounter == 1)
            {
                System.out.print("Numbers Included (Y/N): ");
            }
            else{
                System.out.print("Special Characters Included (Y/N): ");
            }

            // get the answer
            answerInput = in.next().toUpperCase();
            // if the input matches the following, that means the input put in was correct
            if(answerInput.matches("YES|NO|Y|N"))
            {
                // place the users answer in the options array to know what we will need
                if(answerInput.matches("YES|Y"))
                {
                    options[responceCounter] = "YES";
                }
                else{
                    options[responceCounter] = "NO";
                }
                // if everything was entered correctly, add to the response counter so that once we have all three
                // we can leave the loop knowing that we have everything we need
                responceCounter++;
            }
            // if the input entered was not in the format or not what we wanted. Let them now and begin the loop again
            // to ask the same question
            else{
                System.out.println("Incorrect input. Please enter (Y/N/Yes/No)");
            }
        }

/*
        for(int i = 0; i < 3; i++)
        {
            if(i == 0){
                System.out.print("Letters Included (Y/N): ");
            }
            else if(i == 1)
            {
                System.out.print("Numbers Included (Y/N): ");
            }
            else{
                System.out.print("Special Characters Included (Y/N): ");
            }
            String letters = in.next().toUpperCase();
            options[i] = letters;
        }

 */

        /*
        for(int j = 0; j < options.length; j++){
            System.out.println(options[j]);
        }

         */

        // Make a char array of all characters used
        char[] lowerCaseArray = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        System.out.println(lowerCaseArray.length);
        char[] upperCaseArray = "ABCDEFGHIJKLMNOPQRESTUWXYZ".toCharArray();
        System.out.println(upperCaseArray.length);

        System.out.println("We contain the following 32 characters to use for passwords: ");
        System.out.println("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~");

        /*
         What I will do is keep them in a string all together right now. Then when I get each individual char, I will
         then go through the whole array and take the characters out individually,
         then I will convert it into a char array
         */
        /*
        Never mind, just get the char, and remove it from the string
         */



        String specialCharacters = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        // System.out.println(specialCharacters.length());

        System.out.print("Are there any characters that you would like to exclude due to issues from the website, Yes or No?: ");
        String exceptionAnswer = in.next().toUpperCase();

        String newPassword;

        if(exceptionAnswer.equals("YES"))
        {
            System.out.print("Enter how many special characters to exclude: ");
            System.out.println();
            int numberToExclude = in.nextInt();
            // I don't even need this String[] excludedCharactersList = new String[numberToExclude];

            // Actually get the character, find it in the string, and remove it
            for(int j = 0; j < numberToExclude; j++)
            {
                System.out.print("Enter the character to exclude: ");
                System.out.println();
                // excludedCharactersList[j] = in.next();

                specialCharacters = specialCharacters.replace(in.next(), "");
            }
            
        }



        in.close();
    }



    public static String[] mixAndMatchAllPossibleValues(String[] allOptions, char[] upperLetters, char[] lowerLetters, String[] numbers, String[] specialCharacters)
    {
        String[] possibleValuesMixedUp = new String[upperLetters.length + lowerLetters.length + numbers.length + specialCharacters.length];
        return possibleValuesMixedUp;
    }

    public static String getPassword(String[] mixedArrayWithAllPossibleValues){
        return "";
    }
}
