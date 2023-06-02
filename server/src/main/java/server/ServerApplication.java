package server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import server.nettyIM.im.IMserver;

@SpringBootApplication
//@MapperScan(value = "server.dao")
//@ComponentScan(value = "server.nettyIM.handler")
public class ServerApplication {

    public static void main(String[] args) {
        IMserver.start();
        SpringApplication.run(ServerApplication.class, args);
    }

}
