package post.post.App;

import jdk.nashorn.internal.objects.NativeRegExp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import post.Mail;
import post.post.BackOffice;

/**
 * Created by yubraj on 7/28/16.
 */
public class BackMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-back.xml");
        BackOffice backOffice = (BackOffice) context.getBean("backOffice");
        Mail mail = backOffice.receiveMail();
        System.out.println("Mail: no : "+mail.getMailId() + " Mail Country : "+mail.getCountry() + " Mail Weight :"+mail.getWeight());
    }
}