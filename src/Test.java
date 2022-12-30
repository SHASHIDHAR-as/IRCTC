// import javax.print.DocFlavor.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JFrame implements ActionListener{
    Test(){
        setTitle("IRCTC");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        // setLayout(null);
        String htmlContent="""
            <html>
            <style>
                .heading{
                    width:460px ;
                    height:30px;
                }
            </style>
            <div>
                <div class="heading">
                    <h1> &ensp; &ensp; HAMPI EXPRESS  &ensp;(16592)</h1>
                </div>
                <hr>
                <div >
                    <h2>
                        &emsp; &emsp; Yeshwantpur Jn &emsp; &emsp; &emsp; &emsp; &emsp; &#8594; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; Ballari Jn 
                    </h2>
                </div>
                <h3> &emsp;&emsp;&emsp; &emsp; &emsp; 22:02    &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp;  &emsp; &emsp; &emsp;&emsp; &emsp; &emsp; &emsp; 05:50 </h3>
                <h3> &emsp; &emsp; &emsp; ticket cost : 40 &emsp; &emsp; seats available : 20</h3>
            <div>
            </html>
                """;
                
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JLabel label=new JLabel(htmlContent, JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBackground(Color.white);
        label.setOpaque(true);
        panel.add(label);
        
        label=new JLabel(htmlContent, JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        label=new JLabel(htmlContent, JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBackground(Color.white);
        label.setOpaque(true);
        panel.add(label);
        
        label=new JLabel(htmlContent, JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);


        // JLabel label;
        // JPanel panel=new JPanel();
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // panel.setPreferredSize(new Dimension(100, 400));

        // label=new JLabel(htmlContent);
        // panel.add(label);

        // label=new JLabel(htmlContent);
        // panel.add(label);
        
        // label=new JLabel(htmlContent);
        // panel.add(label);
        
        // label=new JLabel(htmlContent);
        // panel.add(label);
        
        // label=new JLabel(htmlContent);
        // panel.add(label);
        
        // panel.setBounds(135,100,700,200);
        // panel.setBackground(Color.white);
        // add(panel);

        // JPanel Jpanel = new JPanel(); 
        JScrollPane Jscroll = new JScrollPane(panel); 

        setLayout(new BorderLayout());
        add(Jscroll, BorderLayout.CENTER);
        
        setLocation(180, 20);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        
    }
    public static void main(String args[])
    {
        new Test();
    }
}
