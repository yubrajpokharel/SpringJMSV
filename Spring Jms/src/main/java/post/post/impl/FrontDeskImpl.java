package post.post.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import post.Mail;
import post.post.FrontDesk;

import javax.jms.*;

/**
 * Created by yubraj on 7/28/16.
 */
public class FrontDeskImpl implements FrontDesk {

    public void sendMail(Mail mail) {
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Destination destination = new ActiveMQQueue("mail.queue");

        Connection connection = null;
        try {
            connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);

            MapMessage message = session.createMapMessage();
            message.setString("mailId", mail.getMailId());
            message.setString("country", mail.getCountry());
            message.setDouble("weight", mail.getWeight());
            producer.send(message);

            session.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch (JMSException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
