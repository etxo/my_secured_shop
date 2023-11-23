package de.telran.my_secured_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

    private final Logger LOGGER = LoggerFactory.getLogger(AspectLogging.class);
    //TASK 2 ==================>
    @Pointcut("execution(* de.telran.my_secured_shop.services.jpa.JpaProductService.*(..))")
    public void getAnyFromJpaProductService(){}
    @Before("getAnyFromJpaProductService()")
    public void logJpaServiceBeforeAny(JoinPoint joinPoint){
        LOGGER.info("Method {} was invoked with parameters {}",
                joinPoint.getSignature(), joinPoint.getArgs());
    }
    @After("getAnyFromJpaProductService()")
    public void logJpaServiceAfterAny(JoinPoint joinPoint){
        LOGGER.info("Method {} has finished", joinPoint.getSignature());
    }
    //TASK 3 =================>
    @Pointcut("execution(* de.telran.my_secured_shop.services..*.*(..))")
    public void getAnyFromServices(){}

    @Before("getAnyFromServices()")
    public void logServiceBeforeAny(JoinPoint joinPoint){
        LOGGER.info("Method {} was invoked with parameter {}.",
                joinPoint.getSignature(),
                joinPoint.getArgs());
    }

    @After("getAnyFromServices()")
    public void logServiceAfterAny(JoinPoint joinPoint){
        LOGGER.info("Method {} of {} has finished its job.",
                joinPoint.getSignature(),
                joinPoint.getSignature().getDeclaringType());
    }
    @AfterReturning("getAnyFromServices()")
    public void logAfterReturnValue(JoinPoint joinPoint) throws Throwable{
        LOGGER.info("Method {} of {} successfully  returned a value.",
                joinPoint.getSignature(),
                joinPoint.getSignature().getDeclaringType());
    }
    @AfterThrowing("getAnyFromServices()")
    public void logAfterThrowingException(JoinPoint joinPoint){
        LOGGER.info("Method {} of {} threw an exception",
                joinPoint.getSignature(),
                joinPoint.getSignature().getDeclaringType());
    }

    @Pointcut("execution(public int de.telran.my_secured_shop.services.jpa.JpaProductService.getCount())")
    public void getProductCount(){}

    @Around("getProductCount()")
    public Object aroundGetCount(ProceedingJoinPoint joinPoint){
        LOGGER.info("Method getProductCount was retrieved");
        try{
            Object result = joinPoint.proceed();
            LOGGER.info("Method getCount of class JpaProductService has finished with result {},", result);
            return 777;
        }catch(Throwable e){
            throw new RuntimeException(e);
        }
    }
}
