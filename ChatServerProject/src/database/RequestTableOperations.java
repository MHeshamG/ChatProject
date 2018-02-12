
package database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed hesham
 */
public class RequestTableOperations {
       
    public void sendRequest (String senderEmail, String recieverEmail){
        
        try {
            String query;
            query="select "  +  DatabaseContract.UserTableContract.email  + "  " +
                    "from  " +  DatabaseContract.UserTableContract.tableName  + "  " +
                    "where  " +  DatabaseContract.UserTableContract.email + " = " + "'" + senderEmail + "'" ;
            
            ResultSet rs=DatabaseHandler.getInstance().select(query);
            
            if(rs.next())
            {
                
              query="insert into " + DatabaseContract.RequesttableContract.tableName + " "
                       + "values('" + UserTableOperations.emailToId(senderEmail)
                + "','" + UserTableOperations.emailToId(recieverEmail) +
                      "');";
              
              DatabaseHandler.getInstance().insert(query);
                     
                
            }
            else
                
            {
                System.out.println("not a user");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestTableOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       }
    
    
    
    
    
    
    
}
