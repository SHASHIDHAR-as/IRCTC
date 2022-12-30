import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class Testing {
    String pnrnum;
    static ArrayList<String> Pnrlist=new ArrayList<>();
    public static void main(String args[])
    {
        
        try{
            Conn c=new Conn();
        // ResultSet rs=c.s.
        ResultSet rs=c.s.executeQuery("select * from pnr_status");
        while(rs.next()){
            Pnrlist.add(rs.getString("pnr_no"));
        }
        // System.out.println(Pnrlist);

    }
    catch(Exception error){
        System.out.println(error);
    }
    while(true){
    Random ran = new Random();
    long first7 = (ran.nextLong() % 90000000L) + 2356000000L;
    String Pnrnum = "" + Math.abs(first7);
    if(!Pnrlist.contains(Pnrnum)){
        System.out.println(Pnrnum);
        break;
}
}
}
}