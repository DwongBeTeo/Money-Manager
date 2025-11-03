package tttn.duong.moneyManager.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tttn.duong.moneyManager.repository.ProfileRepository;

/**
 * Bean definitions for {@link AppUserDetailsService}.
 */
@Generated
public class AppUserDetailsService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'appUserDetailsService'.
   */
  private static BeanInstanceSupplier<AppUserDetailsService> getAppUserDetailsServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AppUserDetailsService>forConstructor(ProfileRepository.class)
            .withGenerator((registeredBean, args) -> new AppUserDetailsService(args.get(0)));
  }

  /**
   * Get the bean definition for 'appUserDetailsService'.
   */
  public static BeanDefinition getAppUserDetailsServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AppUserDetailsService.class);
    beanDefinition.setInstanceSupplier(getAppUserDetailsServiceInstanceSupplier());
    return beanDefinition;
  }
}
