
import java.util.Date;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzg.activemq.pojo.Email;
import com.lzg.activemq.service.ProducerServiceImpl;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ProducerConsumerTest {
 
    @Autowired
    private ProducerServiceImpl producerService;
    
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;
    
    @Autowired  
    @Qualifier("sessionAwareQueue")  
    private Destination sessionAwareQueue;  
    
    @Autowired  
    @Qualifier("adapterQueue")  
    private Destination adapterQueue; 
    
    @Test
    public void testSend() {
        for (int i=0; i<2; i++) {
            producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));
            
        }
    }
    
    @Test  
    public void testSessionAwareMessageListener() {  
        producerService.sendMessage(sessionAwareQueue, "测试SessionAwareMessageListener");  
    }  
    
    @Test  
    public void testMessageListenerAdapter() {  
        producerService.sendMessage(adapterQueue, "测试MessageListenerAdapter");  
    }  
    
    @Test
    public void testObjectMessage() {
        Email email = new Email("主题",new Date() ,"内容",122);
//        producerService.sendMessage(destination, email);
        producerService.sendMessage(adapterQueue, email);
    }

}
