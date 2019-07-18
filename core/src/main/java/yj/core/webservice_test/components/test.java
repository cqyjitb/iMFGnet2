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
            Date date1 = sdf.parse("2019-07-18 10:30:31");
            Date date2 = sdf.parse("2019-07-18 12:00:00");

            Long timecy = ( date2.getTime() - date1.getTime() ) / 1000;
            System.out.println(timecy);
            System.out.println(timecy / 120 );
            double oeetmp =  85/44*100;
            System.out.println(oeetmp);
            String s = ddf1.format(oeetmp) ;
            double oeerate = Double.parseDouble(s);

            System.out.println(oeerate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
