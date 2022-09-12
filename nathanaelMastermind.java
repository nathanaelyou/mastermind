package isp;
/* File Name: nathanaelMastermind.java
   Name: Nathanael You
   Date: May 24th, 2022
 */

//Import File i/o, Scanner and Random
import java.util.*;
import java.io.*;
public class nathanaelMastermind {
    public static void main(String[] args) throws Exception{ //Main method
        Scanner myScanner = new Scanner(System.in); //Creates Scanner
        //Declare variable to print "mastermind" in big font
        String mastermind = "                     _                      _           _" + '\n' +
                "                    | |                    (_)         | |" + '\n' +
                " _ __ ___   __ _ ___| |_ ___ _ __ _ __ ___  _ _ __   __| |" + '\n' +
                "|  _   _ \\ / _  / __| __/ _ \\  __|  _   _ \\| |  _ \\ / _  |" + '\n' +
                "| | | | | | (_| \\__ \\ ||  __/ |  | | | | | | | | | | (_| |" + '\n' +
                "|_| |_| |_|\\__,_|___/\\__\\___|_|  |_| |_| |_|_|_| |_|\\__,_|";
        System.out.println("Welcome to!"); //Output welcome message
        System.out.println(mastermind); //Prints out mastermind
        System.out.println("Please enter a username:"); //Prompt user for username
        String username = myScanner.nextLine(); //Store username
        System.out.println("Welcome " + username + ", hope you have fun!" + '\n');
        String exit = "y"; //Declare string variable used to exit the program
        while(exit.equalsIgnoreCase("y")){ //While "y" is equal to exit
            //Prompt user if they would like to play with one or two users players
            System.out.println("Would you like to play with 1 or 2 players?");
            System.out.println("Enter '1' or '2'");
            //Allow them to also type in "r" to read the rules
            System.out.println("You may also enter 'r' to read the rules of the game");
            String players = myScanner.next(); //Store amount of players input
            //While the input is not equal to "1", "2" or "r"
            while(!players.equals("1") && !players.equals("2") && !players.equalsIgnoreCase("r")){
                System.out.println("That is an invalid input"); //Tell user input is invalid
                //Prompt user again how many players they would like to play with, or read the rules
                System.out.println("Would you like to play with 1 or 2 players?");
                System.out.println("Enter '1' or '2'");
                System.out.println("You may also enter 'r' to read the rules of the game");
                players = myScanner.next(); //Store new input
            }
            if(players.equalsIgnoreCase("r")){ //If the input is equal to "r" (read the rules)
                //Output the rules to the user
                System.out.println("Mastermind is a puzzle game where the goal is to outsmart your opponent with a " +
                        "sneaky code or excellent guessing." + '\n' +
                        "There are 2 roles, code-maker and decoder" + '\n' +
                        "    As the code-maker, your goal is to create a code that will keep the decoder " +
                        "guessing for as long as possible." + '\n' +
                        "    As the decoder, your goal is to think of the code in the fewest number of " +
                        "guesses possible." + '\n' +
                        "If you are playing with 1 player, the CPU will fill the position as the code " +
                        "maker" + '\n' +
                        "The code will consist of 4 numbers (from 1 to 6 in normal mode), where there can" +
                        " be multiple of the same number." + '\n' +
                        "You, as the decoder will now have to guess the code." + '\n' +
                        "Each guess can be made by entering a combination of numbers of 4 numbers" + '\n' +
                        "    (From 1-5 in easy mode, 1-6 in normal mode, and 1-7 in hard mode)" + '\n' +
                        "After the guess is made, you will receive hints to in order to find the code." + '\n' +
                        "There will be 2 types of hints; red hints and white hints:" + '\n' +
                        "    Red hints tell you the number of “pegs“ in the correct position." + '\n' +
                        "    White hints will indicate how many “pegs” are in the correct colour but in " +
                        "the incorrect position." + '\n' +
                        "For example, if the code is [5, 6, 6, 3] and your guess is [5, 4, 2, 6]" + '\n' +
                        "You will receive one red hint and one white hint as the “5” is in the correct " +
                        "position and “6” is in the code, but incorrect position." + '\n' +
                        "You will have 9 guesses to find the code" + '\n' +
                        "    If you get it within 9 guesses, you win!" + '\n' +
                        "    If not, you lose and the CPU wins" + '\n' +
                        "If the game is played with 2 players, the rules and how the game is played " +
                        "are the same, except the code maker is an actual person." + '\n' +
                        "Just make sure to look away when the code maker is entering the code." + '\n');
            }
            else { //Else (If the players is equal to "1" or "2")
                record(); //Call record method (outputs the decoder and code-maker record)
                //Prompt if user would like to play easy, normal or hard mode (difficulty)
                System.out.println("Would you like to play easy, normal or hard mode?");
                System.out.println("'e' for easy, 'n' for normal or 'h' for hard");
                String difficulty = myScanner.next(); //Store difficulty input
                //While the input is not equal to "e", "n" or "h"
                while(!difficulty.equalsIgnoreCase("e") && !difficulty.equalsIgnoreCase("n")
                        && !difficulty.equalsIgnoreCase("h")){
                    //Tell user the difficulty they chose is invalid
                    System.out.println("That is an invalid difficulty, please enter a valid difficulty:");
                    difficulty = myScanner.next(); //Store the new input
                }
                char maxCodeRange; //Create a char for the max code range (The largest digit in the code)
                if(difficulty.equalsIgnoreCase("e")){ //If the difficulty is equal to "e"
                    maxCodeRange = '5'; //The max code range is equal to '5'
                    System.out.println("You have chosen easy mode" + '\n'); //Tell user they have chosen easy mode
                }
                else if(difficulty.equalsIgnoreCase("n")){ //Else if the difficulty is equal to "b"
                    maxCodeRange = '6'; //The max code range is equal to '6'
                    System.out.println("You have chosen normal mode" + '\n'); //Tell user they have chosen normal mode
                }
                else{ //Else (if the difficulty is equal to "h")
                    maxCodeRange = '7'; //The max code range is equal to '7'
                    System.out.println("You have chosen hard mode" + '\n'); //Tell user they have chosen hard mode
                }
                int guessNumber = 0; //Declare int variable = 0 used check the number of guesses
                int complete = 0; //Declare int variable = 0 used check if the guess code is correct
                String code; //Create String variable to store the code
                if(players.equals("1")) { //If players is equal to "1"
                    code = createCode(maxCodeRange); //Get the CPU to create the code and store in variable code
                }
                else{ //Else (if players is equal to "2")
                    //Prompt the code-maker for the code and the requirements for the code
                    System.out.println("Code-maker, please enter the code should contain 4 digits " +
                            "(from 1-" + maxCodeRange + ")");
                    code = myScanner.next(); //Store the code
                    int verifyCode = checkCode(code, maxCodeRange); //Call verifyCode to check if code is valid
                    while(verifyCode == 0 || verifyCode == -1){ //While verifyCode is equal to 0 or -1
                        //Tell code-maker that code is invalid
                        System.out.println("That is an invalid code, please enter a valid code:");
                        code = myScanner.next(); //Store new code input
                        verifyCode = checkCode(code, maxCodeRange); //Call verifyCode to check if code is valid again
                    }
                    for(int i = 0; i < 30; i++){ //Loop through 30 times
                        //Output message to make sure the decoder does not cheat
                        System.out.println("Decoder, don't cheat, don't scroll up!");
                    }
                    System.out.println(); //Print empty line for user friendliness
                }
                //While complete is not equal to 3 and guess number is less than 9
                while(complete != 4 && guessNumber < 9){
                    //Prompt the decoder for the guess and the requirements for the guess
                    System.out.println("Each guess should contain 4 digits (from 1-" + maxCodeRange + ")");
                    if(players.equals("1")) { //If players is equal to "1"
                        //Print out the username and guess the decoder is on
                        System.out.println(username + ", please enter guess #" + (guessNumber + 1) + " out of 9:");
                    }
                    else{ ///Else (if players is equal to "2")
                        //Print out the guess the decoder is on
                        System.out.println("Decoder, please enter guess #" + (guessNumber + 1) + " out of 9:");
                    }
                    //Print decoder may also enter 'quit' to quit
                    System.out.println("You may also enter 'quit' to give up and reveal the code");
                    String guess = myScanner.next(); //Store guess
                    if(guess.equalsIgnoreCase("quit")){ //If the guess is equal to "quit"
                        break; //Break
                    }
                    else { //Else (if the guess is not equal to "quit")
                        int verifyGuess = checkCode(guess, maxCodeRange); //Check if the guess is valid
                        if(verifyGuess == -1){ //If the guess is equal to -1
                            break; //Break
                        }
                        while (verifyGuess == 0) { //While verifyGuess is equal to 0 (invalid)
                            //Tell user guess is invalid and prompt for another code
                            System.out.println("That is an invalid code, please enter a valid code:");
                            guess = myScanner.next(); //Store new guess
                            verifyGuess = checkCode(guess, maxCodeRange); //Check the guess
                        }
                        if (verifyGuess == -1) { //If verifyGuess is equal to -1
                            break; //Break
                        }
                        outputHints(code, guess); //Output the hints/clues for the code
                        complete = winner(code, guess); //Complete is equal to the number of digits in the correct spot
                        guessNumber++; //Add one to the guess number
                    }
                }
                if(complete == 4 && players.equals("1")){ //If complete is equal to 4 and players is equal to "1"
                    String usernameInStars = ""; //Create string variable to the stars
                    for(int i = 0; i < (username.length() / 2); i++){ //Loop through half the username length
                        usernameInStars = usernameInStars.concat("⭐"); //Add a star to the string each time
                    }
                    //Output winning message for single player decoder win
                    System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐" + usernameInStars);
                    System.out.println("⭐ Congratulations " + username + ", you have won! ⭐");
                    System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐" + usernameInStars);
                    updateRecord(0); //Update the record (add one to the decoders)
                }
                //Else (if complete is equal to 4 and players is equal to "2")
                else if(complete == 4 && players.equals("2")){
                    //Output winning message for two player decoder win
                    System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");
                    System.out.println("⭐ Congratulations decoder, you have won! ⭐");
                    System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");
                    updateRecord(0); //Update the record (add one to the decoders)
                }
                else if(players.equals("1")){ //Else if players is equal to "1" (and complete is not equal to 4)
                    System.out.println("❌❌❌❌❌❌❌❌❌❌❌❌❌❌");
                    System.out.println("❌  Aw Man! The CPU has won  ❌");
                    System.out.println("❌❌❌❌❌❌❌❌❌❌❌❌❌❌");
                    System.out.println("The correct code was " + code); //Output the correct code
                    updateRecord(1); //Update the record (add one to the code-makers)
                }
                else{ //Else (if players is equal to "2" and complete is not equal to 4)
                    //Output winning message for two player code-maker win
                    System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");
                    System.out.println("⭐  Congratulations code-maker, you have won!  ⭐");
                    System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");
                    System.out.println("The correct code was " + code);
                    updateRecord(1); //Update the record (add one to the code-makers)
                }
            }
            if(!players.equalsIgnoreCase("r")){ //If players is not equal to "r"
                //Ask user of they would like to play again
                System.out.println("Would you like to play again?");
                System.out.println("Enter 'y' for yes and 'n' for no");
                exit = myScanner.next(); //Store exit input
                //While exit is not equal to "y" and "n"
                while(!exit.equalsIgnoreCase("y") && !exit.equalsIgnoreCase("n")) {
                    //Tell user input is invalid and prompt for new input
                    System.out.println("That is an invalid input, please enter 'y' or 'n'");
                    exit = myScanner.next(); //Store new input
                }
            }
        }
        System.out.println("Hope you had a fun time playing"); //Tell user hope they had a fun time
        System.out.println(mastermind); //Print mastermind
        myScanner.close(); //Close scanner
    }
    public static void record() throws Exception{ //Create method which outputs the decoder and code-maker record
        File myFile = new File("record.txt"); //Create file called record.txt
        boolean exists = myFile.exists(); //Check if file exists
        if(!exists){ //if it does not exist
            PrintWriter output = new PrintWriter(myFile); //Create printWriter
            for(int i = 0; i < 2; i++) { //Loop twice
                output.println(0); //Output 0 on 2 lines
            }
            output.close(); //Close output
        }
        Scanner input = new Scanner (myFile); //Create scanner for file
        String [] recordArray = new String[2]; //Create array for record
        for(int i = 0; i < 2; i++) { //Loop twice
            recordArray[i] = input.next(); //Store each input in the array
        }
        String decoderRecord = recordArray[0]; //Create variable for the decoder record
        String makerRecord = recordArray[1]; //Create variable for the code-maker record
        System.out.println("Decoder Record: " +  decoderRecord + " Wins"); //Output decoder wins
        System.out.println("Code Maker Record: " + makerRecord + " Wins" + '\n'); //Output code-maker wins
        input.close(); //Close input
    }
    public static String createCode(char charCodeRange){ //Create method to create a random code
        int codeRange = Character.getNumericValue(charCodeRange); //Change the code range from char to int
        String [] codeArray = new String[4]; //Create array for the code
        for(int i = 0; i < 4; i++){ //Loop 4 times
            int num = (int)(Math.random() * codeRange + 1); //Get random number within range
            String codeNum = String.valueOf(num); //Store the number as a string
            codeArray[i] = codeNum; //Store in array
        }
        String code = ""; //Create string variable for the code
        for(int i = 0; i < 4; i++){ //Loop 4 times
            code = code.concat(codeArray[i]); //The code is equal to code + each part of the array
        }
        return code; //Return the code
    }
    public static int checkCode(String code, char maxRange){ //Create method to check the code
        int returnValue = 1; //Set returnValue as 1
        if(code.length() != 4){ //If the code length is not equal to 4
            returnValue = 0; //Set returnValue as 0
        }
        else if(code.equalsIgnoreCase("quit")){ //if the returnValue is equal to "quit"
            returnValue = -1; //Set return value to -1
        }
        else { //Else (if the code is not equal to "quit")
            for (int i = 0; i < 4; i++) { //Loop 4 times
                if (code.charAt(i) < '1' || code.charAt(i) > maxRange) { //If the digit in the code is not within range
                    returnValue = 0; //Set returnValue as 0
                    break; //Break
                }
            }
        }
        return returnValue; //Return the returnValue
    }
    public static void outputHints(String code, String guess){ //Create method to output the hints
        String [] codeArray = new String[4]; //Create array for the code
        String [] guessArray = new String[4]; //Create array for the guess
        for(int i = 0; i < 4; i++){ //Loop 4 times
            codeArray[i] = code.substring(i,i+1); //Set the code array equal to part of the code
            guessArray[i] = guess.substring(i,i+1); //Set the guess array equal to part of the guess
        }
        int redHints = 0; //Create int variable for the amount of red hints
        String redHintVisual = ""; //Create string variable for a visual of red hints
        for(int i = 0; i < 4; i++){ //Loop 4 times
            if(codeArray[i].equals(guessArray[i])){ //If codeArray is equal to guessArray
                codeArray[i] = "r"; //Set the part equal of codeArray to "r"
                guessArray[i] = "r"; //Set the part equal of guessArray to "r"
                redHintVisual = redHintVisual.concat("\uD83D\uDD34 "); //Add visual emoji to redHintVisual
                redHints++; //Add one to redHints
            }
        }
        int whiteHints = 0; //Create int variable for the amount of white hints
        String whiteHintVisual = ""; //Create string variable for a visual of white hints
        for(int i = 0; i < 4; i++){ //Loop 4 times
            if(!codeArray[i].equals("r")) { //If the part of the array is not equal to "r"
                for(int j = 0; j < 4; j++){ //Loop 4 times
                    if(codeArray[i].equals(guessArray[j])){ //If part of codeArray equal to the part of guessArray
                        codeArray[i] = "w"; //Set the part equal of codeArray to "w"
                        guessArray[j] = "w"; //Set the part equal of codeArray to "w"
                        whiteHintVisual = whiteHintVisual.concat("⚪ "); //Add visual emoji to whiteVisualHint
                        whiteHints++; //Add one to whiteHints
                        break; //Break
                    }
                }
            }
        }
        //Output the red hints
        System.out.println("You have: " + redHints + " red hint(s) (correct position) " + redHintVisual);
        //Output the white hints
        System.out.println("You have: " + whiteHints + " white hint(s) (correct number, incorrect position) " +
                whiteHintVisual + '\n');
    }
    public static int winner(String code, String guess){ //Create method to check if code is equal to guess
        String [] codeArray = new String[4]; //Create array for the code
        String [] guessArray = new String[4]; //Create array for the guess
        for(int i = 0; i < 4; i++){ //Loop 4 times
            codeArray[i] = code.substring(i,i+1); //Set the code array equal to part of the code
            guessArray[i] = guess.substring(i,i+1); //Set the guess array equal to part of the guess
        }
        int redHints = 0; //Create int variable for the amount of red hints
        for(int i = 0; i < 4; i++){ //Loop 4 times
            if(codeArray[i].equals(guessArray[i])){ //If codeArray is equal to guessArray
                redHints++; //Add one to redHints
            }
        }
        return redHints; //Return redHints
    }
    public static void updateRecord(int winner) throws Exception{ //Create method to update the record
        File myFile = new File("record.txt"); //Open record.txt
        Scanner input = new Scanner (myFile); //Create scanner to read file
        int [] recordArray = new int[2]; //Create array to store numbers
        for(int i = 0; i < 2; i++) { //Loop twice
            recordArray[i] = input.nextInt(); //Store the numbers in array
        }
        if(winner == 0){ //if winner is equal to 0
            recordArray[0] = recordArray[0] + 1; //Add one to recordArray[0] (decoder record)
            //Output new amount of decoder wins
            System.out.println("Decoders now have: " +  recordArray[0] + " Win(s)" + '\n');
        }
        else{ //Else (if winner is equal to 1)
            recordArray[1] = recordArray[1] + 1; //Add one to recordArray[1] (code-maker record)
            //Output new amount of code-maker wins
            System.out.println("Code-makers now have: " + recordArray[1] + " Win(s)" + '\n');
        }
        PrintWriter output = new PrintWriter(myFile); //Create printWriter for the file
        for(int i = 0; i < 2; i++) { //Loop twice
            output.println(recordArray[i]); //Output the 2 new numbers (overwrites old file)
        }
        input.close(); //Close input
        output.close(); //Close output
    }
}
