package post.post.App;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import post.Mail;
import post.post.FrontDesk;

/**
 * Created by yubraj on 7/28/16.
 */
public class FrontMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-front.xml");
        FrontDesk frontDesk = (FrontDesk) applicationContext.getBean("frontDesk");
        frontDesk.sendMail(new Mail("1234", "US", 1.5));
    }
}
