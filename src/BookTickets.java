import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.*;

public class BookTickets extends JFrame{

    BookTickets(String source,String destination){

        ArrayList<Integer> train_no=new ArrayList<Integer>();
        ArrayList<String> train_name=new ArrayList<String>();
        ArrayList<String> arrivalTime=new ArrayList<String>();
        ArrayList<String> destinationTime=new ArrayList<String>();
        ArrayList<Integer> costOfTravel=new ArrayList<Integer>();

        setTitle("IRCTC");
        setLayout(null);

        ArrayList<JPanel> trainDetails=new ArrayList<JPanel>();
        JPanel mainPanel=new JPanel();
        mainPanel.setBounds(10,10,900,500);
        add(mainPanel);

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

            for(int i=0;i<train_no.size();i++){
                System.out.println("train details are");
                rs=c.s.executeQuery("select t1.time as arrival,t2.time as destination, (t2.cost-t1.cost)as cost "+
                " from `"+ train_no.get(i)+"` as t1, `"+ train_no.get(i)+"` as t2 "+
                "where t1.station_id='"+source+"' and t2.station_id='"+destination+"';");
                if(rs.next()){
                    arrivalTime.add(rs.getString("arrival"));
                    destinationTime.add(rs.getString("destination"));
                    costOfTravel.add(rs.getInt("cost"));
                }
                System.out.println(arrivalTime.get(i)+" "+destinationTime.get(i)+" "+costOfTravel.get(i));
            }

            //create a train details panel
            JLabel label=new JLabel(train_no.get(0)+"  "+train_name.get(0)+"  "+source+"  "+destination+"  "+arrivalTime.get(0)+"  "+destinationTime.get(0)+"  "+costOfTravel.get(0));
            trainDetails.add(getPanel(label));
            mainPanel.add(getPanel(label));


        }catch(Exception error){
            error.printStackTrace();
        }

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
    public static JPanel getPanel(JLabel label){
        JPanel panel=new JPanel();
        panel.setBounds(0,0,200,300);
        panel.setBackground(Color.white);
        panel.add(label);
        return panel;
    }
    public static void main(String args[])
    {
        new BookTickets("ypr","bay");
    }
}
