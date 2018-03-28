import java.io.IOException;
import java.util.Scanner;

public class MainMenu
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ERROR = ANSI_RED + "\n***ERROR!***" + ANSI_RESET;
    public static final String SPLIT_IT = ANSI_CYAN + "Split-It" + ANSI_RESET;
   
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    { 
        MenuOptions.loadProjectsFromFile();
        primaryMethod();
    }
  
    //Asks for user input and navigates the user through the program
    public static void primaryMethod()
    {
        Scanner scan = new Scanner(System.in);
        mainScreen();

        //Creates the variable 'selection' and sets it to getSelection,
        //so that if the user inputs no value or more than one character,
        //it produces an error message
        char selection = getSelection();
        
        //Error check to ensure user can only enter options listed.     
        String allowedOptions = "ACVESQ";
        while (allowedOptions.indexOf(selection) == -1)
        {
            errorScreen ();
            selection = getSelection();
        }

        //Switch statement routes user to selected input.   
        switch (selection)
        {
            case 'A': MenuOptions.optionA();
            break; 
             
            case 'C': MenuOptions.optionC();
            break;
             
            case 'V': MenuOptions.optionV();
            break;
            
            case 'E': MenuOptions.optionE();
            break;
            
            case 'S': MenuOptions.optionS();  
            break;
             
            case 'Q': MenuOptions.optionQ();                          
            break;         
        }        
    }

    //Creates string and converts to char data type and upper case, 
    //so if user input is lowercase no errors in above error check.
    //Checks input is not null (i.e just pressing enter key) so user 
    //must input a value. Also checks if input is not a single character.

    /**
     *
     * @return
     */
    public static char getSelection()   
    {  
        Scanner scan = new Scanner(System.in);      
        String optionString = scan.nextLine();
        if (optionString.length() != 1)         
        {
            return ' ';     
        }

        return optionString.toUpperCase().charAt(0);    
    }

    //Prints main screen for user to view.
    public static void mainScreen ()
    {
        System.out.println("\nWelcome to " + SPLIT_IT);
        System.out.println("\n\n\tAbout (" + ANSI_RED + "A" + ANSI_RESET +")");
        System.out.println("\tCreate Project (" + ANSI_GREEN + "C" + ANSI_RESET +")");
        System.out.println("\tEnter Votes (" + ANSI_BLUE + "V" + ANSI_RESET +")");
        System.out.println("\tDisplay or Edit Votes (E)");
        System.out.println("\tShow Project (" + ANSI_YELLOW + "S" + ANSI_RESET +")");
        System.out.println("\tQuit (" + ANSI_PURPLE + "Q" + ANSI_RESET +")");
        System.out.print("\n\n\tPlease choose an option: ");
    }
  
    //Prints error message.
    public static void errorScreen ()
    {
        System.out.println(ERROR + "Please choose a valid option from below: ");
        mainScreen();
    }
}
