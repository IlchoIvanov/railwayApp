package com.example.railwayapp.configuration;

import com.example.railwayapp.repository.StationRepository;
import com.example.railwayapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
                                                       StationRepository stationRepository, UserRepository userRepository,
                                                       ResourceLoader resourceLoader) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        if (stationRepository.count() == 0 ) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(resourceLoader.getResource("classpath:stations.sql"));
            initializer.setDatabasePopulator(populator);
        }
        if (userRepository.count() == 0 ) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(resourceLoader.getResource("classpath:users.sql"));
            initializer.setDatabasePopulator(populator);
        }

        return initializer;
    }


}
