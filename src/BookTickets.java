import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class BookTickets extends JFrame implements ActionListener{
    static Box vertical=Box.createVerticalBox();
    JScrollPane jsp = new JScrollPane(vertical);
    JPanel mainPanel = new JPanel(new FlowLayout());

    
    static ArrayList<JPanel> panels=new ArrayList<JPanel>();
    ArrayList<JButton> book=new ArrayList<JButton>();
    
    BookTickets(String source,String destination,String day){
        
        ArrayList<Integer> trains=new ArrayList<Integer>();
        ArrayList<Integer> train_no=new ArrayList<Integer>();
        ArrayList<String> train_name=new ArrayList<String>();
        ArrayList<String> arrivalTime=new ArrayList<String>();
        ArrayList<String> destinationTime=new ArrayList<String>();
        ArrayList<Integer> costOfTravel=new ArrayList<Integer>();
        
        add(jsp, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        setTitle("IRCTC");

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
                addPanel(label, i);
                
            }

        }catch(Exception error){
            error.printStackTrace();
        }

        // getContentPane().setBackground(Color.white);
        mainPanel.add(vertical,BorderLayout.PAGE_START);

        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    void addPanel(JLabel label,int i){
        JPanel panel=new JPanel(new BorderLayout());
        panels.add(panel);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton button=new JButton("Book");
        button.addActionListener(this);
        book.add(button);
        panel.add(button);
        panel.add(label,BorderLayout.LINE_START);
        vertical.add(panel);
    }
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<book.size();i++){
            if(e.getSource()==book.get(i)){
                
            }
        }
    }
    public static void main(String args[])
    {
        new BookTickets("ypr","bay","tuesday");

    }

}
