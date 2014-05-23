/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agcoopsys;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lenovo
 */
public class validateAllInputs {

    public String formatStringSpaces(String formatString)
    {
        System.out.println(formatString);
        formatString = formatString.trim().replaceAll("\\s+", " ");
        formatString = formatString.toUpperCase();
        return formatString;
    }
    
    public boolean checkIfEmpty(String checkString)
    {
        if(checkString.length() == 0)
            return false;
        return true;
    }
    
    public boolean checkIfMiddleInitial(String checkString)
    {
        if(checkString.matches(".*\\d.*"))
            return false;
        
        if(checkString.length() == 1)
        {
            if(this.checkForSpecial(checkString))
            return true;
        } 
        return false;
    }
    
    public boolean checkForSpecial(String checkString)
    {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(checkString);
        boolean b = m.find();
        
        if(b)
            return false;
        return true;
    }
    
    
     
}
