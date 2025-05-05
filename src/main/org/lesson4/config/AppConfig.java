package org.lesson4.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(value = "org.lesson4")
public class AppConfig {

    @Bean
    public DbProperties databaseProperties(Environment env) {
        return new DbProperties(
                env.getProperty("db.url"),
                env.getProperty("db.username"),
                env.getProperty("db.password")
        );
    }

    @Bean
    public DataSource dataSource(DbProperties db) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(db.url());
        config.setUsername(db.username());
        config.setPassword(db.password());
        return new HikariDataSource(config);
    }
}