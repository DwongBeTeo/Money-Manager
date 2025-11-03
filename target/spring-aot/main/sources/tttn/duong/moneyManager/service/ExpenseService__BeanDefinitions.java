package tttn.duong.moneyManager.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tttn.duong.moneyManager.repository.CategoryRepository;
import tttn.duong.moneyManager.repository.ExpenseRepository;

/**
 * Bean definitions for {@link ExpenseService}.
 */
@Generated
public class ExpenseService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'expenseService'.
   */
  private static BeanInstanceSupplier<ExpenseService> getExpenseServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ExpenseService>forConstructor(CategoryRepository.class, ProfileService.class, ExpenseRepository.class)
            .withGenerator((registeredBean, args) -> new ExpenseService(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'expenseService'.
   */
  public static BeanDefinition getExpenseServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ExpenseService.class);
    beanDefinition.setInstanceSupplier(getExpenseServiceInstanceSupplier());
    return beanDefinition;
  }
}
