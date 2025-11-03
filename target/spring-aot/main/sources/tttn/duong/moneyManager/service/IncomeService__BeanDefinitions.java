package tttn.duong.moneyManager.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tttn.duong.moneyManager.repository.CategoryRepository;
import tttn.duong.moneyManager.repository.IncomeRepository;

/**
 * Bean definitions for {@link IncomeService}.
 */
@Generated
public class IncomeService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'incomeService'.
   */
  private static BeanInstanceSupplier<IncomeService> getIncomeServiceInstanceSupplier() {
    return BeanInstanceSupplier.<IncomeService>forConstructor(CategoryRepository.class, ProfileService.class, IncomeRepository.class)
            .withGenerator((registeredBean, args) -> new IncomeService(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'incomeService'.
   */
  public static BeanDefinition getIncomeServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(IncomeService.class);
    beanDefinition.setInstanceSupplier(getIncomeServiceInstanceSupplier());
    return beanDefinition;
  }
}
