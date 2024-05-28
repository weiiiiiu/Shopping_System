package util;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter
{

    @Override
    public Date convert(Class arg0, Object arg1)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try
        {
            date = format.parse((String) arg1);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

}
