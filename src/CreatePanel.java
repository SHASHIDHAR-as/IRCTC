// import javax.swing.*;
// import java.awt.event.*;
// import java.util.ArrayList;
// import java.awt.*;

// public class CreatePanel extends JFrame implements ActionListener {
//     static Box vertical = Box.createVerticalBox();
//     JScrollPane jsp = new JScrollPane(vertical);
//     JPanel mainPanel = new JPanel(new FlowLayout());

//     JPanel panel;
//     JLabel label;
//     JButton Add;
//     ArrayList<JButton>button=new ArrayList<JButton>();
//     JTextField firstName;

//     CreatePanel() {

//         add(jsp, BorderLayout.CENTER);
//         add(mainPanel, BorderLayout.CENTER);

//         firstName = new JTextField("First Name");
//         firstName.setBounds(100, 0, 200, 30);
//         add(firstName);

//         Add = new JButton("ADD");
//         Add.setBounds(100, 140, 100, 30);
//         Add.addActionListener(this);
//         add(Add);

//         mainPanel.add(vertical, BorderLayout.PAGE_START);

//         setLocation(180, 20);
//         setSize(300, 300);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setVisible(true);
//     }

//     void addPanel(JLabel label) {
//         panel = new JPanel(new BorderLayout());
//         label.setBorder(BorderFactory.createLineBorder(Color.black));
//         button.add(new JButton("harsha"));
//         button.get(1).addActionListener(this);
//         panel.add(button.get(1));
//         panel.add(label, BorderLayout.LINE_START);
//         vertical.add(panel);
//     }

//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == Add) {
//             if (firstName.getText().equals("") ) {
//                 JOptionPane.showMessageDialog(null, "Please fill all the details");
//             } else {
//                 String NameText = firstName.getText();
//                 JLabel label = new JLabel(NameText);
//                 addPanel(label);
//             }
//         } 
//     }

//     public static void main(String[] args) {
//         new CreatePanel();
//     }

// }

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;

public class Createcol {
    Createcol(){
        JLabel label=new JLabel("hi");
        // label.setBounds(5, 10, 30, 30);
        label.setVerticalAlignment(JLabel.BOTTOM );
        
        JPanel redpannel =new JPanel();
        redpannel.setBackground(Color.red);
        redpannel.setBounds(0,0,750,200);
        redpannel.setLayout(new BorderLayout());
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750,750);
        frame.setVisible(true);
        frame.add(redpannel);
        redpannel.add(label);
    }
    public static void main(String[] args) {
       new Createpan(); 
    }
}


// import javax. swing.*;
// import java.awt.*;
// class Createpan extends JFrame{
//     Createpan(){
//         setTitle("JPANEL CREATION");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(null);
//         //setting the bounds for the JFrame
//         setBounds(100,100,500,300);
//         Container c=getContentPane();
//                 //Creating a JPanel for the JFrame
//                 JPanel panel=new JPanel();
//                 //setting the panel layout as null
//                 panel.setLayout(null);
//         JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

//         c.add(scrollBar);

//         //adding a label element to the panel
//         JLabel label=new JLabel("HELLO  I  AM  SUBHOJEET");
//         label.setBounds(70,100,200,50);
//         panel.add(label);
//         // changing the background color of the panel to yellow
//         panel.setBackground(Color.yellow);
//         panel.setBounds(100,50,300,200);
        
//         //adding the panel to the Container of the JFrame
//         c.add(panel);
       
//         setVisible(true);
//     }
//     public static void main(String[] args) {
//         new Createpan();
//     }
// }