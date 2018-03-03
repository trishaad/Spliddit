import java.util.Scanner;

public class MainMenu
{
    public static void main(String[] args)
    { 
        primaryMethod();
    }
  
    public static void primaryMethod()
    {
        Scanner scan = new Scanner(System.in);
        mainScreen();

        //Creates the variable 'selection' and sets it to getSelection,
        //so that if the user inputs no value, it produces an error message
        char selection = getSelection();
        
        //Error check to ensure user can only enter options listed.     
        String allowedOptions = "ACVSQ";
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
            
            case 'S': MenuOptions.optionS();  
            break;
             
            case 'Q': MenuOptions.optionQ();                          
            break;         
        }        
    }

    //Creates string and converts to char data type and upper case, 
    //so if user input is lowercase no errors in above error check.
    //Checks input is not null (i.e just pressing enter key) so user 
    //must input a value.
    public static char getSelection()   
    {  
        Scanner scan = new Scanner(System.in);      
        String optionString = scan.nextLine();
        if (optionString.length() == 0)         
        {
            return ' ';     
        }
        return optionString.toUpperCase().charAt(0);    
    }

    //Prints main screen for user to view.
    public static void mainScreen ()
    {
        System.out.println("\nWelcome to Split-it");
        System.out.println("\n\n\tAbout (A)");
        System.out.println("\tCreate Project (C)");
        System.out.println("\tEnter Votes (V)");
        System.out.println("\tShow Project (S)");
        System.out.println("\tQuit (Q)");
        System.out.print("\n\n\tPlease choose an option: ");
    }
  
    //Prints error message.
    public static void errorScreen ()
    {
        System.out.println("\n ***ERROR!*** Please choose a valid option from below: ");
        mainScreen();
    }
}

