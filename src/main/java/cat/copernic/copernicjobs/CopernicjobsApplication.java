package cat.copernic.copernicjobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cat.copernic.copernicjobs")
public class CopernicjobsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopernicjobsApplication.class, args);

    }
}
