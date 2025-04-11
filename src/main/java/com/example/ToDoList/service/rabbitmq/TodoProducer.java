package com.example.ToDoList.service.rabbitmq;

import com.example.ToDoList.model.entity.ToDoMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TodoProducer {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper;

    public TodoProducer() {
        this.objectMapper = new ObjectMapper();
    }

    public void sendTodoMessage(Integer userId, String title) {
        String routingKey = "todo.all";
        ToDoMessage toDoMessage = new ToDoMessage( title);
        byte[] messageBody = serializeToBytes(toDoMessage);
        Message message = MessageBuilder.withBody(messageBody).build();

        rabbitTemplate.convertAndSend("todo.exchange", routingKey, message);
        System.out.println(" Sent todo for user " + userId);
    }

    public byte[] serializeToBytes(ToDoMessage todoMessage) {
        try {
            return objectMapper.writeValueAsBytes(todoMessage);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

}
