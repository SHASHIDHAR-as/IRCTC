// import java.awt.Color;
// import java.awt.event.*;
// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;


// public class Work extends JFrame{
//                 JTextField JT_fname,JT_lname,JT_age,JT_id;
//                 JButton btn_add;
//                 JTable table = new JTable();
//                 JScrollPane pane;
//                 Object[] cols = null;
//  public Work(){  
//     Container cd= getContentPane();
//     JPanel panel1 = new JPanel();
//     panel1.setLayout(null);
//     JLabel label = new JLabel("To show train selected");
//     label.setBounds(10, 50, 200, 50);
//     panel1.add(label);
//     panel1.setBackground(Color.yellow);
//     panel1.setBounds(0, 0, 1000, 100);
//     cd.add(panel1);
    
//         Container c=getContentPane();
//         JPanel panel = new JPanel();
//         panel.setLayout(null);
//             panel.setBackground(Color.blue);
//         panel.setBounds(0, 100, 1000, 400);
//      JT_id = new JTextField(10);
//      JT_fname = new JTextField(10);
//      JT_lname = new JTextField(10);
//      JT_age = new JTextField(10);
//      JT_id.setBounds(130,20,150,20);
//      JT_fname.setBounds(130, 50, 150, 20);
//      JT_lname.setBounds(130, 80, 150, 20);
//      JT_age.setBounds(130, 110, 150, 20);
//      btn_add = new JButton("ADD");
//      btn_add.setBounds(300, 80, 100, 20);

//        cols = new String[]{"id","fname","lname","age"};

//             DefaultTableModel model = (DefaultTableModel) table.getModel();

//             model.setColumnIdentifiers(cols);

//       //Add A Row To JTable From JTextfields
//        btn_add.addActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {

//               model.addRow(new Object[]{
//                                JT_id.getText(),JT_fname.getText(),
//                                JT_lname.getText(),JT_age.getText()
//                                        });
//         }
//  });
     
       
       
//      pane = new JScrollPane(table);
//      pane.setBounds(100,150,300,100);

       
//      setLayout(null);
  
//      panel.add(pane);
//      panel.add(JT_id);
//      panel.add(JT_fname);
//      panel.add(JT_lname);
//      panel.add(JT_age);
//      panel.add(btn_add);
//      c.add(panel);
     
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      setVisible(true);
//      Color c1= Color.decode("#bdb76b");
//      getContentPane().setBackground(c1);
//      setLocationRelativeTo(null);
//      setSize(1000,700);
//     //  setLayout(null);
//      setLocation(180, 20);
    
//  }
//    public static void main(String[] args){
//        new  Work();
//    }
// }

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Worker extends JFrame implements ActionListener {
    JTextField PassengerName, Age;
    JRadioButton male, female, other;
    JButton Add, back, delete,submit;
    JTable table = new JTable();
    JScrollPane pane;
    Object[] cols = null;
    DefaultTableModel model;
    static Box vertical = Box.createVerticalBox();

    
    Worker(BookedTrain details) {
        
        // String train_name=details.train_name;
        int train_no=details.train_no;
        String train_name=details.train_name;
        String source=details.source;
        String destination=details.destination;
        String arrivalTime=details.arrivalTime;
        String destinationTime=details.destination;
        // System.out.println(train_name);
        setTitle("IRCTC");
        // to show the selectd train
        Container c = getContentPane();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel label = new JLabel(train_no+" "+train_name+" "+source+" "+destination+" "+arrivalTime+" "+destinationTime);
        label.setBounds(100, 50, 200, 50);
        panel.add(label);
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
        panel2.add(PassengerName);

        // TO ADD AGE
        Age = new JTextField("Age");
        Age.setBounds(10, 90, 200, 30);
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

        cols = new String[] { "Name", "AGE", "GENDER" };

        model = (DefaultTableModel) table.getModel();

        model.setColumnIdentifiers(cols);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        // Add A Row To JTable From JTextfields
        // Add.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String gender = null;
        //         if (male.isSelected())
        //             gender = "Male";
        //         else if (female.isSelected())
        //             gender = "Female";
        //         else if (other.isSelected())
        //             gender = "Other";
        //         if (PassengerName.getText().equals("") || Age.getText().equals("") || gender == null) {
        //             JOptionPane.showMessageDialog(null, "Please fill all the details");
        //         } else {
        //             model.addRow(new Object[] { PassengerName.getText(), Age.getText(), gender });
        //         }
        //     }
        // });
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
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Add){
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
                    model.addRow(new Object[] { PassengerName.getText(), Age.getText(), gender });
        }
    }
    else if(e.getSource()==delete){
        if(table.getSelectedRow() != -1) {
            // remove selected row from the model
            model.removeRow(table.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
    }
    }
    else if(e.getSource()==back){
        setVisible(false);
        new SearchTrains().setVisible(true);
    }
    else if(e.getSource()==submit){
        try{
            Conn c=new Conn();
            int rows=table.getRowCount();

    for(int row = 0; row<rows; row++)
    {   
        String PName = (String)table.getValueAt(row, 0);
        String age = (String) table.getValueAt(row, 1);
        String gen = (String)table.getValueAt(row, 2);
        String query = "Insert into Passenger values ('"+PName+"','"+age+"','"+gen+"')";
        c.s.executeUpdate(query);
        // System.out.println(PName+ " "+age+ " "+gen);
    }
    JOptionPane.showMessageDialog(null, "Successfully Saved");
    

            setVisible(false);

    }catch(Exception error){
        System.out.println(error);
    }
    }
    }

    public static void main(String[] args) {
        BookedTrain details =new BookedTrain(11, "sha","sh","df","sd","sd",10);
        new Worker(details);
    }
}

