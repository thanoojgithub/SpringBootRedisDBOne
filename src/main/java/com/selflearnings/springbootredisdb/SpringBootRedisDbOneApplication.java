package com.selflearnings.springbootredisdb;

import com.selflearnings.springbootredisdb.beans.Customer;
import com.selflearnings.springbootredisdb.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Optional;

@SpringBootApplication
public class SpringBootRedisDbOneApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootRedisDbOneApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SpringBootRedisDbOneApplication.class, args);
		CustomerService customerService = configurableApplicationContext.getBean(CustomerService.class);
		Customer newCustomer = new Customer("Cust202205030001", "John Doe", Customer.Gender.MALE, "Bangalore");
		final String newCustomerId = customerService.addCustomer(newCustomer);
		Optional<Customer> customer = customerService.getCustomer(newCustomerId);
		if (customer.isPresent()) {
			log.info("customer details :  " + customer.get());
		}
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("localhost");
		redisStandaloneConfiguration.setPort(6379);
		redisStandaloneConfiguration.setDatabase(0);

		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout

		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
				jedisClientConfiguration.build());

		return jedisConFactory;
	}
}
