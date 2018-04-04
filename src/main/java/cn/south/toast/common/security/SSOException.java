package cn.south.toast.common.security;


import org.springframework.security.core.AuthenticationException;
/**
 * 
 * @author huangbh
 *  date of 2018年4月4日
 */
public class SSOException extends AuthenticationException{
	private static final long serialVersionUID = -1201884880843024132L;
	private Object data;
	
	public SSOException(String msg, Object data) {
		super(msg);
		this.data = data;
	}
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
