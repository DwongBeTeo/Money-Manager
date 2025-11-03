package tttn.duong.moneyManager.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link NotificationService}.
 */
@Generated
public class NotificationService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static NotificationService apply(RegisteredBean registeredBean,
      NotificationService instance) {
    AutowiredFieldValueResolver.forRequiredField("frontendUrl").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
