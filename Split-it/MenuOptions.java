import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class MenuOptions
{

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String ANSI_PURPLE = "\u001B[35m";

    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String ERROR = ANSI_RED + "\n***ERROR!*** " + ANSI_RESET;

    public static final String SPLIT_IT = ANSI_CYAN + "Split-It" + ANSI_RESET;

    static String fileName = "Group Data.csv";
    
    static File fileObject = new File(fileName);

    static Map<String,Project> allProjects = new LinkedHashMap<String,Project>();
    
    //Option A - Displays an explanation of the program.     
    public static void optionA ()
    {     
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nThis program is a Fair Grade Allocator called " + SPLIT_IT + ". ");
        System.out.println("The purpose of the application is to help teams allocate the credit");
        System.out.println("for a project fairly so that all parties are satisfied with the outcome. ");  
        System.out.println("\nChoose '" + ANSI_GREEN + "C" + ANSI_RESET + "' to create a new project. ");
        System.out.println("Choose '" + ANSI_BLUE + "V" + ANSI_RESET + "' to collect the votes cast for a project. "); 
        System.out.println("Choose 'E' to view and edit the votes cast for a project. ");
        System.out.println("Choose '" + ANSI_YELLOW + "S" + ANSI_RESET + "' to view the fair point allocation for a project. "); 
        System.out.println("Choose '" + ANSI_PURPLE + "Q" + ANSI_RESET + "' to end the application. "); 
      
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
        allProjects.put(projectName,p);
        
        //Prints back user team members and project name
        System.out.print("\nHello ");
        for (String lineCount : teamList)
        {
            System.out.print(lineCount);
            System.out.print(", ");
        }
      
        System.out.println("your project: " + projectName + " has been registered. Happy Grading! ");
        menuReturn();
    }    
    
    //Gets the project name from the user and validates that the project name is not a null
    //value nor a non-alphanumeric name. Also prevents creating projects with duplicate names.

    /**
     *
     * @return
     */
    public static String getProjectName()
    {
        Scanner scan = new Scanner(System.in);
        //Error check so user cannot have a project with no name            
        //Error check so user cannot have a project name that is non-alphanumeric
        //Error check so there are no two projects with the same name
        String theName = null;
        boolean valid = false ;
        while (!valid)
        {
            System.out.print("\nEnter the project name: ");
            theName = scan.nextLine();
            if (theName.length() != 0)
            {
                for (int i = 0; i < theName.length(); i++)          
                {
                    if ((Character.isLetterOrDigit(theName.charAt(i)) || Character.isWhitespace(theName.charAt(i))) && !allProjects.containsKey(theName))
                    {
                        valid = true;  
                    }
                    else 
                    {
                        System.out.println(ERROR + "Team name must be unique to " + SPLIT_IT + " and only have numbers or letters!\n");
                        i = i + 30;
                    }
                }
            }
            else
            {
                System.out.println(ERROR + "The project must have a name!\n");
            }
        }
     
        return theName;
    }
  
    //Gets the number of team members and checks that the team size is between
    //3 (minimum) and 15 (maximum) members

    /**
     *
     * @return
     */
    public static Integer getTeamSize()
    {  
        Scanner scan = new Scanner(System.in);
            //Error check so input must be of data type integer only
            //Error check so team size must be 3 for the purpose of deliverable 3
            boolean inputValid = false;
            int memberSize = 0;
            while (!inputValid)
            {
                System.out.print("Enter the number of team members: ");
                try
                {
                    memberSize = scan.nextInt();
                    if (memberSize == 3)
                    {
                        inputValid = true;
                    }
                    else
                    {
                        System.out.print(ERROR + "A team must have only 3 members to use this application!");
                        System.out.println("\n This is required for fair voting.\n");
                    }
                }
                catch (InputMismatchException e)
                {
                    scan.nextLine();
                    System.out.println(ERROR + "The team size must be an integer!\n");
                }
            }
            
        return memberSize;
    }
  
    //Gets the team member names and checks that no duplicate names are stored in the set,
    //that the names are not of null value, and are all alphanumeric

    /**
     *
     * @param memberSize
     * @return
     */
    public static Set<String> getTeamList(int memberSize)
    {
        //Create set to prevent duplicate names in Name List
        Set<String> nameList = new LinkedHashSet<>();
        Scanner scan = new Scanner(System.in);
        for (int lineCount = 0; lineCount <= memberSize - 1; lineCount++)
        {
            //Error check so name list cannot contain more than one member with the same name     
            //Error check so user cannot have a member with no name                         
            //Error check so user cannot have a member name that is non-alphanumeric
            String memberName = null;
            boolean valid = false ;
            while (!valid)
            {
                System.out.print("\tEnter the name of team member " + (lineCount + 1) + ": ");
                memberName = scan.nextLine();
                if (memberName.length() != 0)
                {
                    for (int i = 0; i < memberName.length(); i++)          
                    {
                        if (Character.isLetterOrDigit(memberName.charAt(i)) && !nameList.contains(memberName))
                        {
                            valid = true;  
                        }
                        else 
                        {
                            System.out.println(ERROR + "Every member name must be unique and only have numbers or letters!\n");
                            i = i + 30;
                        }
                    }
                }
                else
                {
                    System.out.println(ERROR + "Every member must have a name!\n");
                }
            }
          
            nameList.add(memberName);
        }  
      
        return nameList;
    }
  
    //Option V - Allows users to input votes for the team members.
    public static void optionV()   
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter the project name you want to add/edit votes for: ");    
        String projectName = scan.nextLine();
        while (!allProjects.containsKey(projectName)) 
        {
            System.out.println(ERROR + "Project name could not be found!");
            System.out.print("\nEnter a project name that has already been created: ");
            projectName = scan.nextLine();
        } 
        Project project = allProjects.get(projectName);
        
        //Asks each member to vote for the other team members and records their votes
        for (String teamMember : project.getTeamList())
        {
            Set<String> otherMembers = project.otherMembers(teamMember);
            LinkedHashMap<String, Integer> votes = collectVotesFor(teamMember, otherMembers);
       
            //Error check so votes add up to 100
            while(sumVotes(votes.values()) != 100)
            {
                System.out.println(ERROR + "Points must add up to 100!\n");
                votes = collectVotesFor(teamMember, otherMembers);
            }
       
            project.recordVotes(teamMember, votes);
        }

        menuReturn();
    }
  
    //Asks to collect votes from a team member, for other members

    /**
     *
     * @param teamMember
     * @param otherMembers
     * @return
     */
    private static LinkedHashMap<String, Integer> collectVotesFor(String teamMember, Set<String> otherMembers)
    {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, Integer> votes = new LinkedHashMap<>();
        System.out.println("\nEnter " + teamMember + "'s votes, points must add up to 100: \n");
        for (String otherMember: otherMembers) 
        {
            //Error check so input must be of data type integer only
            //Error check so points must be between 0-100
            boolean inputValid = false;
            int points = 0;
            while (!inputValid)
            {
                System.out.print("\tEnter " + teamMember + "'s points for " + otherMember + ": ");
                try
                {
                    points = scan.nextInt();
                    if (points >= 0 && points <= 100)
                    {
                        inputValid = true;
                    }
                    else
                    {
                        System.out.println(ERROR + "Points given cannot be less than 0 or greater than 100!\n");
                    }
                }
                catch (InputMismatchException e)
                {
                    scan.nextLine();
                    System.out.println(ERROR + "The points must be an integer!\n");
                }
            }        

            votes.put(otherMember, points);
        }      

        return votes;
    }
  
    //Adds up all of the votes for a team member

    /**
     *
     * @param allVotes
     * @return
     */
    private static Integer sumVotes(Collection<Integer> allVotes)
    {  
        int sum = 0;
        for (int individualVote : allVotes)
        {
            sum += individualVote;     
        }

        return sum;
    }
    
    //Option E - Allows user to view and edit votes.
    public static void optionE ()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter the project's name you wish to display/edit the votes for: ");    
        String editingProjectName = scan.nextLine();

        //Error check: to check whether this project has been created yet or not
        while (!allProjects.containsKey(editingProjectName)) 
        {
            System.out.println(ERROR + "Project name could not be found or does not exist!");
            System.out.print("\nEnter a project name that has been saved: ");
            editingProjectName = scan.nextLine();
        } 
        
        Project editProject = allProjects.get(editingProjectName); 
        System.out.println("\nThere are " + editProject.getTheTeamSize() + " team members in project " + editingProjectName + ".");
        System.out.print("\nWould you like to see the current votes(Y/N): ");
        char selection = MainMenu.getSelection();
        
        //Error check to ensure user can only enter options listed.     
        String allowedOptions = "YN";
        while (allowedOptions.indexOf(selection) == -1)
        {
            System.out.print(ERROR + "Please choose a valid option, either Y or N: ");
            selection = MainMenu.getSelection();
        }
        
        //if statement routes user to selected input.   
        if (selection == 'Y')
        {
            //Displays each members votes for user
            editProject.printVotesDisplay();
        }
        
        System.out.println("\nWould you like to edit votes for all members,one member or none?\n");
        System.out.println("\tAll members (A)");
        System.out.println("\tOne member (M)");
        System.out.println("\tNone (N)");
        System.out.print("\nOption: ");
        selection = MainMenu.getSelection();
        
        //Error check to ensure user can only enter options listed.     
        allowedOptions = "AMN";
        while (allowedOptions.indexOf(selection) == -1)
        {
            System.out.print(ERROR + "Please choose a valid option, either A, M or N: ");
            selection = MainMenu.getSelection();
        }        
        
        if (selection == 'A')
        {
            //Asks each member to vote for the other team members and records their votes
            for (String teamMember : editProject.getTeamList())
            {
                Set<String> otherMembers = editProject.otherMembers(teamMember);
                LinkedHashMap<String, Integer> votes = collectVotesFor(teamMember, otherMembers);

                //Error check so votes add up to 100
                while(sumVotes(votes.values()) != 100)
                {
                    System.out.println(ERROR + "Points must add up to 100!\n");
                    votes = collectVotesFor(teamMember, otherMembers);
                }

                editProject.recordVotes(teamMember, votes);
            } 
            
            System.out.print("\nYour new votes for " + editingProjectName + " have been registered.\n");
            menuReturn();
        }        
        else if(selection == 'M')
        {
            //Error check: to check whether this member is in this project or not
            System.out.print("\nEnter the member whose votes you want to edit:");
            String teamMember = scan.nextLine();
            while (!editProject.getTeamList().contains(teamMember)) 
            {
                System.out.println(ERROR + "Member name could not be found or doesn't exit!");
                System.out.print("\nEnter a member name that is in this project: ");
                teamMember = scan.nextLine();
            } 

            //Creates set of members being voted for and asks member voting to enter
            //new votes for them
            Set<String> otherMembers = editProject.otherMembers(teamMember);
            LinkedHashMap<String, Integer> votes = collectVotesFor(teamMember, otherMembers);

            //Error check so votes add up to 100
            while(sumVotes(votes.values()) != 100)
            {
                System.out.println(ERROR + "Points must add up to 100!");
                votes = collectVotesFor(teamMember, otherMembers);
            }

            //Edit and set the new votes for this member
            editProject.recordVotes(teamMember, votes);
            System.out.print("\n" + teamMember + "'s new votes have been registered.\n");
            menuReturn();
        }
        else
        menuReturn();
    
    }

    //Option S - Shows fairly allocated points for the three members.
    public static void optionS()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter the project's name you wish to see the fairly allocated points for: ");    
        String projectNameShow = scan.nextLine();

        //Error check to see if project name has already been stored in Split-it
        while (!allProjects.containsKey(projectNameShow)) 
        {
            System.out.println(ERROR + "Project name could not be found!");
            System.out.print("\nEnter a project name that has been saved: ");
            projectNameShow = scan.nextLine();
        } 
        Project projectShow = allProjects.get(projectNameShow); 
        System.out.println("\nThere are 3 team members in project " + projectNameShow + ".");
        System.out.println("\nThe point allocation based on votes is:\n");

        //Iterate over each team member to calculate and print out points
        for (String teamMember : projectShow.getTeamList())
        {
            ArrayList<Number> ratioPointsList = new ArrayList<>();
            LinkedHashMap<String, LinkedHashMap<String, Integer>> votesShow = projectShow.getVotes();
            Set<String> otherTeamMembers = projectShow.otherMembers(teamMember);

            //Takes the votes for teamMember by the other 2 members, calculates the ratio required for
            //calculating the point allocation and stores the ratios in an arraylist
            for (String otherTeamMember : otherTeamMembers)
            {
                LinkedHashMap<String, Integer> votesByOthers = votesShow.get(otherTeamMember);
                int voteForTeamMember = votesByOthers.get(teamMember);
                double ratioPoint = (double)(100 - voteForTeamMember)/voteForTeamMember;
                ratioPointsList.add(ratioPoint);
            }

            //Takes the ratios from the arraylist and calculates the final points, then prints them out.
            //The calculation up until the end is done as doubles not integers to keep accuracy. The final point is
            //formatted so that it is rounded to the nearest integer.
            double pointOne = (double)ratioPointsList.get(0);
            double pointTwo = (double)ratioPointsList.get(1);
            double calculatedPoint = 100*1/(1 + pointOne + pointTwo);
            //Format final allocated point to rounded nearest integer
            String finalPoint = String.format("%.0f", calculatedPoint);
            System.out.println("\t" + teamMember + ": \t" + finalPoint);
        }

        menuReturn();
    }
    
    //Option Q - First prints all projects to specified file, then ends application with a dialog.
    public static void optionQ()
    { 
        printProjects();
        System.out.print("\n\nThank you for using " + SPLIT_IT + "! ");
        System.out.print("We hope you use " + SPLIT_IT + " again!");
        System.out.println(ANSI_PURPLE + "\n\n*********************THE PROGRAM HAS ENDED*********************\n\n" + ANSI_RESET);
    }   
    
    //Prints projects to given file  
    public static void printProjects()
    {
        PrintWriter outputStream = makeOutputStream();
        for (Map.Entry<String,Project> pair : allProjects.entrySet()) 
        {
            String projectName = pair.getKey();
            Project project = pair.getValue();
            outputStream.write(project.toString());
        }
      
        outputStream.close();    
    }
    
    //Reads data from csv data file, rebuilds projects (objects) and stores in map
    //with projectname as key, essentially rebuilds allProjects map from database to allow for editing

    /**
     *
     * @throws IOException
     */
    public static void loadProjectsFromFile() throws IOException 
    {
        BufferedReader inputReader = makeInputReader();
        String line = null;
        
        //Creates a set with each element a string. Each string contains a line of data for a single project.
        Set<String> projectsFromLine = new LinkedHashSet<>();
        
        //Reads the file line by line and adds each line as a new element in the set
        while((line =inputReader.readLine()) != null )
        {
            projectsFromLine.add(line);
        }
        
        //Takes each line from the above set, delimits into an array using 
        //commas and extracts project name then builds the project and adds it 
        //to the map of all projects with the extracted projectName as the key
        for (String eachLine : projectsFromLine) 
        {
            String[] projectFields = eachLine.split(",");
            String projectName = projectFields[0];
            allProjects.put(projectName, Project.buildFromLine(projectName, eachLine, projectFields)); 
        }
      
        inputReader.close();
    }
     
    //Creates output stream to write to file

    /**
     *
     * @return
     */
    public static PrintWriter makeOutputStream()
    {
        PrintWriter outStream = null;
        try
        {
            //Output stream doesn't append to csv file, it rewrites so each time program starts database  
            //is read, map rebuilt and printed out at quit so there are no duplicate entries in csv file
            outStream = new PrintWriter(new FileOutputStream(fileObject));
        }
        catch(FileNotFoundException e)
        {
            System.out.println(ERROR + "Could not open the file " + fileName);
            System.exit(0);
        } 
         
        return outStream;
    }
    
    //Creates an input reader so we can read from file

    /**
     *
     * @return
     */
    public static BufferedReader makeInputReader() throws IOException
    {
        BufferedReader inStream = null;
        
        //Checks if file exists, otherwise creates new file with that filename
        if (!fileObject.exists())
        {
            fileObject.createNewFile();
        }
        
        try
        {
            inStream = new BufferedReader(new FileReader(fileObject));
        }
        catch(FileNotFoundException e)
        {
            System.out.println(ERROR + "File: " + fileName + "was not found");
            System.out.println("or could not be opened.");
            System.exit(0);
        }
        
        return inStream;
    }

    //Compares user input to null value so that the if statement is always true, 
    //hence any input returns user to Main Menu  
    public static void menuReturn()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nPress any key followed by " + ANSI_RED + "<Enter>" + ANSI_RESET + " to return to the main menu: "); 
        if (scan.nextLine() != null)
        {
            MainMenu.primaryMethod();
        }        
    }
}
