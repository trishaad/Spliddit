import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class MenuOptions
{
  static String fileName = "groupdata.txt";
    //Option A - Displays an explanation of the program.
    public static void optionA ()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nThis program is a Fair Grade Allocator. ");
        System.out.println("The purpose of the application is to help teams allocate the credit");
        System.out.println("for a project fairly so that all parties are satisfied with the outcome. ");  
        System.out.println("\nChoose 'C' to create a new project. ");
        System.out.println("Choose 'V' to collect the votes cast for a project. "); 
        System.out.println("Choose 'S' to view the fair point allocation for a project. "); 
        System.out.println("Choose 'Q' to end the application. "); 

        menuReturn();
    }
  
    //Option C - Allows user to create new project.
    public static void optionC ()  
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter the project name: ");    
        String projectName = scan.nextLine();
    
        //Error check so user cannot have a project with no name    
        while (projectName.length() == 0)
        {     
            System.out.println("\n***ERROR!*** The project must have a name!");
            System.out.print("\nEnter the project name: ");
            projectName = scan.nextLine();
        }
         File fileObject = new File(fileName);
         
         PrintWriter outputStream = null;
         try
         {
             outputStream =
                  new PrintWriter(new FileOutputStream(fileName, true));
         }
         catch(FileNotFoundException e)
         {
             System.out.println("Error opening the file " + fileName);
             System.exit(0);
         }         
         outputStream.print(projectName + ",") ;      
        System.out.print("Enter the number of team members: ");
    
        //Error check so input must be of data type integer only
        while (!scan.hasNextInt()) 
        {        
            System.out.println("\n***ERROR!*** The team size must be an integer! ");
            System.out.print("\nEnter the number of team members: ");
            scan.next();
        }
      
        int teamSize = scan.nextInt();
        outputStream.print(teamSize + ","); 
      
        //Error check for teamsize as must be greater than 1 so marks 
        //can be actually split   
        while (teamSize <= 2)
        {
            System.out.print("\n***ERROR!*** A team must have at least 3 members!");
            System.out.println("\n This is required for fair voting.");
            System.out.print("\nEnter the number of team members: ");
            teamSize = scan.nextInt();
        }
      
        //Error check for teamsize as must be less than 15   
        while (teamSize > 15)
        {
            System.out.print("\n***ERROR!*** Team size is too large!");
            System.out.println(" A team can have maximum 15 members!");
            System.out.print("\nEnter the number of team members: ");
            teamSize = scan.nextInt();
        }
      
        //Stores all the names of the team members in an array
        //so that they can be called separately later on
        System.out.println();
        String[] nameList = new String[teamSize];
        Scanner scan2 = new Scanner(System.in);
        for (int lineCount = 1; lineCount <= teamSize; lineCount++)
        {
            System.out.print("\tEnter the name of team member " + lineCount + ": ");
            nameList[lineCount - 1] = scan2.nextLine();
                    
            //Error check so user cannot have a member with no name                
            while (nameList[lineCount - 1].length() == 0)        
            {                 
                System.out.println("\n***ERROR!*** Every member must have a name!");
                System.out.print("\n\tEnter the name of team member " + lineCount + ": ");
                nameList[lineCount - 1] = scan2.nextLine();        
            }
          outputStream.print(nameList[lineCount - 1] + ",");
        }
      outputStream.close( );

        //Prints back user team members and project name
        System.out.print("\nHello ");
        for (String lineCount : nameList)
        {
            System.out.print(lineCount);
            System.out.print(",");
        }
        System.out.println("your project: " + projectName + " has been registered, Happy Grading! ");

        menuReturn();
    }

    //Option V - Returns to Main Menu. (**for Deliverable 1)
    public static void optionV()   
  {

    Scanner scan = new Scanner(System.in);
    System.out.print("\nEnter the project name: ");    
    String projectName = scan.nextLine();
    Scanner inputStream = null;

       try
       {
           inputStream =
              new Scanner(new FileInputStream("groupdata.txt"));
       }
       catch(FileNotFoundException e)
       {
           System.out.println("File: groupdata.txt was not found");
           System.out.println("or could not be opened.");
           System.exit(0);
       }
      
      String line = null;


        line = inputStream.nextLine();
        String[] record = line.split(",");

        System.out.println("\nThere are " + record[1] + " team members. ");
        
        File fileObject = new File(fileName);
         
         PrintWriter outputStream = null;
         try
         {
             outputStream =
                  new PrintWriter(new FileOutputStream(fileName, true));
         }
         catch(FileNotFoundException e)
         {
             System.out.println("Error opening the file " + fileName);
             System.exit(0);
         }       
                 
      int m = Integer.parseInt(record[1]);                  
                 
      for (int lineC = 2; lineC <= m+1; lineC++)                 
      {                 
        System.out.println("Enter " + record[lineC] + "'s votes, points must add up to 100: ");                    
        outputStream.print(record[lineC] + ",") ;                                         
        for (int initialA = 2; initialA < lineC ; initialA++)                        
                    
        {                        
          System.out.print("\tEnter " + record[lineC] + "'s points for " + record[initialA] + ": ");                         
          String points = scan.nextLine();                        
          outputStream.print(record[initialA] + "," + points + ",");                   
        }
                   
        for (int initialB = lineC + 1 ; initialB <= m+1; initialB++)                   
        {          
          System.out.print("\tEnter " + record[lineC] + "'s points for " + record[initialB] + ": ");                    
          String points = scan.nextLine();    
          outputStream.print(record[initialB] + "," + points + ",");            
        }                                  
      }      
      
        outputStream.println("");
        outputStream.close();        
        inputStream.close();
        menuReturn();
  
  }
  
    //Option S - Returns to Main Menu. (**for Deliverable 1)
    public static void optionS()
    {
        MainMenu.primaryMethod();
    }

    //Option Q - Ends application with a dialog.
    public static void optionQ()
    {
        System.out.print("\n\nThank you for using Split-it! We hope you use Split-it again!");
        System.out.println("\n\n*********************THE PROGRAM HAS ENDED*********************\n\n");
    }  
  
    //Compares user input to null value so that the if statement is always true, 
    //hence any input returns user to Main Menu  
    public static void menuReturn()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nPress any key followed by <Enter> to return to the main menu: "); 
        if (scan.nextLine() != null)
        {
            MainMenu.primaryMethod();
        }      
      
    }
}