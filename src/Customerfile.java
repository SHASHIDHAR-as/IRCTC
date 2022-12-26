import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Customerfile{
    String name;
    public void createfile(String name){
        File myFile=new File(name+".txt");
        try{
        myFile.createNewFile();
        }
        catch(Exception e){
            System.out.println("Unable to create this file");
            e.printStackTrace();
        }
    }
    public void writefile(ArrayList<String> details,
    String name){
        try{
            FileWriter fileWriter=new FileWriter(name+".txt");
            fileWriter.write("               IRCTC RAILWAY RESERVATION            \n\n");
            fileWriter.write("Hi,"+name+"\n\nYour Train tickets are booked \n\nDETAILS :\n\n");
            for(String detail : details) {
                
                fileWriter.write("\n"+detail.toString()+System.lineSeparator());
                // System.out.println(detail);
            }
            fileWriter.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
    }

        public void deletefile(String name) { 
            File myObj = new File(name+".txt");  
            if (myObj.delete()) { 
            System.out.println("Deleted the file: " + myObj.getName());
            } else {
            System.out.println("Failed to delete the file.");
            } 
        } 
        
    public static void main(String[] args) {
        String name;
        Scanner sc=new Scanner(System.in);
        System.out.println("ENter your name");
        name=sc.next();
        ArrayList<String> details=new ArrayList<String>();
        details.add(name);
        Customerfile c=new Customerfile();
        c.createfile(name);
        c.writefile(details,name);
        c.deletefile(name);
    }
}
