import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class AddPassengers1 extends JFrame implements ActionListener {

    JPanel mainPanel=new JPanel();

    JTextField PassengerName, Age;
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
    
    AddPassengers1(BookedTrain details, String user_name) {
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
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

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
        
        JPanel headerPanel=new JPanel();
        String content="<html><p >PASSENGER DETAILS</p><br> </html>";
        JLabel header=new JLabel(content,JLabel.CENTER);
        header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        headerPanel.setBackground(Color.decode("#e87020"));
        header.setForeground(Color.white);
        header.setFont(new Font("Raleway", Font.BOLD, 20));
        headerPanel.add(header);
        headerPanel.setMaximumSize( new Dimension(  983, 200) );
        mainPanel.add(headerPanel);

        //show selected train
        JPanel selectedTrainpanel=new JPanel();

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/selectedTrain.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 200);
        mainPanel.add(image);

        JLabel trainLabel=new JLabel(details.train_name.toUpperCase()+" ("+details.train_no+")");
        trainLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        trainLabel.setBounds(220,10,550,40);
        trainLabel.setBackground(Color.white);
        trainLabel.setOpaque(true);

        JLabel sourcLabel=new JLabel(sourceName.toUpperCase());
        sourcLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        sourcLabel.setBounds(220,80,200,40);
        sourcLabel.setBackground(Color.white);
        sourcLabel.setOpaque(true);

        JLabel arrivalLabel=new JLabel(details.arrivalTime);
        arrivalLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        arrivalLabel.setBounds(220,110,100,30);
        arrivalLabel.setBackground(Color.white);
        arrivalLabel.setOpaque(true);

        JLabel destinLabel=new JLabel(destinationName.toUpperCase());
        destinLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        destinLabel.setBounds(585,80,200,40);
        destinLabel.setBackground(Color.white);
        destinLabel.setOpaque(true);

        JLabel reachLabel=new JLabel(details.destinationTime);
        reachLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        reachLabel.setBounds(585,110,100,30);
        reachLabel.setBackground(Color.white);
        reachLabel.setOpaque(true);

        JLabel costLabel=new JLabel(details.cost+"");
        costLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        costLabel.setBounds(280,142,100,30);
        costLabel.setBackground(Color.white);
        costLabel.setOpaque(true);

        JLabel seatsLabel=new JLabel(details.seatsAvailable+"");
        seatsLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        seatsLabel.setBounds(650,140,100,30);
        seatsLabel.setBackground(Color.white);
        seatsLabel.setOpaque(true);

        image.add(trainLabel);
        image.add(sourcLabel);
        image.add(destinLabel);
        image.add(arrivalLabel);
        image.add(reachLabel);
        image.add(costLabel);
        image.add(seatsLabel);
        
        selectedTrainpanel.add(image);
        mainPanel.add(selectedTrainpanel);

        JPanel passengers=new JPanel();

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("img/addPassengers.png"));
        Image i22 = i11.getImage().getScaledInstance(983, 230, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel image1 = new JLabel(i33);
        image1.setBounds(0, 0, 983, 230);
        passengers.add(image1);

        PassengerName = new JTextField("Passenger Name");
        PassengerName.setBounds(125, 70, 360, 30);
        PassengerName.setBorder(null);
        PassengerName.setForeground(Color.decode("#a0a0a0"));
        PassengerName.setFont(new Font("Raleway", Font.PLAIN, 16));
        TextAnimator.textAnimator(PassengerName,"Passenger Name");
        image1.add(PassengerName);
        
        // TO ADD AGE
        Age = new JTextField("Age");
        Age.setBounds(125, 120, 360, 30);
        Age.setBorder(null);
        Age.setForeground(Color.decode("#a0a0a0"));
        Age.setFont(new Font("Raleway", Font.PLAIN, 16));
        TextAnimator.textAnimator(Age,"Age");
        image1.add(Age);

        // to select the gender

        male = new JRadioButton("Male");
        male.setBounds(600, 80, 60, 30);
        male.setForeground(Color.decode("#5b5b5b"));
        male.setFont(new Font("Raleway", Font.PLAIN, 16));
        male.setBackground(Color.white);
        image1.add(male);

        female = new JRadioButton("Female");
        female.setBounds(680, 80, 90, 30);
        female.setForeground(Color.decode("#5b5b5b"));
        female.setFont(new Font("Raleway", Font.PLAIN, 16));
        female.setBackground(Color.white);
        image1.add(female);

        other = new JRadioButton("Other");
        other.setBounds(770, 80, 90, 30);
        other.setForeground(Color.decode("#5b5b5b"));
        other.setFont(new Font("Raleway", Font.PLAIN, 16));
        other.setBackground(Color.white);
        image1.add(other);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        // to add button ADD

        Add = new JButton("ADD");
        Add.setBounds(122, 185, 70, 30);
        Add.setFont(new Font("Raleway", Font.BOLD, 20));
        Add.setForeground(Color.decode("#E87020"));
        Add.setBackground(Color.white);
        Add.setBorder(null);
        Add.setOpaque(true);
        Add.addActionListener(this);
        image1.add(Add);

        delete = new JButton("DELETE");
        delete.setBounds(400, 190, 100, 30);
        delete.setFont(new Font("Raleway", Font.BOLD, 20));
        delete.setForeground(Color.decode("#E87020"));
        delete.setBackground(Color.white);
        delete.setBorder(null);
        delete.setOpaque(true);
        delete.addActionListener(this);
        image1.add(delete);

        mainPanel.add(passengers);

        tablePanel=new JPanel();
        //to add table
        cols = new String[] { "Name", "AGE", "GENDER", "PNR", "Ticket cost" };

        model = (DefaultTableModel) table.getModel();

        model.setColumnIdentifiers(cols);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        // table.setFillsViewportHeight(true);
        // table.getColumnModel().getColumn().setPre
        table.getTableHeader().setBackground(Color.white);
        table.setOpaque(true);

        pane = new JScrollPane(table);
        pane.setBounds(0, 0, 983, 200);
        tablePanel.add(pane);

        mainPanel.add(tablePanel);
        // tablePanel.setVisible(false);

        JPanel buttons=new JPanel();
        GridLayout layout = new GridLayout(1,2);
        buttons.setLayout(layout);

        submit = new JButton("SUBMIT");
        submit.setFont(new Font("Raleway", Font.BOLD, 24));
        submit.setForeground(Color.decode("#E87020"));
        submit.setBackground(Color.black);
        submit.setBorder(null);
        submit.setOpaque(false);
        submit.addActionListener(this);
        buttons.add(submit);
        
        back=new JButton("BACK");
        back.setFont(new Font("Raleway", Font.BOLD, 24));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.black);
        back.setBorder(null);
        back.setOpaque(false);
        back.addActionListener(this);
        buttons.add(back);
        
        mainPanel.add(buttons);

        add(mainPanel);

        getContentPane().setBackground(Color.white);
        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Add) {
            tablePanel.setVisible(true);
            // model.setVisible(true);
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
        new AddPassengers1(details, "shas");
    }
}