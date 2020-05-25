package com.example.webflux.aop.aspects;

import com.example.webflux.common.exception.LocalException;
import com.example.webflux.common.utils.CommonUtils;
import com.example.webflux.domain.TraceInfoBean;
import com.example.webflux.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/13 - 23:07
 * @author: Mr_Bangb
 * @description 切面，用于记录controller 请求参数，响应参数，以及线程变量设置
 * 方法有缺陷：目前只适用于POST请求，并约定第一个参数为 requestBody
 */
@Aspect
@Component
@Slf4j
public class LoggerAspect {
    /**
     * 需要获取的参数名称
     */
    private static final String PARAMS_NAME = "requestBody";

    /**
     * 定义切入点
     * com.example.webflux.controller 包下所有公共的任意返回类型的方法
     * 切入顺序
     * 有异常时：环绕通知增强方法->前置通知->环绕通知连接点->目标方法->（环绕通知异常处理）->后置通知->异常通知
     * 无异常时：环绕通知增强方法->前置通知->环绕通知连接点->目标方法->环绕通知后续方法->后置通知->后置返回通知
     */
    @Pointcut("execution(public * com.example.webflux.controller.*.*(..))")
    private void adivceWithExecution(){}

    /**
     * 定义切入点
     * 方法上带有自定义注解@TrackerAnnotation的方法才会切入
     * 切入顺序
     * 有异常时：环绕通知增强方法->前置通知->环绕通知连接点->目标方法->（环绕通知异常处理）->后置通知->异常通知
     * 无异常时：环绕通知增强方法->前置通知->环绕通知连接点->目标方法->环绕通知后续方法->后置通知->后置返回通知
     */
//    @Pointcut("@annotation(com.example.webflux.aop.annotation.TrackerAnnotation)")
    public void adviceWithAnnotation() {}

    /**
     * 前置通知
     * 在连接点前面执行，如果连接点前有增强方法，会先执行增强方法，直到连接点，再去执行前置通知
     * 前置通知不会影响连接点的执行，除非此处抛出异常。
     * @param jp
     */
    @Before("adivceWithExecution()")
    public void beforeAdvice(JoinPoint jp) {
        log.info("----------------------------< beforeAdvice is running >----------------------------");

        // 获取指定参数
        Object requestBody = getSignature(jp);
        if (requestBody instanceof ResponseVO) {
            TraceInfoBean traceInfo = ((ResponseVO) requestBody).getTraceInfoBean();
            // 利用ThreadLocal保存全局变量
            CommonUtils.setTraceInfo((TraceInfoBean) traceInfo);
        } else {
            // 利用ThreadLocal保存全局变量
            CommonUtils.setTraceInfo(new TraceInfoBean());
        }

        log.info("----------------------------< beforeAdvice is over >----------------------------");
    }


    /**
     * 后置返回通知
     * 在连接点正常执行完成后执行，如果连接点抛出异常，则不会执行。
     * 可以获取到环绕通知的返回值
     * @param jsonStr 返回结果
     */
    @AfterReturning(returning="jsonStr",pointcut="adivceWithExecution()")
    public <T> void afterReturnAdvice(JoinPoint jp,Object jsonStr) {
        log.info("----------------------------< afterReturnAdvice is running >----------------------------");
        jp.getSignature();
        // 打印响应结果
        log.info("----------------------------< jsonStr:{} >----------------------------", jsonStr);

        if (jsonStr instanceof ResponseVO) {
            ResponseVO responseVO = (ResponseVO)jsonStr;
            responseVO.setTraceInfoBean(CommonUtils.getTraceInfo());
        }

        // 清空LocalThread 避免内存泄露
        CommonUtils.cleanResource();

        log.info("----------------------------< afterReturnAdvice is over >----------------------------");
    }

    /**
     * 后置通知
     * 在连接点执行完成后执行，不管是正常执行完成，还是抛出异常，都会执行返回通知中的内容。
     * @param jp
     */
    @After("adivceWithExecution()")
    public void afterAdvice(JoinPoint jp) {
        log.info("----------------------------< afterAdvice is running >----------------------------");
        // TODO if you want
        log.info("----------------------------< afterAdvice is over >----------------------------");
    }


    /**
     * 异常通知
     * 在连接点抛出异常后执行
     * 但是不会捕获原方法抛出的异常
     * 参数中异常的类型，决定了发生何种异常才会进入该方法
     * 因此用法是针对特定的异常进行处理
     * 现在已经不建议使用了，通常在全局异常处理器中统一处理异常
     * @param jp
     */
//    @AfterThrowing(value="adivceWithExecution()",throwing = "e")
    public void afterThrowing(JoinPoint jp, LocalException e) {
        log.info("----------------------------< afterThrowing is running >----------------------------");

        // 打印异常信息
        String method  = jp.getSignature().getName();
        log.error("method:{} has happened an BusinessException ,the exception message is {}",method,e.getMessage());

        log.info("----------------------------< afterThrowing is over >----------------------------");
    }

    /**
     * 环绕通知
     * 环绕通知围绕在连接点前后，比如一个方法调用的前后。能够对原有方法增强。
     * 而且，环绕通知还需要负责决定是继续处理join point(调用ProceedingJoinPoint的proceed方法)还是中断执行
     * 可以设置返回值，返回值即为目标方法的返回值。
     * @param jp
     */
    @Around("adivceWithExecution()")
    public <T> ResponseVO<T> processRecorder(ProceedingJoinPoint jp) throws Throwable {
        log.info("----------------------------< processRecorder around is running >----------------------------");

        // 执行切点方法
        Object proceed = jp.proceed();

        log.info("----------------------------< processRecorder around is over >----------------------------");
        return (ResponseVO<T>) proceed;
    }

    /**
     * @param jp
     * @return
     */
    private Object getSignature(JoinPoint jp){
        Object ret = null;
        // 获取方法签名
        Signature signature = jp.getSignature();
        log.info("----------------------------< signature:{} >----------------------------",signature);
        //获取目标类名
        String className = jp.getTarget().getClass().toString();
        log.info("----------------------------< className:{} >----------------------------",className);
        // 获取目标类简称
        className = className.substring(className.indexOf("com"));
        log.info("----------------------------< shortClassName：{} >----------------------------",className);
        //获取目标方法名
        String methodName = signature.getName();
        log.info("----------------------------< methodName：{} >----------------------------",methodName);

        // 获取连接点所有参数
        Object[] parames = jp.getArgs();
        log.info("----------------------------< objects:{} >----------------------------",parames.toString());
        if (ArrayUtils.getLength(parames) == 0) {
           return Optional.empty();

        }

        // 获取所有参数名字
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        log.info("----------------------------< parameterNames:{} >----------------------------",Arrays.asList(parameterNames));

        // 获取指定名称参数
        int index = ArrayUtils.indexOf(parameterNames,PARAMS_NAME);
        if (index != -1) {
            ret = parames[index];
            log.info("----------------------------< {}_{} >----------------------------",PARAMS_NAME,parames[index]);
        } else {
            ret = parames[0];
        }
        return ret;
    }

}