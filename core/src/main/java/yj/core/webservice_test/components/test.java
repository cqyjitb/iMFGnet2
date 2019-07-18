package yj.core.webservice_test.components;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NumberFormat ddf1 = NumberFormat.getNumberInstance() ;
        ddf1.setMaximumFractionDigits(2);
        try {
            Date date1 = sdf.parse("2019-07-18 08:07:00");
            Date date2 = sdf.parse("2019-07-18 13:35:00");

            double timecy = ( date2.getTime() - date1.getTime() ) / 1000;
            System.out.println(timecy);
            System.out.println(17/ ( timecy / 4) );



        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
