import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import org.apache.commons.lang3.StringUtils;

public class Register extends JFrame implements ActionListener{

    JTextField firstName,lastName,userName,password,reEnterPassword,email,address,phone;
    JDateChooser dateChooser;
    JRadioButton male,female,other,indian,foreign;
    JButton register,back;

    Register(){
        setTitle("IRCTC");
        setLayout(null);

        firstName=new JTextField("First Name");
        firstName.setBounds(100,0,200,30);
        add(firstName);

        lastName=new JTextField("Last Name");
        lastName.setBounds(100,50,200,30);
        add(lastName);

        JLabel uniqueUser=new JLabel("User Name should be unique");
        uniqueUser.setBounds(100,40,200,10);
        uniqueUser.setFont(new Font("Raleway",Font.PLAIN,10));
        add(uniqueUser);

        userName=new JTextField("User Name");
        userName.setBounds(100,100,200,30);
        add(userName);

        password=new JTextField("password");
        password.setBounds(100,150,200,30);
        add(password);

        reEnterPassword=new JTextField("Re-Enter password");
        reEnterPassword.setBounds(100,200,200,30);
        add(reEnterPassword);

        email=new JTextField("Email");
        email.setBounds(100,250,200,30);
        add(email);

        address=new JTextField("Address");
        address.setBounds(100,300,200,30);
        add(address);

        phone=new JTextField("Phone");
        phone.setBounds(100,350,200,30);
        add(phone);

        dateChooser=new JDateChooser();
        dateChooser.setBounds(100,400,400,30);
        dateChooser.setForeground(new Color(105,105,105));
        add(dateChooser);

        male=new JRadioButton("Male");
        male.setBounds(100,450,60,30);
        male.setBackground(Color.white);
        add(male);
        
        female=new JRadioButton("Female");
        female.setBounds(200,450,60,30);
        female.setBackground(Color.white);
        add(female);

        other=new JRadioButton("Other");
        other.setBounds(300,450,60,30);
        other.setBackground(Color.white);
        add(other);

        ButtonGroup genderGroup=new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        indian=new JRadioButton("Indian");
        indian.setBounds(100,500,60,30);
        indian.setBackground(Color.white);
        add(indian);
        
        foreign=new JRadioButton("Foreign");
        foreign.setBounds(200,500,120,30);
        foreign.setBackground(Color.white);
        add(foreign);

        ButtonGroup nationalityGroup=new ButtonGroup();
        nationalityGroup.add(indian);
        nationalityGroup.add(foreign);

        register=new JButton("Register");
        register.setBounds(100,550,100,30);
        register.addActionListener(this);
        add(register);

        back=new JButton("Back");
        back.setBounds(100,600,100,30);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==register){
            if(firstName.getText().equals("") || lastName.getText().equals("") || userName.getText().equals("") || password.getText().equals("") || reEnterPassword.getText().equals("") || email.getText().equals("") || address.getText().equals("") || phone.getText().equals("") || (!male.isSelected() && !female.isSelected() && !other.isSelected()) || (!indian.isSelected() && !foreign.isSelected()) || ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText().equals("")){
                JOptionPane.showMessageDialog(null,"Please fill all the details"); 
            }else if(!password.getText().equals(reEnterPassword.getText())){
                JOptionPane.showMessageDialog(null,"password doesnt match"); 
                password.setText("");
                reEnterPassword.setText("");
            }
            else{
                String firstNameText=firstName.getText();
                String lastNameText=lastName.getText();
                String userNameText=userName.getText();
                String passwordText=password.getText();
                String reEnterPasswordText=reEnterPassword.getText();
                String emailText=email.getText();
                String addressText=address.getText();
                String phoneText=phone.getText();
                String dob=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
                
                String gender=null;
                if(male.isSelected())
                gender="Male";
                else if(female.isSelected())
                gender="Female";
                else if(other.isSelected())
                gender="Other";
                
                String natiolaity=null;
                if(indian.isSelected())
                    natiolaity="Indian";
                else if(foreign.isSelected())
                    natiolaity="Foreign";

                try{                                                        
                    Conn c=new Conn();

                    ResultSet rs=c.s.executeQuery("SELECT COUNT(user_name) as valid FROM user_login WHERE user_name='"+userName.getText()+"';");
                    if(rs.next()){
                        if(rs.getInt("valid")!=0){
                            JOptionPane.showMessageDialog(null,"Sorry this user name is already taken!!"); 
                            userName.setText("");
                        }else if(StringUtils.containsWhitespace(userName.getText())){
                            JOptionPane.showMessageDialog(null,"user name can not contain white spaces"); 
                        }
                        else{
                            String query1="insert into user(user_name,first_name,last_name,gender,address,nationality,dob,phone)values ('"+userNameText+"','"+firstNameText+"','"+lastNameText+"','"+gender+"','"+addressText+"','"+natiolaity+"','"+dob+"','"+phoneText+"')";
                            String query2="insert into user_login(user_name,password,email) values('"+userNameText+"','"+passwordText+"','"+emailText+"')";
                            c.s.executeUpdate(query1);
                            c.s.executeUpdate(query2);

                            setVisible(false);
                            new Login().setVisible(true);
                        }
                    }
                }catch(Exception error){
                    JOptionPane.showMessageDialog(null,"Invalid Entries..Please Try again"); 
                    System.out.println(error);
                    setVisible(false);
                    new Register().setVisible(true);
                }
            }
        }
        else if(e.getSource()==back){
            setVisible(false);
            new Login().setVisible(true);
        }
        
    }
    public static void main(String[] args) {
        new Register();
    }

}
