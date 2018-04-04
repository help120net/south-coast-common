package cn.south.toast.common.security;

/**
 * 
 * @author huangbh
 *  date of 2018年4月4日
 */
public abstract class SSOConstant {
	//App登录用户统一授予次角色
	public static final String ROLE_APP_USER = "ROLE_APP";
	//token key
	public static final String ACCESS_TOKEN = "accessToken";
	
	public static final String REQUEST_DATA = "data";
	
	public static final String DEVICE_TYPE = "deviceType";
	
	public static final String VERSION_CODE = "versionCode";
	
	public static final String APPID = "appId";
	
	public static final int DEVICE_TYPE_IOS = 2;

	public static final int DEVICE_TYPE_ANDROID = 1;
	
}
