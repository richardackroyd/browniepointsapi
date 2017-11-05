package brownieapi;

import java.util.Arrays;

import brownieapi.dataaccess.PointsCollectorRepository;
import brownieapi.model.PointsAccount;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PointsCollectorRepository repository) {
        return args -> {


            //repository.save(createAccount(10, "Alex"));
  //          repository.save(createAccount(4, "Emily"));
    //        repository.save(createAccount(12, "Hannah"));

        };
    }

    private PointsAccount createAccount (final int points, final String name) {

        final PointsAccount pointsAccount = new PointsAccount();
        pointsAccount.setPoints(points);
        pointsAccount.setName(name);
        return pointsAccount;

    }

}
