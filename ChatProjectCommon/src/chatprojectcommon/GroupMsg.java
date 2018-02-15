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
    
    private ArrayList<User> members;
    private String groupName;
    
    public GroupMsg(String groupName){
        this.groupName=groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    
    
    public void addToMembers(User user){
        members.add(user);
    }
    
    public ArrayList<User> getMembers(){
        return members;
    }
}
