package tttn.duong.moneyManager.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tttn.duong.moneyManager.service.IncomeService;

/**
 * Bean definitions for {@link IncomeController}.
 */
@Generated
public class IncomeController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'incomeController'.
   */
  private static BeanInstanceSupplier<IncomeController> getIncomeControllerInstanceSupplier() {
    return BeanInstanceSupplier.<IncomeController>forConstructor(IncomeService.class)
            .withGenerator((registeredBean, args) -> new IncomeController(args.get(0)));
  }

  /**
   * Get the bean definition for 'incomeController'.
   */
  public static BeanDefinition getIncomeControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(IncomeController.class);
    beanDefinition.setInstanceSupplier(getIncomeControllerInstanceSupplier());
    return beanDefinition;
  }
}
