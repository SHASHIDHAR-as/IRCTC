import javax.swing.*;
import java.awt.event.*;

public class TextAnimator extends JFrame{
    public static void textAnimator(JTextField text,String content){
        text.addFocusListener(new FocusAdapter(){
            //clear the text when the field gains focus
            public void focusGained(FocusEvent e){
                text.setText("");
            }
            //set the initial text when the field loses focus
            public void focusLost(FocusEvent e){
                if(text.getText().isEmpty()){
                    text.setText(content);
                }
            }
        });
        }
}
