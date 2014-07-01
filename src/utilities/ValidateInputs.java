/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lenovo
 */
public class ValidateInputs {

    public String formatStringSpaces(String formatString)
    {
        //System.out.println(formatString);
        formatString = formatString.trim().replaceAll("\\s+", " ");
        //formatString = formatString.toUpperCase();
        return formatString;
    }
    
    public String removeSpaces(String formatString)
    {
        formatString = formatString.trim().replaceAll("\\s+", "");
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
        
        if(checkString.length() == 2 || checkString.length() == 1)
        {
            if(checkString.charAt(0) == '.')
                return false;
            
            if(this.checkPeriod(checkString))
                return true;
        } 
        return false;
    }
    
    public boolean checkPeriod(String checkString)
    {
        try{
        if(checkString.charAt(0) == '.')
            return false;
        }
        catch(Exception e) {}
        Pattern p = Pattern.compile("[^a-z0-9.]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(checkString);
        boolean b = m.find();
            
        if(b)
            return false;
                
        return true;
    }
    
    public boolean checkForSpecial(String checkString)
    {
        try {
        if(checkString.charAt(0) == '.' || checkString.charAt(0) == '-')
            return false;
        }
        catch(Exception e) {}
        Pattern p;
        p = Pattern.compile("[^a-z0-9. -]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(checkString);
        boolean b = m.find();
        
        if(b)
            return false;
        return true;
    }
    
    
     
}
