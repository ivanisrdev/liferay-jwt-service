package com.sample.user.info.jwt.web.portlet.render;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.sample.jwt.service.JwtService;
import com.sample.jwt.service.dto.ClaimsToken;
import com.sample.user.info.jwt.web.constants.UserInfoJwtWebPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + UserInfoJwtWebPortletKeys.WIDGET_NAME,
                "mvc.command.name=" + UserInfoJwtWebPortletKeys.TEMPLATE_JSP
        },
        service = MVCRenderCommand.class
)
public class UserInfoJwtMVCRenderCommand implements MVCRenderCommand {

    private static final Log _log = LogFactoryUtil.getLog(UserInfoJwtMVCRenderCommand.class);

    @Reference(policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
    private volatile JwtService _jwtService;

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        _log.info("Inside UserInfoJwtMVCRenderCommand");

        try {
            ClaimsToken accessToken = _jwtService.getAccessToken(renderRequest, renderResponse);
            ClaimsToken refreshToken = _jwtService.getRefreshToken(renderRequest, renderResponse);
            renderRequest.setAttribute("accessTokenEmail" , accessToken.getEmailAddress());
            renderRequest.setAttribute("refreshTokenExp" , refreshToken.getEmailAddress());
        } catch (Exception exception) {
            _log.error(exception.getMessage(), exception);
        }

        return UserInfoJwtWebPortletKeys.VIEW_JSP;
    }

}
