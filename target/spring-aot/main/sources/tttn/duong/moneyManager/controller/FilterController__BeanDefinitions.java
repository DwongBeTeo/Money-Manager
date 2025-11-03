package tttn.duong.moneyManager.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tttn.duong.moneyManager.service.ExpenseService;
import tttn.duong.moneyManager.service.IncomeService;

/**
 * Bean definitions for {@link FilterController}.
 */
@Generated
public class FilterController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'filterController'.
   */
  private static BeanInstanceSupplier<FilterController> getFilterControllerInstanceSupplier() {
    return BeanInstanceSupplier.<FilterController>forConstructor(IncomeService.class, ExpenseService.class)
            .withGenerator((registeredBean, args) -> new FilterController(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'filterController'.
   */
  public static BeanDefinition getFilterControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FilterController.class);
    beanDefinition.setInstanceSupplier(getFilterControllerInstanceSupplier());
    return beanDefinition;
  }
}
