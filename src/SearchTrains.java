import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class SearchTrains extends JFrame implements ActionListener{
    JTextField From, To;
    JButton Search,Clear,back;
    JDateChooser dateChooser;

    SearchTrains() {
        setTitle("IRCTC");
        setLayout(null);

        //Main frame image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/orange.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1000, 700);
        add(image);

        //username text feild
        From = new JTextField("From station");
        From.setBounds(100, 219, 300, 40);
        From.setFont(new Font("Raleway", Font.BOLD, 15));
        From.setForeground(Color.gray);
        From.setBorder(null);
        image.add(From);

        //password text feild
        To = new JTextField("To station");
        To.setBounds(460, 219, 300, 40);
        To.setFont(new Font("Raleway", Font.BOLD, 15));
        To.setForeground(Color.gray);
        To.setBorder(null);
        image.add(To);

        dateChooser=new JDateChooser();
        dateChooser.setBounds(100,300,400,30);
        dateChooser.setForeground(new Color(105,105,105));
        image.add(dateChooser);

        
        Search = new JButton("Search");
        Search.setBounds(387, 417, 76, 30);
        Search.setForeground(Color.black);
        Search.setBackground(Color.ORANGE);
        Search.setFont(new Font("Raleway", Font.BOLD, 16));
        Search.setBorder(null);
        Search.addActionListener(this);
        image.add(Search);

        Clear = new JButton("Clear");
        Clear.setBounds(487, 417, 76, 30);
        Clear.setForeground(Color.black);
        Clear.setBackground(Color.ORANGE);
        Clear.setFont(new Font("Raleway", Font.BOLD, 16));
        Clear.setBorder(null);
        Clear.addActionListener(this);
        image.add(Clear);

        back=new JButton("Back");
        back.setBounds(600, 417, 76, 30);
        back.setForeground(Color.black);
        back.setBackground(Color.ORANGE);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBorder(null);
        back.addActionListener(this);
        image.add(back);


        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }


    public void actionPerformed(ActionEvent e) {

        //check for login
        if(e.getSource()==Search){
            //check if all the details are entered
            if(From.getText().equals("") || To.getText().equals("") ){
                JOptionPane.showMessageDialog(null,"Please fill all the details");  
            }
            else{
                String source=From.getText();
                String destination=To.getText();
                String day=getDay(dateChooser.getDate());
                System.out.println(source+" "+destination+" "+day);
            }
        }
        else if(e.getSource()==Clear){
            From.setText("");
            To.setText("");
        }

        else if(e.getSource()==back){
            setVisible(false);
            new HomePage().setVisible(true);
        } 
    }

    public static String getDay(Date d){
        LocalDateTime ldt=d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DayOfWeek dow=ldt.getDayOfWeek();
        return dow.name();
    }
    public static void main(String args[]) {
        new SearchTrains();
    }

}
