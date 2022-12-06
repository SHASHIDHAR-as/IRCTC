import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class CreatePanel extends JFrame implements ActionListener{
    static Box vertical=Box.createVerticalBox();
    JScrollPane jsp = new JScrollPane(vertical);
    JPanel mainPanel = new JPanel(new FlowLayout());

    JPanel panel;
    JLabel label;
    ArrayList<JButton> button=new ArrayList<JButton>();

    CreatePanel(){

        add(jsp, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        panel = new JPanel(new BorderLayout());
        label=new JLabel("hi bro im suchith");
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        button.add(new JButton("suchith"));
        button.get(0).addActionListener(this);
        panel.add(button.get(0));
        panel.add(label,BorderLayout.LINE_START);
        vertical.add(panel);
        
        
        panel = new JPanel(new BorderLayout());
        label=new JLabel("hi bro im harsha");
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        button.add(new JButton("harsha"));
        button.get(1).addActionListener(this);
        panel.add(button.get(1));
        panel.add(label,BorderLayout.LINE_START);
        vertical.add(panel);

        mainPanel.add(vertical,BorderLayout.PAGE_START);
        
        setLocation(180, 20);
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<button.size();i++){
            if(e.getSource()==button.get(i)){
                String text=button.get(i).getText();
                System.out.println(text);
            }
        }
    }
    public static void main(String[] args) {
        new CreatePanel();
    }

}
