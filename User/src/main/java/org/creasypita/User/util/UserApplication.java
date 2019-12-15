package org.creasypita.User.util;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by creasypita on 12/15/2019.
 *
 * @ProjectName: SSM
 */
@SpringBootApplication(scanBasePackages = {"org.creasypita.User"})
@MapperScan("org.creasypita.User.mappers")
public class UserApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(UserApplication.class, args);
    }
}
