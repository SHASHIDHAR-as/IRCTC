import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class Addpassengers extends JFrame implements ActionListener {
    JTextField PassengerName, Age;
    JPanel panel;
    JRadioButton male, female, other;
    JButton Add, back, delete, submit;
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    JScrollPane pane;
    Object[] cols = null;
    int train_no, seats = 0,start = 0, end = 0,cost,pass_num = 0;
    String Pnrnum,train_name,source,destination,arrivalTime,destinationTime;
    String sourceName,destinationName;
    String user_name;
    int seatsAvailable;
    ArrayList<String> Pnrlist=new ArrayList<String>();
    JPanel tablePanel;
    
    Addpassengers(BookedTrain details, String user_name) {
        this.user_name = user_name;
        
        train_no = details.train_no;
        train_name = details.train_name;
        source = details.source;
        destination = details.destination;
        arrivalTime = details.arrivalTime;
        destinationTime = details.destination;
        cost = details.cost;
        seatsAvailable = details.seatsAvailable;
        
        setTitle("IRCTC");
        setLayout(null);

        //to generate pnr number
        try{
            Conn c=new Conn();
            // ResultSet rs=c.s.
            ResultSet rs=c.s.executeQuery("select * from pnr_status");
            while(rs.next()){
                Pnrlist.add(rs.getString("pnr_no"));
            }
            // System.out.println(Pnrlist);
            rs=c.s.executeQuery("select station_name from station where station_id='"+source+"';");
            if(rs.next()){
                sourceName=rs.getString("station_name");
                System.out.println(sourceName);
            }

            rs=c.s.executeQuery("select station_name from station where station_id='"+destination+"';");
            if(rs.next()){
                destinationName=rs.getString("station_name");
                System.out.println(destinationName);
            }
        }
        catch(Exception error){
            System.out.println(error);
        }
        while(true){
            Random ran = new Random();
            long first7 = (ran.nextLong() % 90000000L) + 2356000000L;
            Pnrnum = "" + Math.abs(first7);
            if(!Pnrlist.contains(Pnrnum)){
                System.out.println(Pnrnum);
                break;
            }
        }
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/addPassengers.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        JLabel trainLabel=new JLabel(details.train_name.toUpperCase()+" ("+details.train_no+")");
        trainLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        trainLabel.setBounds(140,100,550,40);
        trainLabel.setBackground(Color.white);
        trainLabel.setOpaque(true);

        JLabel sourcLabel=new JLabel(sourceName.toUpperCase());
        sourcLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        sourcLabel.setBounds(145,165,200,40);
        sourcLabel.setBackground(Color.white);
        sourcLabel.setOpaque(true);

        JLabel arrivalLabel=new JLabel(details.arrivalTime);
        arrivalLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        arrivalLabel.setBounds(145,200,100,30);
        arrivalLabel.setBackground(Color.white);
        arrivalLabel.setOpaque(true);

        JLabel destinLabel=new JLabel(destinationName.toUpperCase());
        destinLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        destinLabel.setBounds(510,165,200,40);
        destinLabel.setBackground(Color.white);
        destinLabel.setOpaque(true);

        JLabel reachLabel=new JLabel(details.destinationTime);
        reachLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        reachLabel.setBounds(510,200,100,30);
        reachLabel.setBackground(Color.white);
        reachLabel.setOpaque(true);

        JLabel costLabel=new JLabel(details.cost+"");
        costLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        costLabel.setBounds(208,223,100,30);
        costLabel.setBackground(Color.white);
        costLabel.setOpaque(true);

        JLabel seatsLabel=new JLabel(details.seatsAvailable+"");
        seatsLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        seatsLabel.setBounds(575,223,100,30);
        seatsLabel.setBackground(Color.white);
        seatsLabel.setOpaque(true);

        image.add(trainLabel);
        image.add(sourcLabel);
        image.add(destinLabel);
        image.add(arrivalLabel);
        image.add(reachLabel);
        image.add(costLabel);
        image.add(seatsLabel);

        PassengerName = new JTextField("Passenger Name");
        PassengerName.setBounds(130, 325, 360, 30);
        PassengerName.setBorder(null);
        PassengerName.setForeground(Color.decode("#a0a0a0"));
        PassengerName.setFont(new Font("Raleway", Font.PLAIN, 16));
        TextAnimator.textAnimator(PassengerName,"Passenger Name");
        image.add(PassengerName);
        
        // TO ADD AGE
        Age = new JTextField("Age");
        Age.setBounds(130, 375, 360, 30);
        Age.setBorder(null);
        Age.setForeground(Color.decode("#a0a0a0"));
        Age.setFont(new Font("Raleway", Font.PLAIN, 16));
        TextAnimator.textAnimator(Age,"Age");
        image.add(Age);

        // to select the gender

        male = new JRadioButton("Male");
        male.setBounds(600, 340, 60, 30);
        male.setForeground(Color.decode("#5b5b5b"));
        male.setFont(new Font("Raleway", Font.PLAIN, 16));
        male.setBackground(Color.white);
        image.add(male);

        female = new JRadioButton("Female");
        female.setBounds(680, 340, 90, 30);
        female.setForeground(Color.decode("#5b5b5b"));
        female.setFont(new Font("Raleway", Font.PLAIN, 16));
        female.setBackground(Color.white);
        image.add(female);

        other = new JRadioButton("Other");
        other.setBounds(770, 340, 90, 30);
        other.setForeground(Color.decode("#5b5b5b"));
        other.setFont(new Font("Raleway", Font.PLAIN, 16));
        other.setBackground(Color.white);
        image.add(other);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        // to add button ADD

        Add = new JButton("ADD");
        Add.setBounds(130, 440, 70, 30);
        Add.setFont(new Font("Raleway", Font.BOLD, 20));
        Add.setForeground(Color.decode("#E87020"));
        Add.setBackground(Color.white);
        Add.setBorder(null);
        Add.setOpaque(true);
        Add.addActionListener(this);
        image.add(Add);

        delete = new JButton("DELETE");
        delete.setBounds(410, 440, 100, 30);
        delete.setFont(new Font("Raleway", Font.BOLD, 20));
        delete.setForeground(Color.decode("#E87020"));
        delete.setBackground(Color.white);
        delete.setBorder(null);
        delete.setOpaque(true);
        delete.addActionListener(this);
        image.add(delete);
    
        panel=new JPanel(new BorderLayout());
        panel.setBounds(0,328,1000,360);
        panel.setLayout(null);
        panel.setBackground(Color.white);
        //to add table
        cols = new String[] { "Name", "AGE", "GENDER", "PNR", "Ticket cost" };

        model = (DefaultTableModel) table.getModel();

        model.setColumnIdentifiers(cols);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTableHeader Theader=table.getTableHeader();
        Theader.setBackground(Color.decode("#e87020"));
        Theader.setFont(new Font("Raleway", Font.BOLD, 20));
        table.setFont(new Font("Raleway", Font.PLAIN, 16));

        pane = new JScrollPane(table);
        pane.setBounds(100, 150, 800, 120);
        panel.add(pane,BorderLayout.CENTER);

        submit = new JButton("SUBMIT");
        submit.setBounds(200,280,100,30);
        submit.setFont(new Font("Raleway", Font.BOLD, 24));
        submit.setForeground(Color.decode("#E87020"));
        submit.setBackground(Color.black);
        submit.setBorder(null);
        submit.setOpaque(false);
        submit.setVisible(false);
        submit.addActionListener(this);
        panel.add(submit);
        
        back=new JButton("BACK");
        back.setBounds(600,280,100,30);
        back.setFont(new Font("Raleway", Font.BOLD, 24));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.black);
        back.setBorder(null);
        back.setVisible(false);
        back.setOpaque(false);
        panel.add(back);

        panel.setVisible(false);
        image.add(panel);

        getContentPane().setBackground(Color.white);
        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Add) {
            String gender = null;
            if (male.isSelected())
                gender = "Male";
            else if (female.isSelected())
                gender = "Female";
            else if (other.isSelected())
                gender = "Other";

            if (PassengerName.getText().equals("") || Age.getText().equals("") || gender == null) {
                JOptionPane.showMessageDialog(null, "Please fill all the details");
            } else {
                panel.setVisible(true);
                submit.setVisible(true);
                back.setVisible(true);
                model.addRow(new Object[] { PassengerName.getText(), Age.getText(), gender, Pnrnum, cost });
                pass_num++;
                seats++;
            }
        } else if (e.getSource() == delete) {
            if (table.getSelectedRow() != -1) {
                // remove selected row from the model
                model.removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                pass_num--;
                seats--;
            }
        } else if (e.getSource() == back) {
            
            setVisible(false);
            new SearchTrains(user_name).setVisible(true);
        } else if (e.getSource() == submit) {

            try {
                Conn c = new Conn();
                int rows = table.getRowCount();
                System.out.println(rows);
                ResultSet rs = c.s.executeQuery("select * from trains where train_no ='" + train_no + "'");
                if (rs.next()) {
                    start = rs.getInt("start_seat");
                    end = rs.getInt("end_seat");

                    int avail = end - start;
                    if (avail >= pass_num) {

                        int total = pass_num * cost;
                        System.out.println("Total cost" + total);
                        System.out.println(seats);
                        for (int row = 0; row < rows; row++) {
                            String PName = (String) table.getValueAt(row, 0);
                            String age = (String) table.getValueAt(row, 1);
                            String gen = (String) table.getValueAt(row, 2);
                            String Pnr = (String) table.getValueAt(row, 3);

                            String query = "Insert into Passenger(Name,Age,gender,pnr_num,seat_no) values ('" + PName
                                    + "','" + age + "','" + gen + "','" + Pnr + "','" + (++start) + "')";
                            c.s.executeUpdate(query);
                            // System.out.println(PName+ " "+age+ " "+gen+" "+Pnrnum);
                        }
                        JOptionPane.showMessageDialog(null, "Successfully Saved");
                        String query1 = "Update Trains set start_seat='" + start + "' where train_no='" + train_no
                        + "'";
                        c.s.executeUpdate(query1);
                        setVisible(false);
                        // new
                        // ConfirmBooking(train_no,train_name,source,destination,arrivalTime,destinationTime,Pnrnum,total,seats,user_name).setVisible(true);

                        BookedTrain details = new BookedTrain(train_no, train_name, source, destination, arrivalTime,destinationTime, total, seatsAvailable);
                        new ConfirmBooking(details, Pnrnum, user_name, seats);

                    } else {
                        JOptionPane.showMessageDialog(null, "No seats available \n only  " + avail + " are available");
                        // setVisible(false);
                    }

                    // System.out.println(avail+"seats");
                    // setVisible(false);
                }
            } catch (Exception error) {
                System.out.println(error);
            }
        }
    }

    public static void main(String[] args) {
        BookedTrain details = new BookedTrain(100, "hampi", "ksr", "ypr", "sd", "sd", 10, 10);
        new Addpassengers(details, "shas");
    }
}