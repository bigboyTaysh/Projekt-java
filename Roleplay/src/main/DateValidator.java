package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pc
 */
public class DateValidator {

    /**
     *
     * @param dateToValidate
     * @param dateFromat
     * @return
     */
    public boolean isThisDateValid(String dateToValidate, String dateFromat){
		
		if(dateToValidate == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
                        
                        Date date = sdf.parse(dateToValidate);
                        
			
		
		} catch (ParseException e) {
			
			return false;
		}
		
		return true;
	}
	
}
