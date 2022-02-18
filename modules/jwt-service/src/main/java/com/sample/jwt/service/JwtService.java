package com.sample.jwt.service;

import aQute.bnd.annotation.ProviderType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.jwt.service.dto.ClaimsToken;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Ivan Sánchez
 */
@ProviderType
public interface JwtService {

    ClaimsToken getAccessToken(PortletRequest portletRequest, PortletResponse portletResponse) throws JsonProcessingException;
    ClaimsToken getRefreshToken(PortletRequest portletRequest, PortletResponse portletResponse) throws JsonProcessingException;
}