package org.example.pedidoobserver;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "myExchange";


    @Bean
    public Queue fileQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue dbQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingFileQueue(Queue fileQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fileQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingDbQueue(Queue dbQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(dbQueue).to(fanoutExchange);
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
