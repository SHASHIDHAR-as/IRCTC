import java.sql.ResultSet;
import java.util.ArrayList;

public class Test {
    Test(){
        String source="ypr",destination="bay";
        ArrayList<Integer> trains=new ArrayList<Integer>();
        ArrayList<Integer> train_no=new ArrayList<Integer>();
        ArrayList<String> train_name=new ArrayList<String>();
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select train_no from trains");

            System.out.println("the trains are:");
            for(int i=0;i<3 && rs.next();i++){
                trains.add(rs.getInt("train_no"));
                System.out.println(trains.get(i));
            }

            System.out.println("Travelling train is: ");
            for(int i=0;i<3;i++){
                String query="select * from trains where train_no= (select t.train_no from (select t1.train_no,t1.station_id as source,t2.station_id as destination from `"+trains.get(i)+"` as t1 cross join `"+trains.get(i)+"` as t2 where t1.stop_no < t2.stop_no) as t where t.source='"+source+"' and t.destination='"+destination+"');";
                rs=c.s.executeQuery(query);
                if(rs.next()){
                    train_no.add(rs.getInt("train_no"));
                    train_name.add(rs.getString("train_name"));
                    // System.out.println(train_no.get(i)+" "+train_name.get(i));              
                }
            }
            System.out.println(train_no);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String args[])
    {
        new Test();
    }
}
