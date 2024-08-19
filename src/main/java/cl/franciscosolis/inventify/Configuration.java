package cl.franciscosolis.inventify;

import cl.franciscosolis.inventify.models.Item;
import cl.franciscosolis.inventify.repositories.ItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
@EnableAutoConfiguration(exclude= ErrorMvcAutoConfiguration.class)
public class Configuration {

    private static final Logger log = LoggerFactory.getLogger(Configuration.class);

    @Bean
    CommandLineRunner initDatabase(ItemsRepository itemsRepository) {
        return args -> {
            Item item = new Item()
                    .setId(7803525000240L)
                    .setName("Brownie receta original")
                    .setPrice(700.0);
            itemsRepository.save(item);
            log.info("Preloading {}", itemsRepository.findById(item.getId()).orElseThrow());
        };
    }

}
