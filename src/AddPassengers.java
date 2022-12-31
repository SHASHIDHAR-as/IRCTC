import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class Addpassengers extends JFrame implements ActionListener {
    JTextField PassengerName, Age;
    JRadioButton male, female, other;
    JButton Add, back, delete, submit;
    JTable table = new JTable();
    JScrollPane pane;
    Object[] cols = null;
    DefaultTableModel model;
    static Box vertical = Box.createVerticalBox();
    String Pnrnum;
    int train_no, seats = 0;
    String train_name;
    String source;
    String destination;
    String arrivalTime;
    int start = 0, end = 0;
    String destinationTime;
    int cost;
    int pass_num = 0;
    String user_name;
    int seatsAvailable;
    ArrayList<String> Pnrlist=new ArrayList<String>();

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

        //to generate pnr number
        
        try{
            Conn c=new Conn();
            // ResultSet rs=c.s.
            ResultSet rs=c.s.executeQuery("select * from pnr_status");
            while(rs.next()){
                Pnrlist.add(rs.getString("pnr_no"));
            }
            // System.out.println(Pnrlist);

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

        JLabel label3 = new JLabel("PNR NUMBER : "+Pnrnum);
        label3.setBounds(190, 50, 200, 50);
        panel.add(label3);

        JLabel label4 = new JLabel("SEATS AVAILABLE : "+seatsAvailable);
        label4.setBounds(390, 50, 200, 50);
        panel.add(label4);

        // JLabel labelN = new JLabel(Pnrnum);
        // labelN.setBounds(300, 50, 200, 50);
        // panel.add(labelN);

        panel.setBackground(Color.yellow);
        panel.setBounds(0, 0, 1000, 100);
        c.add(panel);

        Container c2 = getContentPane();
        // Creating a JPanel for the JFrame
        JPanel panel2 = new JPanel();
        // setting the panel layout as null
        panel2.setLayout(null);
        // adding a label element to the panel
        JLabel label2 = new JLabel("ADD PASSENGERS");
        label2.setBounds(10, 0, 200, 50);
        panel2.add(label2);

        // TO ADD PASSENGERNAME

        PassengerName = new JTextField("Passenger Name");
        PassengerName.setBounds(10, 55, 200, 30);
        TextAnimator.textAnimator(PassengerName,"Passenger Name");
        panel2.add(PassengerName);

        // TO ADD AGE
        Age = new JTextField("Age");
        Age.setBounds(10, 90, 200, 30);
        TextAnimator.textAnimator(Age,"Age");
        panel2.add(Age);

        // to select the gender

        male = new JRadioButton("Male");
        male.setBounds(10, 125, 60, 30);
        male.setBackground(Color.white);
        panel2.add(male);

        female = new JRadioButton("Female");
        female.setBounds(80, 125, 90, 30);
        female.setBackground(Color.white);
        panel2.add(female);

        other = new JRadioButton("Other");
        other.setBounds(200, 125, 60, 30);
        other.setBackground(Color.white);
        panel2.add(other);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        // to add button ADD

        Add = new JButton("ADD");
        Add.setBounds(10, 170, 100, 30);
        Add.addActionListener(this);
        panel2.add(Add);

        delete = new JButton("Delete");
        delete.setBounds(120, 170, 100, 30);
        delete.addActionListener(this);
        panel2.add(delete);

        panel2.setBackground(Color.red);
        panel2.setBounds(0, 100, 1000, 220);
        c2.add(panel2);

        Container c3 = getContentPane();
        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBackground(Color.green);
        panel3.setBounds(0, 200, 1000, 800);

        cols = new String[] { "Name", "AGE", "GENDER", "PNR", "Ticket cost" };

        model = (DefaultTableModel) table.getModel();

        model.setColumnIdentifiers(cols);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        pane = new JScrollPane(table);
        pane.setBounds(100, 150, 500, 200);
        panel3.add(pane);

        submit = new JButton("Submit");
        submit.setBounds(100, 360, 100, 30);
        submit.addActionListener(this);
        panel3.add(submit);
        c3.add(panel3);

        back = new JButton("Back");
        back.setBounds(250, 360, 100, 30);
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
                    int seat[] = new int[pass_num];
                    if (avail >= pass_num) {

                        int total = pass_num * cost;
                        System.out.println("Total cost" + total);
                        System.out.println(seats);
                        for (int row = 0; row < rows; row++) {
                            String PName = (String) table.getValueAt(row, 0);
                            String age = (String) table.getValueAt(row, 1);
                            String gen = (String) table.getValueAt(row, 2);
                            String Pnr = (String) table.getValueAt(row, 3);

                            String query = "Insert into Passengers(Name,Age,gender,pnr_no,seat_no) values ('" + PName
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