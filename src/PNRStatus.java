import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class PNRStatus extends JFrame implements ActionListener{
    JLabel label;
    Box pnrPanel = Box.createVerticalBox();
    // JPanel pnrPanel;
    JTextField pnrNo;
    JButton search;
    PNRStatus(){
        setTitle("IRCTC");
        setLayout(null);

        pnrNo =new JTextField("PNR Number");
        pnrNo.setBounds(50,50,200,50);
        add(pnrNo);

        search =new JButton("Search");
        search.setBounds(50,100,100,50);
        search.addActionListener(this);
        add(search);

        label=new JLabel("PNR details are :");
        label.setBounds(50,150,100,50);
        label.setVisible(false);
        add(label);

        pnrPanel.setBounds(50,180,500,200);
        pnrPanel.setVisible(false);
        add(pnrPanel);

        getContentPane().setBackground(Color.white);

        setSize(1000, 700);
        setVisible(true);
        setLocation(180, 20);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            label.setVisible(true);
            pnrPanel.setVisible(true);
            try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery("select * from pnr_status where pnr_no='"+pnrNo.getText()+"';");
                while(rs.next()){
                    long pnr_no=rs.getLong("pnr_no");
                    int train_no=rs.getInt("train_no");
                    String train_name=rs.getString("train_name");
                    String from_station=rs.getString("from_station");
                    String to_station=rs.getString("to_station");
                    
                    JLabel pnr_noL=new JLabel("PNR Number : "+pnr_no);
                    JLabel train_noL=new JLabel("Train Number : "+train_no);
                    JLabel train_nameL=new JLabel("Train Name : "+train_name);
                    JLabel from_stationL=new JLabel("From : "+from_station);
                    JLabel to_stationL=new JLabel("To : "+to_station);

                    pnrPanel.add(pnr_noL);
                    pnrPanel.add(train_noL);
                    pnrPanel.add(train_nameL);
                    pnrPanel.add(from_stationL);
                    pnrPanel.add(to_stationL);
                }
            }catch(Exception error){
                System.out.println(error);
            }
        }
        
    }
    public static void main(String args[])
    {
        new PNRStatus();
    }
 
}
