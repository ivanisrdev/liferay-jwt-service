package com.sample.user.info.jwt.web.portlet;

import com.sample.user.info.jwt.web.constants.UserInfoJwtWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivan Sánchez
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=" + UserInfoJwtWebPortletKeys.WIDGET_DISPLAY_CATEGORY,
		"com.liferay.portlet.header-portlet-css=" + UserInfoJwtWebPortletKeys.WIDGET_CSS_FILE,
		"com.liferay.portlet.instanceable=" + UserInfoJwtWebPortletKeys.WIDGET_INSTANCEABLE,
		"javax.portlet.display-name=" + UserInfoJwtWebPortletKeys.WIDGET_DISPLAY_NAME,
		"javax.portlet.init-param.template-path=" + UserInfoJwtWebPortletKeys.TEMPLATE_JSP,
		"javax.portlet.init-param.view-template=" + UserInfoJwtWebPortletKeys.VIEW_JSP,
		"javax.portlet.name=" + UserInfoJwtWebPortletKeys.WIDGET_NAME,
		"javax.portlet.resource-bundle=" + UserInfoJwtWebPortletKeys.WIDGET_RESOURCE_LANGUAGE,
		"javax.portlet.security-role-ref=" + UserInfoJwtWebPortletKeys.WIDGET_SECURITY_ROLE
	},
	service = Portlet.class
)
public class UserInfoJwtWebPortlet extends MVCPortlet {
}