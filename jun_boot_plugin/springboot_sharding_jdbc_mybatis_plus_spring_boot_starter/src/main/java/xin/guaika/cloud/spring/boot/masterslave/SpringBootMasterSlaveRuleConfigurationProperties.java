package xin.guaika.cloud.spring.boot.masterslave;

import io.shardingjdbc.core.yaml.masterslave.YamMasterSlaveRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Master slave rule configuration properties.
 *
 * @author Wujun
 */
@ConfigurationProperties(prefix = "sharding.jdbc.config.masterslave")
public class SpringBootMasterSlaveRuleConfigurationProperties extends YamMasterSlaveRuleConfiguration {
}
