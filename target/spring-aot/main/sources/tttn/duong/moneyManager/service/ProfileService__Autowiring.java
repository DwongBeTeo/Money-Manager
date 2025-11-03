package tttn.duong.moneyManager.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ProfileService}.
 */
@Generated
public class ProfileService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ProfileService apply(RegisteredBean registeredBean, ProfileService instance) {
    AutowiredFieldValueResolver.forRequiredField("activationURL").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
