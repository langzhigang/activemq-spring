package com.lzg.activemq.service;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("ProducerService")
public class ProducerServiceImpl {
	
	@Resource
	private JmsTemplate jmsTemplate;  
    
	@Autowired  
    @Qualifier("responseQueue")  
    private Destination responseDestination;
	
    public void sendMessage(Destination destination, final String message) {  
        System.out.println("---------------生产者发送消息-----------------");  
        System.out.println("---------------生产者发了一个消息：" + message);  
        jmsTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
            	TextMessage textMessage = session.createTextMessage(message);
            	textMessage.setJMSReplyTo(responseDestination);	//设置回复队列
                return textMessage;  
            }  
        });  
    }   
    
    public void sendMessage(Destination destination, final Serializable obj) {  
    	//未使用MessageConverter的情况  
//        jmsTemplate.send(destination, new MessageCreator() { 
//     
//            public Message createMessage(Session session) throws JMSException { 
//                ObjectMessage objMessage = session.createObjectMessage(obj); 
//                return objMessage; 
//            } 
//             
//        });  
        //使用MessageConverter的情况  
        jmsTemplate.convertAndSend(destination, obj); 
    }  
}
