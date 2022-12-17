import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;
import java.sql.*;


public class ConfirmBooking extends JFrame implements ActionListener {
    JTextField PassengerName, Age;
    JRadioButton male, female, other;
    JButton Add, back, delete, submit,show,confirm;
    JTable table = new JTable();
    JScrollPane pane;
    Object[] cols = null;
    DefaultTableModel model;
    static Box vertical = Box.createVerticalBox();
    String Pnrnum;
    int train_no,total,seats;
    String train_name ;
        String source;
        String destination ;
        String arrivalTime ;
        String destinationTime ;
        String user_name;
        String timeStamp;

    ConfirmBooking(int train_no,String train_name,String source,String destination,String arrivalTime,String destinationTime,String Pnrnum,int total,int seats,String user_name) {

        this.train_no = train_no;
        this.train_name = train_name;
        this.source = source;
        this.destination = destination;  
        System.out.println(train_no); 
        this.arrivalTime = arrivalTime;
        this.destinationTime = destination;
        this.Pnrnum=Pnrnum;
        this.total=total;
        this.seats=seats;
        this.user_name=user_name;
        // System.out.println(train_name);
        setTitle("IRCTC");
        // to show the selectd train
        Container c = getContentPane();
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label1 = new JLabel("TRAIN SELECTED :");
        label1.setBounds(190, 20, 200, 50);
        panel.add(label1);

        JLabel label = new JLabel(train_no + " " + train_name + " " + source + " " + destination + " " + arrivalTime
                + " " + destinationTime);
        label.setBounds(310, 20, 200, 50);
        panel.add(label);

        JLabel label3 = new JLabel("PNR NUMBER :");
        label3.setBounds(190, 50, 200, 50);
        panel.add(label3);

        JLabel labelN = new JLabel(Pnrnum);
        labelN.setBounds(300, 50, 200, 50);
        panel.add(labelN);

        JLabel label02 = new JLabel("DATE - TIME :");
        label02.setBounds(190, 80, 200, 50);
        panel.add(label02);

        timeStamp = new SimpleDateFormat("dd/MM/yyyy \n HH-mm-ss").format(Calendar.getInstance().getTime());
        JLabel label01 = new JLabel(timeStamp);
        label01.setBounds(300, 80, 300, 50);
        panel.add(label01);

        JLabel label05 = new JLabel("Number of seats booked :");
        label05.setBounds(190, 105, 300, 50);
        panel.add(label05);
        JLabel label06 = new JLabel(seats+" ");
        label06.setBounds(350, 105, 200, 50);
        panel.add(label06);

        JLabel label03 = new JLabel("Total Fare :");
        label03.setBounds(190, 130, 200, 50);
        panel.add(label03);
        JLabel label04 = new JLabel(total+" ");
        label04.setBounds(300, 130, 200, 50);
        panel.add(label04);

        show = new JButton("Show passengers");
        show.setBounds(190, 190, 200, 30);
        show.addActionListener(this);
        panel.add(show);

        panel.setBackground(Color.yellow);
        panel.setBounds(0, 0, 1000, 250);
        c.add(panel);

        Container c3 = getContentPane();
        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBackground(Color.green);
        panel3.setBounds(0, 200, 1000, 800);

        cols = new String[] { "Name", "AGE", "GENDER","PNR" };

        model = (DefaultTableModel) table.getModel();

        model.setColumnIdentifiers(cols);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        pane = new JScrollPane(table);
        pane.setBounds(100, 150, 500, 200);
        panel3.add(pane);

        confirm = new JButton("ConfirmBooking");
        confirm.setBounds(100, 360, 150, 30);
        confirm.addActionListener(this);
        panel3.add(confirm);
        c3.add(panel3);

        back = new JButton("Back");
        back.setBounds(280, 360, 100, 30);
        back.addActionListener(this);
        panel3.add(back);

        c3.add(panel3);
        setLayout(null);
        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == show) {
                try{
                    Conn c = new Conn();
                    // String Pnrnum="2276717745";
                    ResultSet rs = c.s.executeQuery("select * from Passenger where pnr_num = '" + Pnrnum + "'");

                // Printing ID, name, email of customers
                // of the SQL command above
                // System.out.println("Name\t\t\tage\t\tGender\t\tPNR");

                // Condition check
                while (rs.next()) {

                    String name = rs.getString("Name");
                    String age = rs.getString("Age");
                    String Gender = rs.getString("Gender");
                    String pnr = rs.getString("pnr_num");
                    // int Pnr = rs.getInt("pnr_num");
                    System.out.println(name + "\t\t" + age
                            + "\t\t" + Gender+"\t\t"+pnr);
                            model.addRow(new Object[] { name,age,Gender,pnr });
                }
            }
                catch (Exception error) {
                    System.out.println(error);
                }
                }
            
            
        else if (e.getSource() == confirm) {
            try {       int booking_id;

                Random ran = new Random();
                booking_id= ran.nextInt(3000);
                System.out.println("bookin_id"+booking_id);
                        Conn c = new Conn();
                        int rows = table.getRowCount();
                        System.out.println(rows);
        
                        for (int row = 0; row < rows; row++) {
                            // String PName = (String) table.getValueAt(row, 0);
                            // String age = (String) table.getValueAt(row, 1);
                            // String gen = (String) table.getValueAt(row, 2);
                            String Pnr = (String) table.getValueAt(row, 3);
        
                                    String query = "Insert into pnr_status(pnr_no,train_no,train_name,from_station,to_station) values ('"+Pnr+"','"+train_no+"','"+train_name+"','"+source+"','"+destination+"')";

                                    c.s.executeUpdate(query);
                                    //insert into booking
                                    
                                    String query2 = "Insert into bookings(booking_id,pnr_no,user_name,date,ticket_cost) values ('"+booking_id+"','"+Pnr+"','"+user_name+"','"+timeStamp+"','"+total+"')";
                                    c.s.executeUpdate(query2);
                            // System.out.println(PName+ " "+age+ " "+gen+" "+Pnrnum);
                        }
                        JOptionPane.showMessageDialog(null, "Tickets confirmed\n"+"BOOKING ID:"+booking_id);
        
                        // setVisible(false);
        
                    } catch (Exception error) {
                        System.out.println(error);
                    }
        } 

        else if (e.getSource() == back) {
            setVisible(false);
            new HomePage(user_name).setVisible(true);
        
        } 
        
        
        //     setVisible(false);
        //     new SearchTrains().setVisible(true);
        // } else if (e.getSource() == submit) {
        //     try {
        //         Conn c = new Conn();
        //         int rows = table.getRowCount();
        //         System.out.println(rows);

        //         for (int row = 0; row < rows; row++) {
        //             String PName = (String) table.getValueAt(row, 0);
        //             String age = (String) table.getValueAt(row, 1);
        //             String gen = (String) table.getValueAt(row, 2);
        //             String Pnr = (String) table.getValueAt(row, 3);

        //                     String query = "Insert into Passenger(Name,Age,gender,pnr_num) values ('"+PName+"','"+age+"','"+gen+"','"+Pnr+"')";
        //             c.s.executeUpdate(query);
        //             // System.out.println(PName+ " "+age+ " "+gen+" "+Pnrnum);
        //         }
        //         JOptionPane.showMessageDialog(null, "Successfully Saved");

        //         setVisible(false);

            // } catch (Exception error) {
            //     System.out.println(error);
            // }
        // }
    }

    public static void main(String[] args) {
        // BookedTrain details = new BookedTrain(11, "sha", "sh", "df", "sd", "sd", 10);
        new ConfirmBooking(100,"hampi","ksr","ypr","sd","fs","1334",54,2,"shashi");
    }
}