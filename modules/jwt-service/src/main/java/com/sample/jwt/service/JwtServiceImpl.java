package com.sample.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectSession;
import com.liferay.portal.security.sso.openid.connect.provider.OpenIdConnectSessionProvider;
import com.sample.jwt.service.constants.JwtServiceConstants;
import com.sample.jwt.service.dto.ClaimsToken;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.Base64;

/**
 * @author Ivan Sánchez
 */
@Component(
        immediate = true,
        configurationPid = JwtServiceConstants.WIDGET_CONFIGURATION_NAME,
        service = JwtService.class
)
public class JwtServiceImpl implements JwtService {

    private static final Log _log = LogFactoryUtil.getLog(JwtServiceImpl.class);

    @Reference(policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
    private volatile OpenIdConnectSessionProvider _openIdConnectSessionProvider;

    @Override
    public ClaimsToken getAccessToken(PortletRequest portletRequest, PortletResponse portletResponse) throws JsonProcessingException {

        OpenIdConnectSession openIdConnectSession = getOpenIdConnectSession(portletRequest);
        if (openIdConnectSession == null) {
            _log.error("openIdConnectSession null");
            return null;
        }

        String jwtToken = decodePayloadJwt(openIdConnectSession.getAccessTokenValue());
        ClaimsToken accessToken = (ClaimsToken) deserializeJsonToDto(jwtToken, ClaimsToken.class);

        return accessToken;
    }

    @Override
    public ClaimsToken getRefreshToken(PortletRequest portletRequest, PortletResponse portletResponse) throws JsonProcessingException {
        OpenIdConnectSession openIdConnectSession = getOpenIdConnectSession(portletRequest);
        if (openIdConnectSession == null) {
            _log.error("openIdConnectSession null");
            return null;
        }

        String jwtToken = decodePayloadJwt(openIdConnectSession.getRefreshTokenValue());
        ClaimsToken refreshToken = (ClaimsToken) deserializeJsonToDto(jwtToken, ClaimsToken.class);

        return refreshToken;
    }

    private OpenIdConnectSession getOpenIdConnectSession(PortletRequest portletRequest) {
        OpenIdConnectSession openIdConnectSession = _openIdConnectSessionProvider.getOpenIdConnectSession(PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(portletRequest)).getSession());
        return openIdConnectSession;
    }

    private String decodePayloadJwt(String jwt) {
            DecodedJWT decodedJWT = JWT.decode(jwt);
            return new String(Base64.getUrlDecoder().decode(decodedJWT.getPayload()));
        }

    private Object deserializeJsonToDto(String token, Class<?> aClass) throws JsonProcessingException {
            _log.debug("Deserialize JSON to DTO");
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return mapper.readValue(token, aClass);
    }



}
