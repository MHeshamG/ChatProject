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
    
    }
    
    public void appenedMessage(Message msg){
        messages.add(msg);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
    
}
