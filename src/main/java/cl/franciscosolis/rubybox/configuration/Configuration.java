package cl.franciscosolis.rubybox.configuration;

import cl.franciscosolis.rubybox.models.Product;
import cl.franciscosolis.rubybox.repositories.ProductsRepository;
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
    CommandLineRunner initDatabase(ProductsRepository productsRepository) {
        return args -> {
            Product product = new Product()
                    .setId(7803525000240L)
                    .setName("Brownie receta original")
                    .setPrice(700.0);
            productsRepository.save(product);
            log.info("Preloading {}", productsRepository.findById(product.getId()).orElseThrow());
        };
    }

}
