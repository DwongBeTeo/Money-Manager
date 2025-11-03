package tttn.duong.moneyManager.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tttn.duong.moneyManager.service.ExpenseService;

/**
 * Bean definitions for {@link ExpenseController}.
 */
@Generated
public class ExpenseController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'expenseController'.
   */
  private static BeanInstanceSupplier<ExpenseController> getExpenseControllerInstanceSupplier() {
    return BeanInstanceSupplier.<ExpenseController>forConstructor(ExpenseService.class)
            .withGenerator((registeredBean, args) -> new ExpenseController(args.get(0)));
  }

  /**
   * Get the bean definition for 'expenseController'.
   */
  public static BeanDefinition getExpenseControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ExpenseController.class);
    beanDefinition.setInstanceSupplier(getExpenseControllerInstanceSupplier());
    return beanDefinition;
  }
}
