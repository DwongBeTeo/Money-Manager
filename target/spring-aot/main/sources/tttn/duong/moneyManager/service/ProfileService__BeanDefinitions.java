package tttn.duong.moneyManager.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import tttn.duong.moneyManager.repository.ProfileRepository;
import tttn.duong.moneyManager.util.JwtUtil;

/**
 * Bean definitions for {@link ProfileService}.
 */
@Generated
public class ProfileService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'profileService'.
   */
  private static BeanInstanceSupplier<ProfileService> getProfileServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ProfileService>forConstructor(ProfileRepository.class, EmailService.class, PasswordEncoder.class, AuthenticationManager.class, JwtUtil.class)
            .withGenerator((registeredBean, args) -> new ProfileService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'profileService'.
   */
  public static BeanDefinition getProfileServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProfileService.class);
    InstanceSupplier<ProfileService> instanceSupplier = getProfileServiceInstanceSupplier();
    instanceSupplier = instanceSupplier.andThen(ProfileService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
