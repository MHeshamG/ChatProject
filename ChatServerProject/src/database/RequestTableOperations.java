
package database;
import chatprojectcommon.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RequestTableOperations {
       
    private static RequestTableOperations requestTableOperations;
    
    public static RequestTableOperations getInstance(){
        
        if(requestTableOperations==null)
            requestTableOperations=new RequestTableOperations();
        
        return requestTableOperations;
    }
    
    public void sendRequest (String senderEmail, String recieverEmail){
         
            //String query;
            String query2;
            int senderId=0;
            int recieverId=0;
           /* query="select "  +  DatabaseContract.UserTableContract.email  + "  " +
                    "from  " +  DatabaseContract.UserTableContract.tableName  + "  " +
                    "where  " +  DatabaseContract.UserTableContract.email + " = " + "'" + recieverEmail + "'" ;
            System.out.println(query);
            ResultSet rs=DatabaseHandler.getInstance().select(query);
            */
           System.out.println(recieverEmail);
           recieverId=UserTableOperations.getInstance().emailToId(recieverEmail);
           System.out.println(recieverId);
            if(recieverId!=-1)
            {
                //System.out.println("yes rs has next");
                senderId=UserTableOperations.getInstance().emailToId(senderEmail);
                //System.out.println(senderId);
                //recieverId=UserTableOperations.getInstance().emailToId(recieverEmail);
                
                query2="insert into " + DatabaseContract.RequesttableContract.tableName + " " +"(" +
                        DatabaseContract.RequesttableContract.senderId + " , " +
                        DatabaseContract.RequesttableContract.recieverId + " "+ ") "
                        + "values (" + senderId + "," + recieverId + ");" ;
                
                System.out.println(query2);
                DatabaseHandler.getInstance().insert(query2);   
            }
            else
            {
                System.out.println("not a user");
            }
       }
    
    
    
    public void comfirmRequest(String senderEmail, String recieverEmail)
    {
             FreindTableOperations.getInstance().insertFriends(senderEmail, senderEmail);
             deleteRequest(senderEmail, recieverEmail);
    }
    
    public void deleteRequest(String senderEmail, String recieverEmail)       
    {
                String query;
                query="delete from "+DatabaseContract.RequesttableContract.tableName + " "
                        +"where  "+DatabaseContract.RequesttableContract.senderId   + " = "
                        +UserTableOperations.getInstance().emailToId(senderEmail) + " " +"and " +
                        DatabaseContract.RequesttableContract.recieverId   + " = "
                        +UserTableOperations.getInstance().emailToId(recieverEmail);
                
        System.out.println(query);
        DatabaseHandler.getInstance().delete(query);
    }
    public ArrayList<User> getRequestsList(String email){
    
         ArrayList<User> list=new ArrayList<User>();
        try {
           
            int userId=UserTableOperations.getInstance().emailToId(email);
            
            String query;
            query="select * from "+DatabaseContract.UserTableContract.tableName
                    + " where "+DatabaseContract.UserTableContract.id+  " in ("
                    +"select recieverId from requests where "+DatabaseContract.RequesttableContract.senderId+"="+userId+");";
            
            System.out.println(query);
            ResultSet rs=DatabaseHandler.getInstance().select(query);
            
            while(rs.next()){
                User user = new User();
                        user.setName(rs.getString(DatabaseContract.UserTableContract.name));
                        user.setUserName(rs.getString(DatabaseContract.UserTableContract.userName));
                        user.setGender(rs.getString(DatabaseContract.UserTableContract.gender));
                        user.setEmail(rs.getString(DatabaseContract.UserTableContract.email));
                list.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RequestTableOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return list;
    }
}
