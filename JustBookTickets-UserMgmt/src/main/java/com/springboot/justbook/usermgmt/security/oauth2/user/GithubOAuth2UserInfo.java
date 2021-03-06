package com.springboot.justbook.usermgmt.security.oauth2.user;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo {

    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return ((Integer) attributes.get("id")).toString();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
	public String getUserName() {
		return (String) attributes.get("username");
	}

	@Override
	public String getAddress() {
		return (String) attributes.get("address");
	}

	@Override
	public String getContactNo() {
		return (String) attributes.get("phoneNo");
	}
}
