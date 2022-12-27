import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class AddTrains  extends JFrame implements ActionListener {
    JTextField trainNo,trainName,seats,stopNo,time,cost;
    JComboBox stationId;
    JButton saveTrain,Add, back, delete, submit;
    JTable table = new JTable();
    JScrollPane pane;
    Object[] cols = null;
    DefaultTableModel model;
    String userName;
    String FromStations[];
    String loginId,train_no,train_name,Seats;
    static Box vertical = Box.createVerticalBox();
        
    AddTrains(String loginId) {
        this.loginId=loginId;
        setTitle("IRCTC");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        trainNo=new JTextField("train no");
        trainNo.setBounds(20,20,100,50);
        add(trainNo);

        trainName=new JTextField("train name");
        trainName.setBounds(120,20,100,50);
        add(trainName);

        seats=new JTextField("seats");
        seats.setBounds(240,20,100,50);
        add(seats);

        saveTrain=new JButton("save trian");
        saveTrain.setBounds(50,80,100,30);
        saveTrain.addActionListener(this);
        add(saveTrain);

        Container c2 = getContentPane();
        // Creating a JPanel for the JFrame
        JPanel panel2 = new JPanel();
        // setting the panel layout as null
        panel2.setLayout(null);
        // adding a label element to the panel
        JLabel label2 = new JLabel("ADD stations");
        label2.setBounds(10, 0, 200, 50);
        panel2.add(label2);

        stopNo = new JTextField("stop number");
        stopNo.setBounds(10, 55, 200, 30);
        panel2.add(stopNo);


        try{
            Conn c=new Conn();
            int count=0;
            ResultSet rs=c.s.executeQuery("select count(station_id) as count from station");
            if(rs.next()){
                count=rs.getInt("count");
                System.out.println(count);
                FromStations=new String[count];
                ResultSet rsd=c.s.executeQuery("select station_id from station");
    
                for(int i=0;i<count&&rsd.next();i++){
                    FromStations[i]=rsd.getString("station_id");
                    // System.out.println(FromStations[i]);
                }
            }}catch(Exception error){
                error.printStackTrace();
            }

        stationId = new JComboBox(FromStations);
        stationId.setBounds(10, 90, 200, 29);
        stationId.setFont(new Font("Raleway", Font.BOLD, 15));
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        stationId.setRenderer(listRenderer);
        stationId.setForeground(Color.black);
        stationId.setBackground(Color.white);
        stationId.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setContentAreaFilled(false);
                button.setBorder(null);
                return button;
            }
        });
        panel2.add(stationId);

        time = new JTextField("time");
        time.setBounds(10, 120, 200, 30);
        panel2.add(time);

        cost = new JTextField("cost");
        cost.setBounds(10, 150, 200, 30);
        panel2.add(cost);

        Add = new JButton("ADD");
        Add.setBounds(10, 180, 100, 30);
        Add.addActionListener(this);
        panel2.add(Add);

        delete = new JButton("Delete");
        delete.setBounds(120, 180, 100, 30);
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

        cols = new String[] { "stop number", "station id", "time","cost"};

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
        ArrayList<String> stations=new ArrayList<>();
        try{
            Conn c = new Conn();
            ResultSet rs=c.s.executeQuery("select station_id from station;");
            while(rs.next()){
                stations.add(rs.getString("station_id"));
            }
        }catch(Exception error){
            System.out.println(error);            
        }
        if(e.getSource()==saveTrain){
            train_no=trainNo.getText();
            train_name=trainNo.getText();
            Seats=trainNo.getText();
            JOptionPane.showMessageDialog(null, "train saved successfully");
        }

        if (e.getSource() == Add) {

            if (stopNo.getText().equals("") || stationId.getSelectedItem().equals("") ||  time.getText().equals("") || cost.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill all the details");
            } else {
                if(!stations.contains(stationId.getSelectedItem())){
                    JOptionPane.showMessageDialog(null, "Invalid station id");
                }else{
                    model.addRow(new Object[] { stopNo.getText(), stationId.getSelectedItem(),time.getText() ,cost.getText()});
                }
            }
        } else if (e.getSource() == delete) {
            if (table.getSelectedRow() != -1) {
                // remove selected row from the model
                model.removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Admin(loginId);
        } else if (e.getSource() == submit) {
            try {
                Conn c = new Conn();
                String query1="insert into trains values ("+Integer.parseInt(train_no)+",'"+train_name+"',"+Integer.parseInt(Seats)+");";
                c.s.executeUpdate(query1);
                int rows = table.getRowCount();
                System.out.println(rows);
                for (int row = 0; row < rows; row++) {
                    String stopNo = (String) table.getValueAt(row, 0);
                    String stationId= (String)table.getValueAt(row, 1);
                    String time= (String) table.getValueAt(row, 2);
                    String cost = (String) table.getValueAt(row, 3);
                    String query2= "create table  if not exists `"+train_no+"`("+
                        "train_no int ,"+
                    "stop_no int,"+
                    "station_id varchar(30),"+
                    "time varchar(20),"+
                    "cost int ,"+
                    "foreign key (station_id) references station(station_id),"+
                    "foreign key (train_no) references trains(train_no)"+
                    ");";
                    String query3= "Insert into `"+train_no+"` values ('"+train_no+"','"+stopNo+"','"+stationId+"','"+time+"',"+Integer.parseInt(cost)+");";
                    System.out.println(query2);
                    System.out.println(query3);
                    c.s.executeUpdate(query2);
                    c.s.executeUpdate(query3);
                    }
                
                    JOptionPane.showMessageDialog(null, "train added successfully");
                    setVisible(false);
                    new Admin(loginId);

            } catch (Exception error) {
                System.out.println(error);
            }
        }
    }

    public static void main(String[] args) {
        new AddTrains("11111");
    }    
}
