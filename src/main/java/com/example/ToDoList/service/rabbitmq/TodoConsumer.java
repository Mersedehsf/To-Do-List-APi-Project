package com.example.ToDoList.service.rabbitmq;

import com.example.ToDoList.model.entity.ToDoMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@EnableRabbit
public class TodoConsumer {

    @RabbitListener(queues = "todo.queue.all") // wildcard to listen to all user queues
    public void receiveTodoMessage(Message message, Channel channel, MessageProperties messageProperties) throws IOException, InterruptedException {
        Thread.sleep(9000);
        byte[] payload = message.getBody();
        ToDoMessage todoMessage = deserializePayload(payload);

        if (todoMessage != null) {
            System.out.println("Received todo message: " + todoMessage.toString());
            channel.basicAck(messageProperties.getDeliveryTag(), false);
        } else {
            System.out.println("Failed to deserialize message payload");
        }

    }


    private ToDoMessage deserializePayload(byte[] payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(payload, ToDoMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
