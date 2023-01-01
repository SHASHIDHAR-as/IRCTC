import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Source;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.sql.*;
import java.io.File;


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
        String email,genOtp;
        boolean buttonPressed = false;
        int seatsAvailable;
        BookedTrain details;

        ConfirmBooking(BookedTrain details,String Pnrnum,String user_name,int seats){
        this.details=details;
        train_no = details.train_no;
        train_name = details.train_name;
        source = details.source;
        destination =details. destination; 
        seatsAvailable=details.seatsAvailable; 
        // System.out.println(train_no); 
        arrivalTime = details.arrivalTime;
        this.destinationTime = destination;
        this.Pnrnum=Pnrnum;
        total=details.cost;
        this.seats=seats;
        this.user_name=user_name;
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

        cols = new String[] { "Name", "AGE", "GENDER","PNR","SEAT_NO" };

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
        
        // if (!buttonPressed) {
        if (e.getSource() == show & !buttonPressed) {
                try{
                    Conn c = new Conn();
                    // String Pnrnum="2276717745";
                    ResultSet rs = c.s.executeQuery("select * from Passenger where pnr_num = '" + Pnrnum + "'");
                while (rs.next()) {

                    String name = rs.getString("Name");
                    String age = rs.getString("Age");
                    String Gender = rs.getString("Gender");
                    String pnr = rs.getString("pnr_num");
                    int Seat_num = rs.getInt("seat_no");
                    model.addRow(new Object[] { name,age,Gender,pnr,Seat_num });
                }
                
            }
                catch (Exception error) {
                    System.out.println(error);
                }
                buttonPressed = true;
                }
            // }
            
            
        else if (e.getSource() == confirm) {
            try {       int booking_id;

                Random ran = new Random();
                booking_id= ran.nextInt(3000);
                System.out.println("bookin_id"+booking_id);
                        Conn c = new Conn();
                        int rows = table.getRowCount();
                        System.out.println(rows);
        
                                    String query = "Insert into pnr_status(pnr_no,train_no,train_name,from_station,to_station) values ('"+Pnrnum+"','"+train_no+"','"+train_name+"','"+source+"','"+destination+"')";

                                    c.s.executeUpdate(query);
                                    //insert into booking
                                    
                                    String query2 = "Insert into bookings(booking_id,pnr_no,user_name,date,ticket_cost) values ('"+booking_id+"','"+Pnrnum+"','"+user_name+"','"+timeStamp+"','"+total+"')";
                                    c.s.executeUpdate(query2);

                        ArrayList<String> details=new ArrayList<String>();
                        details.add("PNR NUM    :"+Pnrnum);
                        details.add("USER NAME  :"+user_name);
                        details.add("SOURCE     :"+source);
                        details.add("DESTINATION:"+destination);
                        details.add("TIME       :"+timeStamp);
                        details.add("BOOKING ID :"+booking_id);
                        details.add("NUM SEATS  :"+String.valueOf(seats));
                        details.add("TOTAL FAIR :"+String.valueOf(total));
                        
                        ResultSet rs = c.s.executeQuery("SELECT email  FROM user_login WHERE user_name='"+user_name+"';");
                        
                        if(rs.next()){
                            email=rs.getString("email");
                            System.out.println(email);
                            genOtp=String.copyValueOf(OTP(4));
                            SendOTP.sendOTP(genOtp,email);
                            String enteredOtp= JOptionPane.showInputDialog("Enter the otp sent to your email to confirm tickets "); 
                            System.out.println(enteredOtp);
                            if(genOtp.equals(enteredOtp)){
                                JOptionPane.showMessageDialog(null,"Your tickets are being confirmed \nwait for 5 seconds");
                                // MailAttachment.sendConfirmation(email,user_name,details);
                                MailAttachment.sendConfirmation(email,user_name,details,"Railway tickets booking confirmation","\n\nYour Train tickets are booked \n\nDETAILS :\n\n");
                                JOptionPane.showMessageDialog(null, "Tickets confirmed \nBooking details are sent to your email\n"+"BOOKING ID:"+booking_id);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Incorrect OTP. please try again"); 
                            }
                        }

                    } catch (Exception error) {
                        System.out.println(error);
                    }
                    setVisible(false);
                    new HomePage(user_name).setVisible(true);
        } 
        else if (e.getSource() == back) {
            try{
                Conn c=new Conn();
                String query="delete from passenger where pnr_num='"+Pnrnum+"';";
                String query1="update trains set start_seat=start_seat-"+seats+ " where train_no="+train_no+";";
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query);
            }catch(Exception error){
                System.out.println(error);
            }
            setVisible(false);
            new Addpassengers(details,user_name).setVisible(true);
        } 


    }
    static char[] OTP(int len)
    {
        System.out.print("You OTP is : ");
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++)
        {
            otp[i] =numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }

    public static void main(String[] args) {
        BookedTrain details = new BookedTrain(11, "sha", "sh", "df", "sd", "sd", 10,10);

        new ConfirmBooking(details,"54654655","shashi",40);
    }
}