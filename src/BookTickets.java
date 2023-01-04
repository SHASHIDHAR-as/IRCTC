import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class BookTickets extends JFrame implements ActionListener{

    String source,destination,userName,sourceName,destinationName;

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

        JPanel headerPanel=new JPanel();
        String content="<html><p class=\"head\">"+source.toUpperCase()+" TO "+destination.toUpperCase()+"</p><br> </html>";
        JLabel header=new JLabel(content,JLabel.CENTER);
        header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        headerPanel.setBackground(Color.decode("#e87020"));
        header.setForeground(Color.white);
        header.setFont(new Font("Raleway", Font.BOLD, 20));
        headerPanel.add(header);
        headerPanel.setMaximumSize( new Dimension(  983, 200) );
        mainPanel.add(headerPanel);

        try{
            Conn c=new Conn();

            ResultSet rs=c.s.executeQuery("select station_name from station where station_id='"+source+"';");
            if(rs.next()){
                sourceName=rs.getString("station_name");
                System.out.println(sourceName);
            }

            rs=c.s.executeQuery("select station_name from station where station_id='"+destination+"';");
            if(rs.next()){
                destinationName=rs.getString("station_name");
                System.out.println(destinationName);
            }

            rs=c.s.executeQuery("select train_no from trains");

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
        JPanel panel=new JPanel();

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/booktickets.png"));
        Image i2 = i1.getImage().getScaledInstance(580, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        JLabel trainLabel=new JLabel(train_name.get(i).toUpperCase()+" ("+train_no.get(i)+")");
        trainLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        trainLabel.setBounds(25,10,550,40);
        trainLabel.setBackground(Color.white);
        trainLabel.setOpaque(true);

        JLabel sourcLabel=new JLabel(sourceName.toUpperCase());
        sourcLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        sourcLabel.setBounds(25,80,200,40);
        sourcLabel.setBackground(Color.white);
        sourcLabel.setOpaque(true);

        JLabel arrivalLabel=new JLabel(arrivalTime.get(i));
        arrivalLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        arrivalLabel.setBounds(25,110,100,30);
        arrivalLabel.setBackground(Color.white);
        arrivalLabel.setOpaque(true);

        JLabel destinLabel=new JLabel(destinationName.toUpperCase());
        destinLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        destinLabel.setBounds(390,80,200,40);
        destinLabel.setBackground(Color.white);
        destinLabel.setOpaque(true);

        JLabel reachLabel=new JLabel(destinationTime.get(i));
        reachLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        reachLabel.setBounds(390,110,100,30);
        reachLabel.setBackground(Color.white);
        reachLabel.setOpaque(true);

        JLabel costLabel=new JLabel(costOfTravel.get(i)+"");
        costLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        costLabel.setBounds(100,140,100,30);
        costLabel.setBackground(Color.white);
        costLabel.setOpaque(true);

        JLabel seatsLabel=new JLabel(seatsAvailable.get(i)+"");
        seatsLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        seatsLabel.setBounds(100,170,100,30);
        seatsLabel.setBackground(Color.white);
        seatsLabel.setOpaque(true);
        
        JButton button=new JButton("BOOK");
        button.setFont(new Font("Raleway", Font.BOLD, 20));
        button.setForeground(Color.decode("#E87020"));
        button.setBackground(Color.white);
        button.setBounds(480,150,100,30);
        button.setBorder(null);
        button.addActionListener(this);
        book.add(button);

        image.add(button);
        image.add(trainLabel);
        image.add(sourcLabel);
        image.add(destinLabel);
        image.add(arrivalLabel);
        image.add(reachLabel);
        image.add(costLabel);
        image.add(seatsLabel);

        panel.add(image);

        panels.add(panel);

        mainPanel.add(panel);
    }
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<book.size();i++){
            if(e.getSource()==book.get(i)){
                BookedTrain details=new BookedTrain(train_no.get(i),train_name.get(i),source,destination,arrivalTime.get(i),destinationTime.get(i),costOfTravel.get(i),seatsAvailable.get(i));

                setVisible(false);
                new AddPassengers(details,userName).setVisible(true);
            }
        }
        if(e.getSource()==back){

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(false);
            new SearchTrains(userName);
        }
    }
    public static void main(String args[])
    {
        new BookTickets("ye","bay","wednesday","suchith");

    }

}