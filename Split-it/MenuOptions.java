/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MenuOptions
{
  static String fileName = "Group Data.txt";
  //static ArrayList<Project> allProjects = new ArrayList<Project>();
  static Map<String,Project> allProjects = new HashMap<String,Project>();
  static int key=0;
    //Option A - Displays an explanation of the program.
    public static void optionA ()
    {
        System.out.println("\n\n" + allProjects);
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
        String projectName = getProjectName();
        int teamSize = getTeamSize();
        System.out.println();
        Set<String> teamList = getTeamList(teamSize);
        Project p = new Project(projectName, teamSize, teamList);
        //allProjects.add(p);
        allProjects.put(projectName,p);
        //key++;
        
        //Prints back user team members and project name
        System.out.print("\nHello ");
        for (String lineCount : teamList)
        {
            System.out.print(lineCount);
            System.out.print(",");
        }
        System.out.println("your project: " + projectName + " has been registered, Happy Grading! ");

        menuReturn();
    }    
    
  public static String getProjectName()
  {
      Scanner scan = new Scanner(System.in);
      System.out.print("\nEnter the project name: ");
      String theName = scan.nextLine();
      
       //Error check so user cannot have a project with no name    
        while (theName.length() == 0)
        {     
            System.out.println("\n***ERROR!*** The project must have a name!");
            System.out.print("\nEnter the project name: ");
            theName = scan.nextLine();
        }    
        
       //Error check so user cannot have a member name that is non-alphnanumeric
            boolean valid = true ;
            while (valid)
       {
            for (int i = 0; i < theName.length(); i++)          
            {
            if (Character.isWhitespace(theName.charAt(i)) || Character.isLetterOrDigit(theName.charAt(i)))
              {
                valid = false;  
              }
            else 
              {
               System.out.println("\n***ERROR!*** Project name must only have numbers or letters!");
               System.out.print("\n\tEnter the project name: ");
               theName = scan.nextLine();
                valid = true;
              }
            }
        }
            

        
        return theName;
  }
  
  public static Integer getTeamSize()
  {
      //Stores all the names of the team members in an array
      //so that they can be called separately later on     
      Scanner scan = new Scanner(System.in);
      System.out.print("Enter the number of team members: ");
    
        //Error check so input must be of data type integer only
        while (!scan.hasNextInt()) 
        {        
            System.out.println("\n***ERROR!*** The team size must be an integer! ");
            System.out.print("\nEnter the number of team members: ");
            scan.next();
        }
      
        int memberSize = scan.nextInt(); 
      
        //Error check for teamsize as must be greater than 1 so marks 
        //can be actually split   
        while (memberSize <= 2)
        {
            System.out.print("\n***ERROR!*** A team must have at least 3 members!");
            System.out.println("\n This is required for fair voting.");
            System.out.print("\nEnter the number of team members: ");
            memberSize = scan.nextInt();
        }
      
        //Error check for teamsize as must be less than 15   
        while (memberSize > 15)
        {
            System.out.print("\n***ERROR!*** Team size is too large!");
            System.out.println(" A team can have maximum 15 members!");
            System.out.print("\nEnter the number of team members: ");
            memberSize = scan.nextInt();
        }
      
      return memberSize;
      
  }
  
  public static Set<String> getTeamList(int memberSize)
  {
        //Create set to prevent duplicate names in Name List
        Set<String> nameList = new HashSet<>();
        Scanner scan = new Scanner(System.in);
        for (int lineCount = 0; lineCount <= memberSize - 1; lineCount++)
        {
            System.out.print("\tEnter the name of team member " + (lineCount + 1) + ": ");
            String memberName = scan.nextLine();
            
            //Error check so name list cannot contain more than one member with the same name 
            while (nameList.contains(memberName))
            {
                System.out.println("\n***ERROR!*** Every member must have a unique name!");
                System.out.print("\n\tEnter the name of team member " + (lineCount + 1) + ": ");
                memberName = scan.nextLine();
            }
                    
            //Error check so user cannot have a member with no name                
            while (memberName.length() == 0)        
            {                 
                System.out.println("\n***ERROR!*** Every member must have a name!");
                System.out.print("\n\tEnter the name of team member " + (lineCount + 1) + ": ");
                memberName = scan.nextLine();        
            }
            //Error check so user cannot have a member name that is non-alphnanumeric
            boolean valid = true ;
            while (valid)
            {
                for (int i = 0; i < memberName.length(); i++)          
                {
                    if (Character.isLetterOrDigit(memberName.charAt(i)))
                    {
                        valid = false;  
                    }
                    else 
                    {
                        System.out.println("\n***ERROR!*** Every member name must only have numbers or letters!");
                        System.out.print("\n\tEnter the name of team member " + (lineCount + 1) + ": ");
                        memberName = scan.nextLine();
                        valid = true;
                    }
                }
            }
            nameList.add(memberName);
         }  
      
   return nameList;
  }
  
  private static HashMap<String, Integer> collectVotesFor(String teamMember, Set<String> otherMembers){
      Scanner scan = new Scanner(System.in);
      HashMap<String, Integer> votes = new HashMap<>();
      System.out.println("Enter " + teamMember + "'s votes, points must add up to 100: ");  
      for (String otherMember: otherMembers) {
          System.out.print("\tEnter " + teamMember + "'s points for " + otherMember + ": ");
          int points = scan.nextInt();
          while (points < 0 || points > 100)
          {
              System.out.println("\n***ERROR!*** Points given cannot be less than 0 or greater the 100!");
              System.out.print("\tEnter " + teamMember + "'s points for " + otherMember + ": ");
              points = scan.nextInt();
          }
          votes.put(otherMember, points);
      }
      return votes;
  }
  
private static Integer sumVotes(Collection<Integer> allVotes)
{  
  int sum = 0;
  for (int individualVote : allVotes){
      sum += individualVote;     
  }
    return sum;
}

    //Option V - Returns to Main Menu. (**for Deliverable 1)
    public static void optionV()   
  {

    Scanner scan = new Scanner(System.in);
    System.out.print("\nEnter the project name: ");    
    String projectName = scan.nextLine();
    Project project = allProjects.get(projectName);

    for (String teamMember : project.getTeamList())
    {
       Set<String> otherMembers = project.otherMembers(teamMember);
       HashMap<String, Integer> votes = collectVotesFor(teamMember, otherMembers);
       
       while(sumVotes(votes.values()) != 100){
           System.out.println("\n***ERROR!*** Points must add up to 100!");
           votes = collectVotesFor(teamMember, otherMembers);
       }
       
       project.recordVotes(teamMember, votes);
    }
    
    // looop through all of the project members
    // foreach one you want to vote for the other mebmers
    
    
//    Scanner inputStream = makeInputStream();
//    String line = null;
//
//
//    line = inputStream.nextLine();
//    String[] record = line.split(",");
//
//    System.out.println("\nThere are " + project.getTheTeamSize() + " team members. ");
//
     PrintWriter outputStream = makeOutputStream();
//
//    //int m = Integer.parseInt(record[1]);                  
//
//    for (int lineCount = 2; lineCount <= project.getTheTeamSize()+1; lineCount++)                 
//    {                 
//      System.out.println("Enter " + record[lineCount] + "'s votes, points must add up to 100: ");                    
//      outputStream.print(record[lineCount] + ",") ;                                         
//      for (int initialA = 2; initialA < lineCount ; initialA++)                        
//
//      {                        
//        System.out.print("\tEnter " + record[lineCount] + "'s points for " + record[initialA] + ": ");                         
//        String points = scan.nextLine();                        
//        outputStream.print(record[initialA] + "," + points + ",");                   
//      }
//
//      for (int initialB = lineCount + 1 ; initialB <= project.getTheTeamSize()+1; initialB++)                   
//      {          
//        System.out.print("\tEnter " + record[lineCount] + "'s points for " + record[initialB] + ": ");                    
//        String points = scan.nextLine();    
//        outputStream.print(record[initialB] + "," + points + ",");            
//      }                                  
//    }      

      outputStream.println("");
      outputStream.close();        
//      inputStream.close();
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
        printProjects();
        System.out.print("\n\nThank you for using Split-it! We hope you use Split-it again!");
        System.out.println("\n\n*********************THE PROGRAM HAS ENDED*********************\n\n");
    }   
    
    public static PrintWriter makeOutputStream()
    {
         File fileObject = new File(fileName);
         
         PrintWriter outStream = null;
         try

         {
             outStream =
                  new PrintWriter(new FileOutputStream(fileName, true));
         }
         catch(FileNotFoundException e)
         {
             System.out.println("Error opening the file " + fileName);
             System.exit(0);
         } 
         
         return outStream;
    }
    
    public static Scanner makeInputStream()
    {
        File fileObject = new File(fileName);
        Scanner inStream = null;

       try
       {
           inStream =
              new Scanner(new FileInputStream(fileName));
       }
       catch(FileNotFoundException e)
       {
           System.out.println("File: " + fileName + "was not found");
           System.out.println("or could not be opened.");
           System.exit(0);
       }
       
        return inStream;
    }
    
public static void printProjects()
{
        PrintWriter outputStream = makeOutputStream();
        for (Map.Entry<String,Project> pair : allProjects.entrySet()) 
        {
    String projectName = pair.getKey();
    Project project = pair.getValue();
    outputStream.write(project.toString());
        }
        /*int mapSize = allProjects.size();
        for (int i = 0; i < mapSize; i++)
        {
            String str = allProjects.get(i).toString();
            outputStream.write(str);AA        
        }
        
        int arraySize = allProjects.size();
        for (int i = 0; i < arraySize ; i++) 
        {
            String str = allProjects.get(i).toString();
            outputStream.write(str);
        }*/
 
         outputStream.close();
    
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
  
