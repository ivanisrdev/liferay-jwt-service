package com.sample.jwt.service.config;

import aQute.bnd.annotation.ProviderType;
import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.sample.jwt.service.constants.JwtServiceConstants;

/**
 * @author Ivan Sánchez
 */
@ProviderType
@ExtendedObjectClassDefinition(
        category = JwtServiceConstants.CONFIGURATION_CATEGORY,
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = JwtServiceConstants.WIDGET_CONFIGURATION_NAME,
        localization = JwtServiceConstants.CONFIGURATION_LOCALIZATION
)
public interface JwtServiceConfiguration {

    @Meta.AD(
            deflt = "",
            required = false
    )
    String endpoint();


}