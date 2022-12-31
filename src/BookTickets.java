import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class BookTickets extends JFrame implements ActionListener{

    String source,destination,userName;

    JPanel mainPanel=new JPanel();
    
    static ArrayList<JPanel> panels=new ArrayList<JPanel>();
    ArrayList<JButton> book=new ArrayList<JButton>();
            
    ArrayList<Integer> trains=new ArrayList<Integer>();
    ArrayList<Integer> train_no=new ArrayList<Integer>();
    ArrayList<String> train_name=new ArrayList<String>();
    ArrayList<String> arrivalTime=new ArrayList<String>();
    ArrayList<String> destinationTime=new ArrayList<String>();
    ArrayList<Integer> costOfTravel=new ArrayList<Integer>();
    ArrayList<Integer> seatsAvailable=new ArrayList<Integer>();
    ArrayList<BookedTrain> details=new ArrayList<BookedTrain>();

    JButton back;
    
    BookTickets(String source,String destination,String day,String userName){
        this.source=source;
        this.destination=destination;
        this.userName=userName;
        
        setTitle("IRCTC");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        try{
            Conn c=new Conn();

            ResultSet rs=c.s.executeQuery("select train_no from trains");

            if(rs==null){
                System.out.println("null");
            }

            System.out.println("the trains are:");
            for(int i=0;rs.next();i++){
                trains.add(rs.getInt("train_no"));
                System.out.println(trains.get(i));
            }
  
            for(int i=0;i<trains.size();i++){
                String query="select * from (select t.train_no,t.train_name,t.start_seat,t.end_seat from (select * from trains where train_no in (select t1.train_no from `"+trains.get(i)+"` as t1 inner join `"+trains.get(i)+"` as t2 where t1.station_id='"+source+"' and t2.station_id='"+destination+"' and t1.stop_no<t2.stop_no)) as t inner join schedule as s where t.train_no =s.train_no and s.wednesday='y') as sample1 inner join (select t1.train_no,t1.time as arrival_time,t2.time as reach_time,(t2.cost-t1.cost) as cost from `"+trains.get(i)+"` as t1 inner join `"+trains.get(i)+"` as t2 inner join schedule as s where t1.station_id='"+source+"' and t2.station_id='"+destination+"' and s.train_no="+trains.get(i)+" and s."+day+"='y' ) as sample2 where sample1.train_no=sample2.train_no;";
                
                System.out.println(query);

                rs=c.s.executeQuery(query);
                if(rs.next()){
                    train_no.add(rs.getInt("train_no"));
                    train_name.add(rs.getString("train_name"));
                    int startSeat=rs.getInt("start_seat");
                    int endSeat=rs.getInt("end_seat");
                    seatsAvailable.add(endSeat-startSeat);
                    arrivalTime.add(rs.getString("arrival_time"));
                    destinationTime.add(rs.getString("reach_time"));
                    costOfTravel.add(rs.getInt("cost"));
                }
            }

            //create a train details panel
            for(int i=0;i<arrivalTime.size();i++){
                System.out.println("Travelling trains are: ");
                System.out.println(train_no.get(i)+" "+train_name.get(i));              
                
                BookedTrain selectedTrain=new BookedTrain(train_no.get(i), train_name.get(i), source, destination, arrivalTime.get(i), destinationTime.get(i), costOfTravel.get(i), seatsAvailable.get(i));
                details.add(selectedTrain);
                // JLabel label=new JLabel(train_no.get(i)+"  "+train_name.get(i)+"  "+source+"  "+destination+"  "+arrivalTime.get(i)+"  "+destinationTime.get(i)+"  "+costOfTravel.get(i)+" "+seatsAvailable.get(i));
                addPanel(selectedTrain, i);

                mainPanel.add(panels.get(i));
                
            }

        }catch(Exception error){
            error.printStackTrace();
        }

        JScrollPane Jscroll = new JScrollPane(mainPanel); 

        setLayout(new BorderLayout());
        add(Jscroll, BorderLayout.CENTER);

        back=new JButton("BACK");
        back.setFont(new Font("Raleway", Font.BOLD, 24));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.black);
        back.setBorder(null);
        back.setOpaque(false);
        back.addActionListener(this);
        mainPanel.add(back);

        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    void addPanel(BookedTrain details,int i){
        String str1="""
            <html><style>
                .heading{
                    width:460px ;
                    height:30px;
                }
            </style>
            <div><div class="heading"><h1> &ensp; &ensp;"""+details.train_name.toUpperCase();                
        String str2="&ensp;("+details.train_no+")</h1>";
        String str3="""
                </div><hr>
                <div ><h2>&emsp; &emsp; """;
        String str4=details.source;
        String str5="&emsp; &emsp; &emsp; &emsp; &emsp; &#8594; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; "+details.destination; 
        String str6="""
            </h2></div><h3> &emsp;&emsp;&emsp; &emsp; &emsp; """;
        String str7=details.arrivalTime;
        String str8="""
            &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp;  &emsp; &emsp; &emsp;&emsp; &emsp; &emsp; &emsp; """;
        String str9=details.destinationTime+"</h3>";
        String str10="<h3> &emsp; &emsp; &emsp; Ticket Cost : "+details.cost+" &emsp; &emsp; Available Seats : "+details.seatsAvailable+"</h3><div></html>";
        String htmlContent=str1+str2+str3+str4+str5+str6+str7+str8+str9+str10;

        JPanel panel=new JPanel();

        JLabel label=new JLabel(htmlContent, JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBackground(Color.white);
        label.setOpaque(true);

        // JButton button=new JButton("BOOK");
        // button.addActionListener(this);
        // book.add(button);

        JButton button=new JButton("BOOK");
        button.setFont(new Font("Raleway", Font.BOLD, 20));
        button.setForeground(Color.decode("#E87020"));
        button.setBackground(Color.black);
        button.setBorder(null);
        button.setOpaque(false);
        button.addActionListener(this);
        book.add(button);

        JPanel subPanel=new JPanel();
        subPanel.add(label);
        subPanel.add(button);
        panel.add(subPanel);

        panels.add(panel);
    }
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<book.size();i++){
            if(e.getSource()==book.get(i)){
                BookedTrain details=new BookedTrain(train_no.get(i),train_name.get(i),source,destination,arrivalTime.get(i),destinationTime.get(i),costOfTravel.get(i),seatsAvailable.get(i));

                setVisible(false);
                new Addpassengers(details,userName).setVisible(true);
            }
        }
        if(e.getSource()==back){
            setVisible(false);
            new SearchTrains(userName);
        }
    }
    public static void main(String args[])
    {
        new BookTickets("ypr","bay","wednesday","suchith");

    }

}