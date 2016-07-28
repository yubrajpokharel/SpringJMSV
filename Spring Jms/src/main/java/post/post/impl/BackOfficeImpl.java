package post.post.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import post.Mail;
import post.post.BackOffice;

import javax.jms.*;

/**
 * Created by yubraj on 7/28/16.
 */
public class BackOfficeImpl implements BackOffice{

    public Mail receiveMail() {
        Mail mail = new Mail();
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Destination destination = new ActiveMQQueue("mail.queue");

        Connection conn = null;

        try {
            conn = cf.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(destination);

            conn.start();
            MapMessage message = (MapMessage) consumer.receive();

            mail.setMailId(message.getString("mailId"));
            mail.setCountry(message.getString("country"));
            mail.setWeight(message.getDouble("weight"));

            session.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(conn!=null) {
                try {
                    conn.close();
                } catch (JMSException e) {
                }
            }
        }
        return mail;
    }

}
