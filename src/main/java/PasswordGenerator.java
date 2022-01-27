import org.apache.commons.lang3.ArrayUtils;

import java.util.*;


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

        String specialOption = "";

        /*
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
                // place the users answer in the specialOption array to know what we will need
                if(answerInput.matches("YES|Y"))
                {
                    specialOption[responceCounter] = "YES";
                }
                else{
                    specialOption[responceCounter] = "NO";
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
        */
        System.out.print("Special Characters Included (Y/N): ");
        specialOption = in.next().toUpperCase();


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
            specialOption[i] = letters;
        }

 */

        /*
        for(int j = 0; j < specialOption.length; j++){
            System.out.println(specialOption[j]);
        }

         */

        // Make a char array of all characters used
        char[] lowerCaseArray = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        // System.out.println(lowerCaseArray.length);
        char[] upperCaseArray = "ABCDEFGHIJKLMNOPQRESTUWXYZ".toCharArray();
        // System.out.println(upperCaseArray.length);
        char[] numbers = "0123456789".toCharArray();

        String generatedPassword;
        // If we do have special characters, ask which characters to exclude
        // else generate a password of numbers
        char[] mixedValued;     // declare the array which will hold all values
        if(specialOption.equals("Y"))
        {
            String specialCharacters = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
            System.out.println("We contain the following 32 characters to use for passwords: " + specialCharacters);

            /*
             What I will do is keep them in a string all together right now. Then when I get each individual char, I will
             then go through the whole array and take the characters out individually,
             then I will convert it into a char array
             */
            /*
            Never mind, just get the char, and remove it from the string
             */


            // System.out.println(specialCharacters.length());

            System.out.print("Are there any characters that you would like to exclude due to issues from the website, Y or N?: ");
            String exceptionAnswer = in.next().toUpperCase();

            String newPassword;

            if(exceptionAnswer.equals("Y"))
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

            char[] specialCharacterArray = specialCharacters.toCharArray();

            mixedValued = mixPossibleValues(upperCaseArray, lowerCaseArray,  numbers, specialCharacterArray);
        }
        else{
            mixedValued = mixLettersAndNumbers(upperCaseArray, lowerCaseArray, numbers);
        }

        in.close();

        System.out.println(Arrays.toString(mixedValued));
        System.out.println(mixedValued.length);

        generatedPassword = getPassword(mixedValued, passwordLength);

        System.out.println("New Password is: " + generatedPassword);

        System.out.println("Would you like to ");
    }


    public static char[] mixPossibleValues(char[] upperLetters, char[] lowerLetters, char[] numbers, char[] specialCharacters)
    {
        char[] upperAndSpecial = ArrayUtils.addAll(upperLetters, specialCharacters);
        char[] lowerAndNumbers = ArrayUtils.addAll(lowerLetters, numbers);
        char[] allFourArraysMixed = ArrayUtils.addAll(upperAndSpecial, lowerAndNumbers);

        List<Character> addedTogether = new ArrayList<Character>();
        for(char character : allFourArraysMixed){
            addedTogether.add(character);
        }

        for(int timesToShuffle = 0; timesToShuffle < 13; timesToShuffle++)
        {
            Collections.shuffle(addedTogether);
        }


        char[] shuffledCharacters = new char[addedTogether.size()];
        for(int i = 0; i < addedTogether.size(); i++)
        {
            shuffledCharacters[i] = addedTogether.get(i);
        }

        //
        //shuffledCharacters = addedTogether.toArray(shuffledCharacters);

        return shuffledCharacters;
    }

    public static char[] mixLettersAndNumbers(char[] upperLetters, char[] lowerLetters, char[] numbers)
    {
        char[] upperAndNumbers = ArrayUtils.addAll(upperLetters, numbers);
        char[] allThreeArraysMixed = ArrayUtils.addAll(upperAndNumbers, lowerLetters);

        List<Character> addedTogether = new ArrayList<Character>();
        for(char character : allThreeArraysMixed){
            addedTogether.add(character);
        }

        for(int timesToShuffle = 0; timesToShuffle < 7; timesToShuffle++)
        {
            Collections.shuffle(addedTogether);
        }


        char[] shuffledCharacters = new char[addedTogether.size()];
        for(int i = 0; i < addedTogether.size(); i++)
        {
            shuffledCharacters[i] = addedTogether.get(i);
        }

        //
        //shuffledCharacters = addedTogether.toArray(shuffledCharacters);

        return shuffledCharacters;
    }

    public static String getPassword(char[] mixedArrayWithAllPossibleValues, int passwordLength){
        String newPasword = "";

        Random rand = new Random();
        int lengthOfTotalArray = mixedArrayWithAllPossibleValues.length;

        int randonNumber = 0;
        for(int i = 0; i < passwordLength; i++){
            randonNumber = rand.nextInt(lengthOfTotalArray);
            System.out.println("What random number to pick " + randonNumber);
            newPasword = newPasword + mixedArrayWithAllPossibleValues[randonNumber];
        }

        return newPasword;
    }
}
