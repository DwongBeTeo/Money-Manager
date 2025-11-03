package tttn.duong.moneyManager;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link MoneyManagerApplication}.
 */
@Generated
public class MoneyManagerApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'moneyManagerApplication'.
   */
  public static BeanDefinition getMoneyManagerApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MoneyManagerApplication.class);
    beanDefinition.setInstanceSupplier(MoneyManagerApplication::new);
    return beanDefinition;
  }
}
