package tttn.duong.moneyManager.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Bean definitions for {@link EmailService}.
 */
@Generated
public class EmailService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'emailService'.
   */
  private static BeanInstanceSupplier<EmailService> getEmailServiceInstanceSupplier() {
    return BeanInstanceSupplier.<EmailService>forConstructor(JavaMailSender.class)
            .withGenerator((registeredBean, args) -> new EmailService(args.get(0)));
  }

  /**
   * Get the bean definition for 'emailService'.
   */
  public static BeanDefinition getEmailServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EmailService.class);
    InstanceSupplier<EmailService> instanceSupplier = getEmailServiceInstanceSupplier();
    instanceSupplier = instanceSupplier.andThen(EmailService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
