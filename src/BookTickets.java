import javax.swing.*;
import javax.swing.border.Border;

import java.sql.*;
import java.util.ArrayList;
import java.awt.*;

public class BookTickets extends JFrame{
    static Border blackline = BorderFactory.createLineBorder(Color.black);

    BookTickets(String source,String destination,String day){
        
        ArrayList<Integer> trains=new ArrayList<Integer>();
        ArrayList<Integer> train_no=new ArrayList<Integer>();
        ArrayList<String> train_name=new ArrayList<String>();
        ArrayList<String> arrivalTime=new ArrayList<String>();
        ArrayList<String> destinationTime=new ArrayList<String>();
        ArrayList<Integer> costOfTravel=new ArrayList<Integer>();

        setTitle("IRCTC");
        setLayout(null);

        ArrayList<JPanel> trainDetails=new ArrayList<JPanel>();
        JPanel mainPanel = new JPanel(new FlowLayout());
        // mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainPanel.setBounds(10,10,900,500);
        mainPanel.setBackground(Color.BLUE);
        add(mainPanel);

        try{
            Conn c=new Conn();

            ResultSet rs=c.s.executeQuery("select train_no from trains");

            System.out.println("the trains are:");
            for(int i=0;i<3 && rs.next();i++){
                trains.add(rs.getInt("train_no"));
                System.out.println(trains.get(i));
            }

            
            for(int i=0;i<trains.size();i++){
                String query="select * from trains where train_no= (select t.train_no from (select t1.train_no,t1.station_id as source,t2.station_id as destination from `"+trains.get(i)+"` as t1 cross join `"+trains.get(i)+"` as t2 where t1.stop_no < t2.stop_no) as t where t.source='"+source+"' and t.destination='"+destination+"');";
                
                rs=c.s.executeQuery(query);
                if(rs.next()){
                    train_no.add(rs.getInt("train_no"));
                    train_name.add(rs.getString("train_name"));
                }
            }

            for(int i=0;i<train_no.size();i++){
                String query="select distinct(t1.time) as arrival,t2.time as destination, (t2.cost-t1.cost)as cost "+
                " from (`"+ train_no.get(i)+"` as t1, `"+ train_no.get(i)+"` as t2) inner join schedule "+
                "where t1.station_id='"+source+"' and t2.station_id='"+destination+"' and schedule.train_no="+train_no.get(i)+" and schedule."+day+"='y';";

                rs=c.s.executeQuery(query);

                if(rs.next()){
                    arrivalTime.add(rs.getString("arrival"));
                    destinationTime.add(rs.getString("destination"));
                    costOfTravel.add(rs.getInt("cost"));
                }
            }

            //create a train details panel
            for(int i=0;i<arrivalTime.size();i++){
                System.out.println("Travelling trains are: ");
                System.out.println(train_no.get(i)+" "+train_name.get(i));              

                JLabel label=new JLabel(train_no.get(i)+"  "+train_name.get(i)+"  "+source+"  "+destination+"  "+arrivalTime.get(i)+"  "+destinationTime.get(i)+"  "+costOfTravel.get(i));
                trainDetails.add(getPanel(label));
                mainPanel.add(getPanel(label));
            }

        }catch(Exception error){
            error.printStackTrace();
        }

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setLocation(180, 20);
        setVisible(true);
    }
    public static JPanel getPanel(JLabel label){
        JPanel panel=new JPanel();
        panel.setBounds(0,0,50,50);
        panel.setBorder(blackline);
        panel.add(label);
        return panel;
    }
    public static void main(String args[])
    {
        new BookTickets("ypr","bay","sunday");

    }
}
