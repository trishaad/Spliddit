/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author trishaadbsurty
 */
public class Project {
    
private String theName;
private int teamSize;
private Set<String> teamList;
private HashMap<String, HashMap<String, Integer>> votes;


Project (String teamName, int memberSize, Set<String> nameList)
{
    theName = teamName;
    teamSize = memberSize;
    teamList = nameList;
    
    votes = new HashMap<String, HashMap<String, Integer>>();
    
    for (String memberName: nameList) {
        HashMap<String, Integer> otherMembers = new HashMap<String, Integer>();
        
        for (String otherMemberName: nameList) {
            if (!otherMemberName.equals(memberName)) {
                otherMembers.put(otherMemberName, 0);
            }
        }
        
        votes.put(memberName, otherMembers);
    }
}

 //Creates a String which is a textual representation of the Project object.
    
@Override
public String toString()
{
    
     return (theName + ","
             + teamSize + ","
             + printTeamList() + ","
             + printVotes() + "\n");
}

public String getTheName()
{
    return theName;
}

public Integer getTheTeamSize()
{
    return teamSize;
}

public Set<String> getTeamList()
{
    return teamList;
}
  
public String printTeamList()
{
            String theList = "";
            String comma = ",";
        
        for (String memberName : teamList)
        {
            theList += (memberName + comma);
        }
    //removes comma at the end of the team list    
    return theList.substring(0, theList.length() -1);    
}

public HashMap<String, HashMap<String, Integer>> getVotes()
{
    return votes;
}

public Set<String> otherMembers(String memberName){
    return votes.get(memberName).keySet();
}

public void recordVotes(String teamMember, HashMap<String, Integer> teamMemberVotes)
{
    votes.put(teamMember, teamMemberVotes);
}

public String printVotes()
{ 
    String voteString = "";
    String comma = ",";
    for (Map.Entry<String,HashMap<String, Integer>> pair : votes.entrySet()) 
    {
        String voterName = pair.getKey();
        voteString += (voterName + comma);        
        HashMap<String, Integer> memberVotes = pair.getValue();
        for (Map.Entry<String, Integer> memberVote : memberVotes.entrySet()) 
        {
            String votedMemberName = memberVote.getKey();
            String votePoint = memberVote.getValue().toString();
            voteString += (votedMemberName + comma + votePoint + comma);            
        }
    }
    
   //removes comma at the end of the vote string  
   return voteString.substring(0, voteString.length() -1);   
}
        
}
