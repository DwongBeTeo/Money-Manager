package tttn.duong.moneyManager.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tttn.duong.moneyManager.service.DashboardService;

/**
 * Bean definitions for {@link DashboardController}.
 */
@Generated
public class DashboardController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'dashboardController'.
   */
  private static BeanInstanceSupplier<DashboardController> getDashboardControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<DashboardController>forConstructor(DashboardService.class)
            .withGenerator((registeredBean, args) -> new DashboardController(args.get(0)));
  }

  /**
   * Get the bean definition for 'dashboardController'.
   */
  public static BeanDefinition getDashboardControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DashboardController.class);
    beanDefinition.setInstanceSupplier(getDashboardControllerInstanceSupplier());
    return beanDefinition;
  }
}
