package cn.south.toast.common.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.south.toast.common.dto.AppResultObj;
import cn.south.toast.common.security.SSOException;


/**
 * 改写自生活馆项目 
 * @author huangbh
 * 异常输出界面统一格式
 */
@Controller
@RequestMapping(value = "/error")
public class GlobalErrorController implements ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(GlobalErrorController.class);
	
	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping
	@ResponseBody
	public ResponseEntity<Object> error(HttpServletRequest request) {
		
		HttpStatus status = getStatus(request);
		
		int val = status.value();
		
		if(val == HttpStatus.NOT_FOUND.value()){
			return error404(request);
		}
		
		if(val == HttpStatus.UNAUTHORIZED.value()){
			return error401(request);
		}
		
		if(val == HttpStatus.FORBIDDEN.value()){
			return error500(request);
		}
		
		return error500(request);
	}
	
	@RequestMapping(value = "/500")
	@ResponseBody
	public ResponseEntity<Object> error500(HttpServletRequest request) {
		Object out=AppResultObj.serverError();
		Throwable throwable= errorAttributes.getError(new ServletRequestAttributes(request));
		logger.error("服务器错误", throwable);
		
		if (throwable != null) {
			if(throwable instanceof SSOException){
				SSOException ssoException = (SSOException) throwable;
				out = ssoException.getData();
			}else if(throwable instanceof AccessDeniedException){
				String msg=throwable.getMessage();
				out = StringUtils.isBlank(msg) ? out = AppResultObj.notAllow() : AppResultObj.notAllow(msg);
			}else{
				out=AppResultObj.serverError();
			}
		}
		return new ResponseEntity<Object>(out, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/401")
	@ResponseBody
	public ResponseEntity<Object> error401(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Object>(AppResultObj.notAllow(), status);
	}
	
	@RequestMapping(value = "/404")
	@ResponseBody
	public ResponseEntity<Object> error404(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Object>(AppResultObj.newResult(404,"你访问的页面不存在"), status);
	}

	/**
	* 获取错误编码
	* @param request
	* @return
	*/
	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try {
			return HttpStatus.valueOf(statusCode);
		} catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
