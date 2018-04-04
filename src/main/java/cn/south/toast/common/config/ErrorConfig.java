package cn.south.toast.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cn.south.toast.common.dto.AppResultObj;




/**
 * @author huangbh
 * 异常配置
 */
@Configuration
@ControllerAdvice
public class ErrorConfig extends ResponseEntityExceptionHandler {
	
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
//                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
//                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
//                container.addErrorPages(new ErrorPage(java.lang.Throwable.class, "/error/500"));
//            }
//        };
//    }
	
    @Bean
    public GlobalErrorController globalErrorController(){
    	return new GlobalErrorController();
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuffer stringBuffer = new StringBuffer();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            stringBuffer.append(error.getField());
            stringBuffer.append(error.getDefaultMessage());
            stringBuffer.append(",");
        }
        AppResultObj resultObj = new AppResultObj(AppResultObj.CODE_PARAM_ERR, stringBuffer.toString(), null, false);
        return new ResponseEntity<Object>(resultObj, headers, HttpStatus.OK);
    }
}
