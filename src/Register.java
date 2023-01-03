import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class Register extends JFrame implements ActionListener{

    JTextField firstName,lastName,userName,password,reEnterPassword,email,address,phone;
    JDateChooser dateChooser;
    JRadioButton male,female,other,indian,foreign;
    JButton register,back;

    Register(){
        setTitle("IRCTC");
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/register.png"));
        Image i2 = i1.getImage().getScaledInstance(983, 660, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 983, 660);
        add(image);

        firstName=new JTextField("First Name");
        firstName.setBounds(225,110,530,30);
        firstName.setFont(new Font("Raleway", Font.PLAIN, 17));
        firstName.setForeground(Color.gray);
        firstName.setBorder(null);
        TextAnimator.textAnimator(firstName,"First Name");
        image.add(firstName);

        lastName=new JTextField("Last Name");
        lastName.setBounds(225,158,530,30);
        lastName.setFont(new Font("Raleway", Font.PLAIN, 17));
        lastName.setForeground(Color.gray);
        lastName.setBorder(null);
        TextAnimator.textAnimator(lastName,"Last Name");
        image.add(lastName);

        userName=new JTextField("User Name");
        userName.setBounds(225,205,530,30);
        userName.setFont(new Font("Raleway", Font.PLAIN, 17));
        userName.setForeground(Color.gray);
        userName.setBorder(null);
        TextAnimator.textAnimator(userName,"User Name");
        image.add(userName);

        password=new JTextField("password");
        password.setBounds(225,250,235,30);
        password.setFont(new Font("Raleway", Font.PLAIN, 17));
        password.setForeground(Color.gray);
        password.setBorder(null);
        TextAnimator.textAnimator(password,"password");
        image.add(password);

        reEnterPassword=new JTextField("Re-Enter password");
        reEnterPassword.setBounds(520,250,235,30);
        reEnterPassword.setFont(new Font("Raleway", Font.PLAIN, 17));
        reEnterPassword.setForeground(Color.gray);
        reEnterPassword.setBorder(null);
        TextAnimator.textAnimator(reEnterPassword,"Re-Enter password");
        image.add(reEnterPassword);

        email=new JTextField("Email");
        email.setBounds(225,300,530,30);
        email.setFont(new Font("Raleway", Font.PLAIN, 17));
        email.setForeground(Color.gray);
        email.setBorder(null);
        TextAnimator.textAnimator(email,"Email");
        image.add(email);

        address=new JTextField("Address");
        address.setBounds(225,345,530,30);
        address.setFont(new Font("Raleway", Font.PLAIN, 17));
        address.setForeground(Color.gray);
        address.setBorder(null);
        TextAnimator.textAnimator(address,"Address");
        image.add(address);

        phone=new JTextField("Phone");
        phone.setBounds(225,395,530,30);
        phone.setFont(new Font("Raleway", Font.PLAIN, 17));
        phone.setForeground(Color.gray);
        phone.setBorder(null);
        TextAnimator.textAnimator(phone,"Phone");
        image.add(phone);

        JLabel dob=new JLabel("DOB");
        dob.setBounds(230,440,50,30);
        dob.setFont(new Font("Raleway", Font.PLAIN, 17));
        dob.setForeground(Color.gray);
        dob.setBackground(Color.white);
        dob.setOpaque(true);
        image.add(dob);

        dateChooser=new JDateChooser();
        dateChooser.setBounds(300,440,200,30);
        image.add(dateChooser);

        male=new JRadioButton("Male");
        male.setBounds(230,500,60,30);
        male.setBackground(Color.white);
        male.setFont(new Font("Raleway", Font.PLAIN, 15));
        male.setForeground(Color.gray);
        image.add(male);
        
        female=new JRadioButton("Female");
        female.setBounds(400,500,100,30);
        female.setBackground(Color.white);
        female.setFont(new Font("Raleway", Font.PLAIN, 15));
        female.setForeground(Color.gray);
        image.add(female);

        other=new JRadioButton("Other");
        other.setBounds(550,500,90,30);
        other.setBackground(Color.white);
        other.setFont(new Font("Raleway", Font.PLAIN, 15));
        other.setForeground(Color.gray);
        image.add(other);

        ButtonGroup genderGroup=new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        indian=new JRadioButton("Indian");
        indian.setBounds(550,440,90,30);
        indian.setBackground(Color.white);
        indian.setFont(new Font("Raleway", Font.PLAIN, 15));
        indian.setForeground(Color.gray);
        image.add(indian);
        
        foreign=new JRadioButton("Foreign");
        foreign.setBounds(680,440,120,30);
        foreign.setBackground(Color.white);
        foreign.setFont(new Font("Raleway", Font.PLAIN, 15));
        foreign.setForeground(Color.gray);
        image.add(foreign);

        ButtonGroup nationalityGroup=new ButtonGroup();
        nationalityGroup.add(indian);
        nationalityGroup.add(foreign);

        register=new JButton("Register");
        register.setBounds(680,580,100,30);
        register.setFont(new Font("Raleway", Font.BOLD, 23));
        register.setForeground(Color.decode("#E87020"));
        register.setBackground(Color.black);
        register.setBorder(null);
        register.setOpaque(false);
        register.addActionListener(this);
        image.add(register);

        back=new JButton("Back");
        back.setBounds(210,580,100,30);
        back.setFont(new Font("Raleway", Font.BOLD, 23));
        back.setForeground(Color.decode("#E87020"));
        back.setBackground(Color.black);
        back.setBorder(null);
        back.setOpaque(false);
        back.addActionListener(this);
        image.add(back);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(280, 80);
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
                            String genOtp=String.copyValueOf(OTP(4));
                            SendOTP.sendOTP(genOtp,emailText);
                            String enteredOtp= JOptionPane.showInputDialog("Enter the otp sent to your email "); 
                            System.out.println(enteredOtp);
                            if(genOtp.equals(enteredOtp)){
                                System.out.println("correct");
                                String query1="insert into user(user_name,first_name,last_name,gender,address,nationality,dob,phone)values ('"+userNameText+"','"+firstNameText+"','"+lastNameText+"','"+gender+"','"+addressText+"','"+natiolaity+"','"+dob+"','"+phoneText+"')";
                                String query2="insert into user_login(user_name,password,email) values('"+userNameText+"','"+passwordText+"','"+emailText+"')";
                                c.s.executeUpdate(query1);
                                c.s.executeUpdate(query2);
    
                                JOptionPane.showMessageDialog(null,"Please wait we register your account"); 

                                ArrayList<String> details=new ArrayList<String>();
                                details.add("USER NAME   : "+userNameText);
                                details.add("FIRST NAME  : "+firstNameText);
                                details.add("LAST NAME   : "+lastNameText);
                                details.add("PASSWORD    : "+passwordText);
                                details.add("EMAIL       : "+emailText);
                                details.add("GENDER      : "+gender);
                                details.add("ADDRESS     : "+addressText);
                                details.add("NATIONALITY : "+natiolaity);
                                details.add("DOB         : "+dob);
                                details.add("PHONE       : "+phoneText);
                                
                                rs = c.s.executeQuery("SELECT email  FROM user_login WHERE user_name='"+userNameText+"';");
                                
                                if(rs.next()){
                                    emailText=rs.getString("email");
                                    System.out.println(email);
                                    MailAttachment.sendConfirmation(emailText,userNameText,details,"Registration successfull","\n\nYour Account is successfully created in IRCTC \n\nDETAILS :\n\n");
                                    JOptionPane.showMessageDialog(null,"Registration successfull...Details are sent to your mail ...Please login"); 
                                    setVisible(false);
                                    new Login().setVisible(true);
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null,"Sorry!! There was some error..Please try again"); 
                                        setVisible(false);
                                        new Login().setVisible(true);
                                    }
                                    }
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
        new Register();
    }

}
