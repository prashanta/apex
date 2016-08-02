package com.gemt.apex.utility;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.gemt.apex.model.response.RestResponse;

@Aspect
@Component
public class WrapperAspect {
	
	@Pointcut("execution(* com.gemt.apex.rest..*.*(..))")
    public void businessMethods() { }
	
	@Around("businessMethods()")
	public Object wrapResponse(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("Wrap start");
		Object result = pjp.proceed();
		System.out.println("Wrap end");
		System.out.println(result.getClass());
		return new RestResponse(result);
   	}
}
