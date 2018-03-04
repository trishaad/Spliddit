/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author rharbird
 */
public class Splittit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        char option = '*';
        
        while (option != 'q')
        {
            option = menu();
            switch(option){
                
                case 'a':
                    about();
                    break;
                case 'c':
                    Project p = createProject();
                    // Just for debugging purposes, let's print the project
                    // details here
                    System.out.println(p.toString());
                    break;
                case 'e':
                    enterVotes();
                    break;
                case 's':
                    showProject();
                    break;
                case 'q': 
                    break;
                default:
                    System.out.println("Unknown option, please try again");
                    break;
            }
        
        }
        
        System.out.println("\nGoodbye!");
    }

    private static char menu() 
    {
        
        Scanner scan = new Scanner(System.in);

        System.out.println("\n\nWelcome to Split-it:");

        System.out.println("\tAbout (A)");
        System.out.println("\tCreate Project (C)");
        System.out.println("\tEnter Votes (V)");
        System.out.println("\tShow Project (S)");
        System.out.println("\tQuit (Q)");
        
        System.out.print("\nPlease choose an option: ");
        
        return (scan.nextLine().toLowerCase().charAt(0));
        
    }

    private static void about() {
        System.out.println("Explanation of the application goes here.");
    }

    private static Project createProject() {
        

        String projectName = getProjectName();
        int numberOfparticipants = getNumberOfParticipants();
        String[] teamNames = getTeamNames(numberOfparticipants);

        Project p = new Project(projectName, numberOfparticipants, teamNames);
          
        return p;
    }

    
    private static String getProjectName()
    {
        boolean valid = false;
        String projectName = new String();
        Scanner scan = new Scanner(System.in);

        while (valid == false)
        {
            System.out.print("\nEnter the project name: ");
    
            projectName = scan.nextLine();
            
            if (!Project.validateName(projectName))
            {
                System.out.println("\nProject name must contain alphanumeric characters only, try again.");
            }
            else
            {
                valid = true;
            }
        }
        return projectName;
    }
    
    private static int getNumberOfParticipants() 
    {
       Scanner scan = new Scanner(System.in);
       boolean valid = false;
       int numberOfMembers = 0;

       while (valid == false)
       {
            System.out.print("\nEnter the number of team members: ");
            
            // We don't check whether or not this is a number yet. We will learn how to 
            // do this with Exceptions later in the course.
            numberOfMembers = scan.nextInt();
            
            if (Project.validateNumberOfMembers(numberOfMembers))
            {
                valid = true;
            }
            else 
            {
                System.out.println("\nProjects can have between " + 
                Project.MINMEMBERS + " and " + 
                Project.MAXMEMBERS + " members, try again.");
            }
        }
       
       return numberOfMembers;
    }

    private static String[] getTeamNames(int numberOfparticipants) {

       Scanner scan = new Scanner(System.in);
       boolean valid = false;
       
       String[] names = new String[Project.MAXMEMBERS];
       
       for (int i = 0; i < numberOfparticipants; i++)
       {
           valid = false;          
           while (valid == false)
           {
                System.out.print("\nEnter team member's name: ");
                String name = scan.nextLine();
                if (Project.validateName(name))
                {
                    valid = true;
                    names[i] = name;  
                }
                else
                {
                    System.out.println("\nA name must contain only alphanumeric characters and be between " + 
                            Project.MINNAMELENGTH + " and " + Project.MAXNAMELENGTH + 
                            " characters, try again.");
                }
            }  
       }
       return names;
    }



    
    private static void enterVotes() {
        // empty for now
    }

    private static void showProject() {
        // empty for now.
    }




    
}

