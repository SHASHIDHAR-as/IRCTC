import java.sql.*;

public class Conn{
    Connection c;
    Statement s;
    public Conn(){
        try{
            c=DriverManager.getConnection("jdbc:mysql:///irctc","root","SkSb#292817");
            s = c.createStatement();

        } catch(Exception e){
            System.out.println(e);
        }
    }
}