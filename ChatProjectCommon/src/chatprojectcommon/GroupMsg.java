/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprojectcommon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mohamed hesham
 */
public class GroupMsg implements Serializable{
    
    private ArrayList<String> members;
    private String groupName;
    
    public GroupMsg(String groupName){
        members=new ArrayList<>();
        this.groupName=groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    
    
    public void addToMembers(String user){
        members.add(user);
    }
    
    public ArrayList<String> getMembers(){
        return members;
    }
}
