// import java.awt.*;

// // import javax.swing.*;

// // public class samplepane extends JFrame {
// //     samplepane() {

// //         JPanel mainPanel = new JPanel();
// //         mainPanel.setLayout(null);
// //         mainPanel.setBackground(Color.red);
// //         mainPanel.setLayout(new BorderLayout());
// //         mainPanel.setBounds(0, 100, 1000, 700);
// //         JLabel label = new JLabel("HI THIS IS SHaSHIDHR");
// //         label.setBounds(100,10,300,30);
// //         mainPanel.add(label);
// //         add(mainPanel);

// //         setTitle("IRCTC");
// //         setLayout(null);
// //         setSize(1000, 700);
// //         setVisible(true);
// //         setLocation(180, 20);

// //     }

// //     public static void main(String[] args) {
// //         new samplepane();
// //     }
// // }
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// class Samplepane extends JFrame implements ActionListener {
//     // static Box vertical=Box.createVerticalBox();
//     static Box vertical=Box.createVerticalBox();
//     JScrollPane jsp = new JScrollPane(vertical);
//     JPanel mainPanel = new JPanel(new FlowLayout());
//     JTextField PassengerName,Age;
//     JRadioButton male, female, other;
//     JButton Add, back, Remove;
//     JPanel panel3 ;
    
//     Samplepane(JPanel panels) {
    
//         setTitle("JPANEL CREATION");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(null);
//         // setting the bounds for the JFrame
//         // setBounds(100,100,500,300);
//         setSize(1000, 700);
//         Container c = getContentPane();
//         JPanel panel = new JPanel();
//         panel.setLayout(null);
//         JLabel label = new JLabel("To show train selected");
//         label.setBounds(10, 50, 200, 50);
//         panel.add(label);
//         panel.setBackground(Color.yellow);
//         panel.setBounds(0, 0, 1000, 100);
//         c.add(panel);

//         // container2

//         Container c2 = getContentPane();
//         // Creating a JPanel for the JFrame
//         JPanel panel2 = new JPanel();
//         // setting the panel layout as null
//         panel2.setLayout(null);
//         // adding a label element to the panel
//         JLabel label2 = new JLabel("ADD PASSENGERS");
//         label2.setBounds(10, 0, 200, 50);
//         panel2.add(label2);

//         // TO ADD PASSENGERNAME

//         PassengerName = new JTextField("Passenger Name");
//         PassengerName.setBounds(10, 55, 200, 30);
//         panel2.add(PassengerName);

//         // TO ADD AGE
//         Age = new JTextField("Age");
//         Age.setBounds(10, 90, 200, 30);
//         panel2.add(Age);

//         //to select the gender

//         male = new JRadioButton("Male");
//         male.setBounds(10, 125, 60, 30);
//         male.setBackground(Color.white);
//         panel2.add(male);

//         female = new JRadioButton("Female");
//         female.setBounds(80, 125, 90, 30);
//         female.setBackground(Color.white);
//         panel2.add(female);

//         other = new JRadioButton("Other");
//         other.setBounds(200, 125, 60, 30);
//         other.setBackground(Color.white);
//         panel2.add(other);

//         ButtonGroup genderGroup = new ButtonGroup();
//         genderGroup.add(male);
//         genderGroup.add(female);
//         genderGroup.add(other);

//         //to add button ADD

//         Add = new JButton("ADD");
//         Add.setBounds(10, 170, 100, 30);
//         panel2.add(Add);
//         panel2.setBackground(Color.red);
//         panel2.setBounds(0, 100, 1000, 220);
//         c2.add(panel2);

//         //to show the passengers added

//         Container c3 = getContentPane();
    
//         // Creating a JPanel for the JFrame
//         panel3 = new JPanel();
//         // setting the panel layout as null
//         panel3.setLayout(null);

//         JScrollPane jsp = new JScrollPane(vertical);
//         add(jsp, BorderLayout.CENTER);
//         // adding a label element to the panel
//         // changing the background color of the panel to yellow
//         panel3.setBackground(Color.yellow);
//         panel3.setBounds(0, 320, 1000, 1800);

//         // adding the panel to the Container of the JFrame
//         c3.add(panel3);
//         mainPanel.add(c3);
//         mainPanel.add(vertical,BorderLayout.PAGE_START);
//         setLocation(180, 20);
//         setVisible(true);
//     }


//     void addPanel(JLabel label){
//         JPanel panele=new JPanel(new BorderLayout());
//         panel3.add(panele);
//         label.setBorder(BorderFactory.createLineBorder(Color.black));
//         JButton button=new JButton("EDIT");
//         button.addActionListener(this);
//         // book.add(button);
//         panel3.add(button);
//         panel3.add(label,BorderLayout.LINE_START);
//         // vertical.add(panel3);
//         vertical.add(panel3);
    
//     }

//     public void actionPerformed(ActionEvent e) {
//         String passenger,age,gender;
//         if(e.getSource()==Add){
//             passenger=PassengerName.getText();
//             age=Age.getText();
//             gender=null;
//             if(male.isSelected())
//             gender="Male";
//             else if(female.isSelected())
//             gender="Female";
//             else if(other.isSelected())
//             gender="Other";
//             JLabel label=new JLabel(passenger+" "+age+" "+gender);
//             addPanel(label);
            
//         }
//     }
//     public static void main(String[] args) {
//         new Samplepane();
//     }
// }





// import javax.swing.table.DefaultTableModel;

// public class Java_JTable_Add_Row_Using_TextBoxes extends javax.swing.JFrame {

//     /**
//      * Creates new form Java_JTable_Add_Row_Using_TextBoxes
//      */
//     public Java_JTable_Add_Row_Using_TextBoxes() {
//         initComponents();
//     }

//     @SuppressWarnings("unchecked")
//     // <editor-fold defaultstate="collapsed" desc="Generated Code">                        
//     private void initComponents() {

//         jLabel1 = new javax.swing.JLabel();
//         jLabel2 = new javax.swing.JLabel();
//         jLabel3 = new javax.swing.JLabel();
//         jLabel4 = new javax.swing.JLabel();
//         jTextFieldID = new javax.swing.JTextField();
//         jTextFieldFN = new javax.swing.JTextField();
//         jTextFieldLN = new javax.swing.JTextField();
//         jTextFieldAGE = new javax.swing.JTextField();
//         btnAddRow = new javax.swing.JButton();
//         jScrollPane1 = new javax.swing.JScrollPane();
//         jTable1 = new javax.swing.JTable();
//         // setSize(1000, 700);
//         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

//         jLabel1.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
//         jLabel1.setText("Id :");

//         jLabel2.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
//         jLabel2.setText("First Name :");

//         jLabel3.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
//         jLabel3.setText("Last Name :");

//         jLabel4.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
//         jLabel4.setText("Age :");

//         btnAddRow.setText("Add Row");
//         btnAddRow.addActionListener(new java.awt.event.ActionListener() {
//             public void actionPerformed(java.awt.event.ActionEvent evt) {
//                 btnAddRowActionPerformed(evt);
//             }
//         });

//         jTable1.setModel(new javax.swing.table.DefaultTableModel(
//             new Object [][] {

//             },
//             new String [] {
//                 "Id", "First Name", "Last Name", "Age"
//             }
//         ));
//         jScrollPane1.setViewportView(jTable1);

//         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//         getContentPane().setLayout(layout);
//         layout.setSize(1000,700);
//         layout.setHorizontalGroup(
//             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//             .addGroup(layout.createSequentialGroup()
//                 .addContainerGap()
//                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                         .addGroup(layout.createSequentialGroup()
//                             .addComponent(jLabel1)
//                             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                             .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
//                         .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
//                             .addComponent(jLabel2)
//                             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                             .addComponent(jTextFieldFN, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                         .addComponent(btnAddRow, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
//                         .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
//                             .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                                 .addComponent(jLabel3)
//                                 .addComponent(jLabel4))
//                             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                             .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                 .addComponent(jTextFieldAGE, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                 .addComponent(jTextFieldLN, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
//                 .addGap(18, 18, 18)
//                 .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
//                 .addContainerGap(29, Short.MAX_VALUE))
//         );
//         layout.setVerticalGroup(
//             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//             .addGroup(layout.createSequentialGroup()
//                 .addGap(50, 50, 50)
//                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                     .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
//                     .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                 .addGap(18, 18, 18)
//                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                     .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
//                     .addComponent(jTextFieldFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                 .addGap(18, 18, 18)
//                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                     .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
//                     .addComponent(jTextFieldLN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                 .addGap(18, 18, 18)
//                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                     .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
//                     .addComponent(jTextFieldAGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                 .addComponent(btnAddRow, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
//                 .addGap(21, 21, 21))
//             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                 .addContainerGap(14, Short.MAX_VALUE)
//                 .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
//                 .addContainerGap())
//         );

//         pack();
//     }// </editor-fold>                      

//     // button to add the row to the jtable 
//     private void btnAddRowActionPerformed(java.awt.event.ActionEvent evt) {                                          
//        // get the model from the jtable
//         DefaultTableModel model = (DefaultTableModel)jTable1.getModel();

//          // insert row to the model from jtextfields using addRow method
//         model.addRow(new Object[]{jTextFieldID.getText(), jTextFieldFN.getText(),
//                                 jTextFieldLN.getText(), jTextFieldAGE.getText()});

        
//     }                                      

//     /**
//      * @param args the command line arguments
//      */
//     public static void main(String args[]) {
//         try {
//             for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                 if ("Nimbus".equals(info.getName())) {
//                     javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                     break;
//                 }
//             }
//         } catch (ClassNotFoundException ex) {
//             java.util.logging.Logger.getLogger(Java_JTable_Add_Row_Using_TextBoxes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         } catch (InstantiationException ex) {
//             java.util.logging.Logger.getLogger(Java_JTable_Add_Row_Using_TextBoxes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         } catch (IllegalAccessException ex) {
//             java.util.logging.Logger.getLogger(Java_JTable_Add_Row_Using_TextBoxes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//             java.util.logging.Logger.getLogger(Java_JTable_Add_Row_Using_TextBoxes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//         }
//         //</editor-fold>

//         /* Create and display the form */
//         java.awt.EventQueue.invokeLater(new Runnable() {
//             public void run() {
//                 new Java_JTable_Add_Row_Using_TextBoxes().setVisible(true);
//             }
//         });
//     }

//     // Variables declaration - do not modify                  
//     private javax.swing.JButton btnAddRow;
//     private javax.swing.JLabel jLabel1;
//     private javax.swing.JLabel jLabel2;
//     private javax.swing.JLabel jLabel3;
//     private javax.swing.JLabel jLabel4;
//     private javax.swing.JScrollPane jScrollPane1;
//     private javax.swing.JTable jTable1;
//     private javax.swing.JTextField jTextFieldAGE;
//     private javax.swing.JTextField jTextFieldFN;
//     private javax.swing.JTextField jTextFieldID;
//     private javax.swing.JTextField jTextFieldLN;
//     // End of variables declaration                
// }

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class Java_JTable_Add_Row_Using_TextBoxes extends JFrame {
   private JTable table;
   private DefaultTableModel model;
   private Object[][] data;
   private String[] columnNames;
   private JButton button;
   public Java_JTable_Add_Row_Using_TextBoxes() {
      setTitle("RemoveSelectedRow Test");
      data = new Object[][] {{"101", "Ramesh"}, {"102", "Adithya"}, {"103", "Jai"}, {"104", "Sai"}};
      columnNames = new String[] {"ID", "Name"};
      model = new DefaultTableModel(data, columnNames);
      table = new JTable(model);
      table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      button = new JButton("Remove");
      button.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
            // check for selected row first
            if(table.getSelectedRow() != -1) {
               // remove selected row from the model
               model.removeRow(table.getSelectedRow());
               JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
         }
      });
      add(new JScrollPane(table), BorderLayout.CENTER);
      add(button, BorderLayout.SOUTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(400, 300);
      setLocationRelativeTo(null);
      setVisible(true);
   }
   public static void main(String args[]) {
      new Java_JTable_Add_Row_Using_TextBoxes();
   
   }
}


// import java.util.Random;

// public class sample {
//     public static void main(String[] args) {
//         Random ran = new Random();
//         long first7 = (ran.nextLong() % 90000000L) + 2356000000L;
//         String cardno = "" + Math.abs(first7);
//         System.out.println(cardno);
//     }
// }
