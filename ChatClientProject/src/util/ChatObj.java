/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import chatprojectcommon.Message;
import java.util.ArrayList;

/**
 * represents the chat between individuals and groups
 * @author mohamed hesham
 */

public class ChatObj {

    private ArrayList<Message>messages;

    public ChatObj() {
        messages=new ArrayList<Message>();
    }
    
    public void appenedMessage(Message msg){
        if(messages==null){
            System.out.println("yes it is null");
        }
        messages.add(msg);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
    
    public int getSize(){
        return messages.size();
    }
    
}
