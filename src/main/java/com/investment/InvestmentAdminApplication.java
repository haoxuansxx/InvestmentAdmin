package com.investment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot 启动类
 *
 * @author Sun
 */

@MapperScan("com.investment.mapper")
@SpringBootApplication
public class InvestmentAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestmentAdminApplication.class, args);
        System.out.println("   _____ __             __                                                          \n" +
                "  / ___// /_____ ______/ /_      __  ______     _______  _______________  __________\n" +
                "  \\__ \\/ __/ __ `/ ___/ __/_____/ / / / __ \\   / ___/ / / / ___/ ___/ _ \\/ ___/ ___/\n" +
                " ___/ / /_/ /_/ / /  / /_/_____/ /_/ / /_/ /  (__  ) /_/ / /__/ /__/  __(__  |__  ) \n" +
                "/____/\\__/\\__,_/_/   \\__/      \\__,_/ .___/  /____/\\__,_/\\___/\\___/\\___/____/____/  \n" +
                "                                   /_/                                              ");
    }

}
