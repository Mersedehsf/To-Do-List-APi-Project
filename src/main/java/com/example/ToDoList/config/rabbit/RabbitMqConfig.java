package com.example.ToDoList.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String TODO_EXCHANGE = "todo.exchange";
    public static final String TODO_QUEUE = "todo.queue.all";
    public static final String TODO_ROUTING_KEY = "todo.all";

    @Bean
    public DirectExchange todoExchange() {
        return new DirectExchange(TODO_EXCHANGE);
    }

    @Bean
    public Queue todoQueue() {
        return new Queue(TODO_QUEUE, true); // durable
    }

    @Bean
    public Binding todoBinding(Queue todoQueue, DirectExchange todoExchange) {
        return BindingBuilder
                .bind(todoQueue)
                .to(todoExchange)
                .with(TODO_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
