
import java.util.*;
import java.lang.*;
import java.io.*;
import java.time.*;
import java.time.temporal.*;
import java.time.format.*;

/* Name of the class has to be "Main" only if the class is public. */
public class Getdays
{
	public static void main (String[] args) throws java.lang.Exception
	{
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the date in the format(DD/MM/YYYY)");
String date=sc.next();
String output = 
LocalDate.parse(                               // Generate `LocalDate` object from String input.
            date ,
            DateTimeFormatter.ofPattern( "d/M/uuuu" ) 
        )                                    
         .getDayOfWeek()                       // Get `DayOfWeek` enum object.
         .getDisplayName(                      // Localize. Generate a String to represent this day-of-week.
             TextStyle.SHORT_STANDALONE ,      // How long or abbreviated. Some languages have an alternate spelling for "standalone" use (not so in English).
             Locale.US                         // Or Locale.CANADA_FRENCH and such. Specify a `Locale` to determine (1) human language for translation, and (2) cultural norms for abbreviation, punctuation, etc.
        )  ;

        System.out.println( "output: " + output ) ;

	}
}