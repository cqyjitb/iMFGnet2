package yj.core.webservice_test.components;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NumberFormat ddf1 = NumberFormat.getNumberInstance() ;
        ddf1.setMaximumFractionDigits(2);
        try {
            Date date1 = sdf.parse("2019-07-20 08:03:00");
            Date date2 = sdf.parse("2019-07-20 10:41:00");
            Date date3 = new Date(120 * 100 * 1000 + date1.getTime());
            double timecy = ( date2.getTime() - date1.getTime() ) / 1000;
            System.out.println(timecy);
            System.out.println(73/ ( timecy / 100) );
            System.out.println(sdf.format(date3));
            List cardlist = new ArrayList();

            if (!cardlist.contains("1234")){
                cardlist.add("123");
            }
            System.out.println(cardlist.size());


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
