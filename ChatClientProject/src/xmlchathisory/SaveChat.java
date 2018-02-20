/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlchathisory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import xsdclasses.HistoryType;
import xsdclasses.MessageType;
import xsdclasses.ObjectFactory;
import chatprojectcommon.Message;
import chatprojectcommon.User;

/**
 *
 * @author WIN 7
 */
public class SaveChat {

    public void saveXMLFile(File createdfile, ArrayList<Message> listMsg, User user) {
        try {
              FileOperations  fileOperation=new  FileOperations ();
            System.out.println("welcome from savexml method");
            JAXBContext context = JAXBContext.newInstance("xsdclasses");

            ObjectFactory factory = new ObjectFactory();

            HistoryType historyType = factory.createHistoryType();
            historyType.setSender(user.getName());

            ArrayList<MessageType> messages =(ArrayList<MessageType>) historyType.getMessage();
            
         
            
              for(int i=0;i<listMsg.size();i++)
                    {   
                        MessageType messageType=factory.createMessageType();
                           messageType.setFrom(listMsg.get(i).getFrom());
                           messageType.setTo(listMsg.get(i).getTo()); 
                           messageType.setFont(listMsg.get(i).getFont());
                           messageType.setSize(listMsg.get(i).getFontSize());
                           messageType.setColor(listMsg.get(i).getColor());
                           messageType.setBody(listMsg.get(i).getBody());
                           
                              messages.add(messageType);
                    }
              
            JAXBElement<HistoryType> history = factory.createHistory(historyType);
                   Marshaller marsh=context.createMarshaller();
                    marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                    //marsh.marshal(history, createdfile);
          
                    
               marsh.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "chat.xsd");
           
               
              marsh.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "<?xml-stylesheet type='text/xsl' href='savechat.xsl'?>");
            
            FileOutputStream fileOutputStream = new FileOutputStream(createdfile);
            marsh.marshal(history,fileOutputStream );
            fileOutputStream.close();


            //copyFile(getClass().getResource("/XML/savechat.xsl").openStream(), createdfile.getParent() +"/savechat/.xsl");
            fileOperation.copy(getClass().getResource("savechat.xsl").openStream(), createdfile.getParent() +"/savechat.xsl");
            
            System.out.println(createdfile.getParent());
            fileOperation.copy(getClass().getResource("chat.xsd").openStream(), createdfile.getParent() +"/chat.xsd");
            
            //copyFile(getClass().getResource("/XML/chat.xsd").openStream(), createdfile.getParent() +"/chat.xsd");
            

        } catch (JAXBException | FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

   
   
            
}


