import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author trishaadbsurty
 */
public class Project 
{
    private String theName;
    private int teamSize;
    private Set<String> teamList;
    private LinkedHashMap<String, LinkedHashMap<String, Integer>> votes;

    /**
     *
     * @param teamName
     * @param memberSize
     * @param nameList
     */
    Project (String teamName, int memberSize, Set<String> nameList)
    {
        theName = teamName;
        teamSize = memberSize;
        teamList = nameList;
        votes = new LinkedHashMap<String, LinkedHashMap<String, Integer>>();
        for (String memberName: nameList) 
        {
            LinkedHashMap<String, Integer> otherMembers = new LinkedHashMap<String, Integer>();
            for (String otherMemberName: nameList) 
            {
                if (!otherMemberName.equals(memberName)) 
                {
                    otherMembers.put(otherMemberName, 0);
                }
            }
        
            votes.put(memberName, otherMembers);
        }
    }
    
    //Creates a String which is a textual representation of the Project object.    

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return (theName + "," + teamSize + "," + printTeamList() + "," + printVotesFile() + "\n");
    }

    /**
     *
     * @return
     */
    public String getTheName()
    {
        return theName;
    }

    /**
     *
     * @return
     */
    public Integer getTheTeamSize()
    {
        return teamSize;
    }

    /**
     *
     * @return
     */
    public Set<String> getTeamList()
    {
        return teamList;
    }
  
    //Prints the team names in the required format to be stored in the file
    
    /**
     *
     * @return
     */
    public String printTeamList()
    {
        String theList = "";
        String comma = ",";
        for (String memberName : teamList)
        {
            theList += (memberName + comma);
        }
    
        //Removes comma at the end of the team list    
        return theList.substring(0, theList.length() -1);    
    }

    /**
     *
     * @return
     */
    public LinkedHashMap<String, LinkedHashMap<String, Integer>> getVotes()
    {
        return votes;
    }

    //Returns all other members for a given member name

    /**
     *
     * @param memberName
     * @return
     */
    public Set<String> otherMembers(String memberName)
    {
        return votes.get(memberName).keySet();
    }

    //Sets each members votes into the hashmap
    
    /**
     *
     * @param teamMember
     * @param teamMemberVotes
     */
    public void recordVotes(String teamMember, LinkedHashMap<String, Integer> teamMemberVotes)
    {
        votes.put(teamMember, teamMemberVotes);
    }

    //Prints the votes out in the required format to be stored in the file
    
    /**
     *
     * @return
     */
    public String printVotesFile()
    { 
        String voteString = "";
        String comma = ",";
        for (Map.Entry<String,LinkedHashMap<String, Integer>> pair : votes.entrySet()) 
        {
            String voterName = pair.getKey();
            voteString += (voterName + comma);        
            LinkedHashMap<String, Integer> memberVotes = pair.getValue();
            for (Map.Entry<String, Integer> memberVote : memberVotes.entrySet()) 
            {
                String votedMemberName = memberVote.getKey();
                String votePoint = memberVote.getValue().toString();
                voteString += (votedMemberName + comma + votePoint + comma);            
            }
        }
    
        //Removes comma at the end of the vote string  
        return voteString.substring(0, voteString.length() -1);   
    }   
    
    //Prints the votes out for each member so user can view them 
    public void printVotesDisplay()
    {
        {
            System.out.println("\nVotes for the project: " + theName);
            for (Map.Entry<String,LinkedHashMap<String, Integer>> pair : getVotes().entrySet()) 
            {
                String voterName = pair.getKey();
                System.out.println("\nThese are " + voterName + "'s votes:\n");       
                LinkedHashMap<String, Integer> memberVotes = pair.getValue();
                for (Map.Entry<String, Integer> memberVote : memberVotes.entrySet()) 
                {
                    String votedMemberName = memberVote.getKey();
                    String votePoint = memberVote.getValue().toString();
                    System.out.println("\t" + votedMemberName + ": " + votePoint + " points");            
                }
            }
        }        
    }
    
    //Rebuilds entire project from line of data
    
    /**
     *
     * @param projectName
     * @param projectLine
     * @param projectFields
     * @return
     */
    public static Project buildFromLine(String projectName, String projectLine, String[] projectFields) 
    {  
        projectFields = projectLine.split(",");
        int teamSize = Integer.parseInt(projectFields[1]);
        LinkedHashSet<String> teamList = new LinkedHashSet<>();
        for (int i = 2; i < 2 + teamSize; i++)
        {
            teamList.add(projectFields[i]);
        }

        Project existingProject = new Project(projectName, teamSize, teamList);
        int currentIndex = 1 + teamSize;
        for (int i = 0; i < teamSize; i++)
        {
            currentIndex += 1;
            String voterName = projectFields[currentIndex];
            LinkedHashMap<String,Integer> votes = new LinkedHashMap<>();
            for (int j = 0; j < teamSize - 1; j++) 
            {
                String votedFor = projectFields[currentIndex + 1];
                Integer votePoint = Integer.parseInt(projectFields[currentIndex + 2]);
                votes.put(votedFor, votePoint);
                currentIndex += 2;
            }

            existingProject.recordVotes(voterName, votes);
        }

        return existingProject;
    }    
}
