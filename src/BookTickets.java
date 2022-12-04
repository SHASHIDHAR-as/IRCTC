import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.*;

public class BookTickets extends JFrame{

    BookTickets(String source,String destination){

        JPanel panel;
        String source="ypr",destination="bay";
        ArrayList<Integer> train_no=new ArrayList<Integer>();
        ArrayList<String> train_name=new ArrayList<String>();

        setTitle("IRCTC");
        setLayout(null);

        panel=new JPanel();
        panel.setBounds(50,50,700,500);
        add(panel);

        try{
            Conn c=new Conn();


            int trains[]=new int[3];
            ResultSet rs=c.s.executeQuery("select train_no from trains");


            System.out.println("the trains are:");
            for(int i=0;i<3 && rs.next();i++){
                trains[i]=rs.getInt("train_no");
                System.out.println(trains[i]);
            }

            System.out.println("Travelling train is: ");

            for(int i=0;i<trains.length;i++){
                rs=c.s.executeQuery("select * from trains where train_no= (select t.train_no from (select t1.train_no,t1.station_id as source,t2.station_id as destination from `"+trains[i]+"` as t1 cross join `"+trains[i]+"` as t2 where t1.stop_no < t2.stop_no) as t where t.source='"+source+"' and t.destination='"+destination+"');");
                if(rs.next()){
                    train_no.add(rs.getInt("train_no"));
                    train_name.add(rs.getString("train_name"));
                    System.out.println(train_no.get(i)+" "+train_name.get(i));              
                }
            }

            System.out.println("train details are");
            rs=c.s.executeQuery("select t1.time as ,t2.time"+
            " inner join `"+train_no.get(0)+"` on "+
            " trains.train_id = `"+train_no.get(0)+"`.train_id;"
            
            
            
            );
            JLabel label=new JLabel(rs.getString("train_no")+" "+rs.getString("train_name"));
            JPanel newPanel=new JPanel();
            newPanel.setBounds(0,0,50,50);
            newPanel.add(label);
            panel.add(newPanel);
        
        }catch(Exception error){
            error.printStackTrace();
        }

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
    public static void main(String args[])
    {
        new BookTickets();
    }
}
