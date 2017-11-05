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
    public CommandLineRunner commandLineRunner(ApplicationContext ctx, final PointsCollectorRepository repository) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

//            repository.save(createAccount(10, "Alex"));
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
