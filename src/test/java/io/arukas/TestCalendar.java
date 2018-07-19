package io.arukas;

import org.joda.time.DateTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/18
 * Time: 20:15
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
public class TestCalendar {

    @Test
    public void testAddHour() {

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar instance = Calendar.getInstance();
//        instance.add(Calendar.DAY_OF_MONTH, 1);
//
//        String curDate = s.format(instance.getTime());  //当前日期
//        System.out.println(curDate);

        instance.add(Calendar.DAY_OF_WEEK, 1);
        String curDate = s.format(instance.getTime());  //当前日期
        System.out.println(curDate);
    }

    @Test
    public void test01() {

        DateTime now = DateTime.now();
        DateTime dateTime = now.plusDays(1);
        Date date = dateTime.toDate();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        Date date1 = now.plusMinutes(5).toDate();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1));
    }


}
