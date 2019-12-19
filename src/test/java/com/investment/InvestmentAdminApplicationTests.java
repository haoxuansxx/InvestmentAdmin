package com.investment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvestmentAdminApplicationTests {

    @Test
    public void contextLoads() throws ParseException {
        Date date = new Date();
        //HH表示24小时制
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = dFormat.format(date);
        Date parse = dFormat.parse(formatDate);
        System.out.println(parse);
    }

}
