package com.sample.jwt.service.config;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.sample.jwt.service.constants.JwtServiceConstants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import java.util.Map;


/**
 * @author Ivan Sánchez
 */
@Component(
        immediate = true,
        configurationPid = JwtServiceConstants.WIDGET_CONFIGURATION_NAME,
        service = JwtServiceConfiguration.class
)
public class JwtServiceConfigurationImpl implements JwtServiceConfiguration {

        private volatile JwtServiceConfiguration _configuration;

        @Activate
        @Modified
        protected void active(Map<String, Object> properties) {
                _configuration = ConfigurableUtil.createConfigurable(JwtServiceConfiguration.class, properties);
        }


        @Override
        public String endpoint() {
                return null;
        }
}
