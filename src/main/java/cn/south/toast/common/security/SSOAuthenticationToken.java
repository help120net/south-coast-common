package cn.south.toast.common.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


/**
 * 
 * @author huangbh
 *  date of 2018年4月4日
 */
public class SSOAuthenticationToken extends UsernamePasswordAuthenticationToken{
	private static final long serialVersionUID = 1L;
	private String id;
	private String mallId;
	private String mobile;
	private String cityId;
	private String token;
	
	public SSOAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public SSOAuthenticationToken(Object principal, Object credentials,
								  SecurityUser securityUser) {
		super(principal, credentials, securityUser.getAuthorities());
		this.id = securityUser.getId();
		this.mallId = securityUser.getMallId();
		this.token=credentials.toString();
		this.mobile = securityUser.getMobile();
		this.cityId = securityUser.getCityId();
	}

	public SSOAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, String id, String mallId, String mobile) {
		super(principal, credentials, authorities);
		this.id = id;
		this.mallId = mallId;
		this.mobile = mobile;
	}

	public String getMallId() {
		return mallId;
	}

	public String getId() {
		return id;
	}

	public String getMobile() {
		return mobile;
	}

	public String getCityId() {
		return cityId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
