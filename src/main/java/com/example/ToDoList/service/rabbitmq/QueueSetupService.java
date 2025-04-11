package com.example.ToDoList.service.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueSetupService {

    @Autowired
    private AmqpAdmin amqpAdmin;

    public void setupQueueForUser(Integer userId) {
        String queueName = "todo.queue.user." + userId;
        String routingKey = "todo.user." + userId;
        String exchangeName = "todo.exchange";

        Queue queue = new Queue(queueName, true); // durable
        amqpAdmin.declareQueue(queue);

        Binding binding = BindingBuilder
                .bind(queue)
                .to(new DirectExchange(exchangeName))
                .with(routingKey);  // user-specific routing key
        amqpAdmin.declareBinding(binding);
    }

}
