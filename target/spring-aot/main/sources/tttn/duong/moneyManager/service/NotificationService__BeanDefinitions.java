package tttn.duong.moneyManager.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tttn.duong.moneyManager.repository.ProfileRepository;

/**
 * Bean definitions for {@link NotificationService}.
 */
@Generated
public class NotificationService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'notificationService'.
   */
  private static BeanInstanceSupplier<NotificationService> getNotificationServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<NotificationService>forConstructor(ProfileRepository.class, EmailService.class, ExpenseService.class)
            .withGenerator((registeredBean, args) -> new NotificationService(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'notificationService'.
   */
  public static BeanDefinition getNotificationServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(NotificationService.class);
    InstanceSupplier<NotificationService> instanceSupplier = getNotificationServiceInstanceSupplier();
    instanceSupplier = instanceSupplier.andThen(NotificationService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
