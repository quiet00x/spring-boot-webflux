package com.example.webflux.aop.aspects;

import com.example.webflux.common.exception.LocalException;
import com.example.webflux.common.utils.CommonUtils;
import com.example.webflux.domain.TraceInfoBean;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/13 - 23:07
 * @author: Mr_Bangb
 * @description 切面类，用于记录 controller 请求参数，响应参数，以及全局变量设置
 *     方法有缺陷：目前只适用于POST请求，并约定第一个参数为 requestVO
 */
@Aspect
@Component
@Slf4j
public class LoggerAspect {
    /**
     * 需要获取的参数名称
     */
    private static final String PARAMS_NAME = "requestVO";

    /**
     * 定义切入点：
     *      1. com.example.webflux.controller 包下所有公共的任意返回类型的方法
     *      2. 或者标注有 @TrackerAnnotation的方法
     * 通知执行顺序：
     *  有异常时：环绕通知增强方法->前置通知->环绕通知连接点->目标方法->（环绕通知异常处理）->后置通知->异常通知
     *  无异常时：环绕通知增强方法->前置通知->环绕通知连接点->目标方法->环绕通知后续方法->后置通知->后置返回通知
     *
     * 常用的切入点表达式标签
     *    针对方法：
     *      1. @annotation(自定义注解类全名) 匹配标有注解的任意方法
     *          demo: @annotation(com.example.webflux.aop.annotations.TrackerAnnotation)
     *      2. execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
     *         其中带 ?号的表示可选项，ret-type-pattern,name-pattern, parameters-pattern是必选项
     *         a. modifier-pattern? 访问修饰符，如public 表示匹配公有方法
     *         b. ret-type-pattern 返回值匹配，* 表示任何返回值,全路径的类名等
     *         c. declaring-type-pattern? 类路径匹配
     *         d. name-pattern 方法名匹配，* 代表所有,set*,代表以set开头的所有方法
     *         e. (param-pattern) 参数匹配，指定方法参数(声明的类型),
     *              (..)代表所有参数; ()代表一个参数; (,String)代表第一个参数为任何值,第二个为String类型;
     *         f. throws-pattern? 异常类型匹配
     *          demo:
     *          (1). execution(public * com.example.webflux.controller.*.*(..)) controller包下所有的公共方法
     *          (2). execution(* com.example.webflux.controller..*.*(..)) 定义在 controller 包和子包里的所有公共方法
     *     针对类：
     *       1. within(): 用于匹配指定类型内的方法执行
     *          demo:
     *          (1). within(com.example.webflux.controller.*) controller 包里的任意类的任意方法
     *          (2). within(com.example.webflux.controller…*) controller 包和子包里的任意类任意方法
     *       2. @within(注解类全名) 匹配注解标注的类的任务方法
     *          demo: @within(com.example.webflux.aop.annotations.TrackerAnnotation)
     *       3. this(指定接口) 匹配实现了指定接口的所有实现类中的所有方法
     *       4. @target(注解类全名) 匹配注解标注的类中所有方法
     *
     */
    @Pointcut("execution(public * com.example.webflux.controller.*.*(..))")
    private void adivceWithExecution(){}

    /**
     * 前置通知
     * 在连接点前面执行，如果连接点前有增强方法，会先执行增强方法，直到连接点，再去执行前置通知
     * 前置通知不会影响连接点的执行，除非此处抛出异常。
     * @param jp
     */
    @Before("adivceWithExecution()")
    public <T> void beforeAdvice(JoinPoint jp) {
        // 获取指定参数
        T requestBody = getSignature(jp);

        if (requestBody instanceof RequestVO) {
            TraceInfoBean traceInfo = ((ResponseVO) requestBody).getTraceInfoBean();
            // 利用ThreadLocal保存全局变量
            CommonUtils.setTraceInfo((TraceInfoBean) traceInfo);
        } else {
            // 利用ThreadLocal保存全局变量
            CommonUtils.setTraceInfo(new TraceInfoBean());
        }
    }


    /**
     * 后置返回通知
     * 在连接点正常执行完成后执行，如果连接点抛出异常，则不会执行。
     * 可以获取到环绕通知的返回值
     * @param jsonStr 返回结果
     */
    @AfterReturning(returning="jsonStr",pointcut="adivceWithExecution()")
    public <T> void afterReturnAdvice(JoinPoint jp,Object jsonStr) {
        // 打印响应结果
        log.info("----------------------------< jsonStr:{} >----------------------------", jsonStr);

        if (jsonStr instanceof ResponseVO) {
            ResponseVO responseVO = (ResponseVO)jsonStr;
            responseVO.setTraceInfoBean(CommonUtils.getTraceInfo());
        }

        // 清空LocalThread 避免内存泄露
        CommonUtils.cleanResource();
    }

    /**
     * 后置通知
     * 在连接点执行完成后执行，不管是正常执行完成，还是抛出异常，都会执行返回通知中的内容。
     * @param jp
     */
    @After("adivceWithExecution()")
    public void afterAdvice(JoinPoint jp) {
        // TODO if you want
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
        // 打印异常信息
        String method  = jp.getSignature().getName();
        log.error("method:{} has happened an BusinessException ,the exception message is {}",method,e.getMessage());
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
    private <T> T getSignature(JoinPoint jp){
        // 获取方法签名
        Signature signature = jp.getSignature();
        //获取目标类名
        String className = jp.getTarget().getClass().toString();
        // 获取目标类简称
        className = className.substring(className.indexOf("com"));
        //获取目标方法名
        String methodName = signature.getName();
        // 打印调用方堆栈信息
        log.info("----------------------------< API: {}.{} >----------------------------",className,methodName);

        // 获取连接点所有参数
        List<Object> args = Arrays.asList(jp.getArgs());
        log.info("----------------------------< objects:{} >----------------------------",args.toString());
        if (CollectionUtils.isEmpty(args)) {
           return (T) Optional.empty();
        }

        // 获取所有参数名字
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        log.info("----------------------------< parameterNames:{} >----------------------------",Arrays.asList(parameterNames));

        // 获取指定名称参数
        int index = ArrayUtils.indexOf(parameterNames,PARAMS_NAME);
        T ret;
        if (index != -1) {
            log.info("----------------------------< {}_{} >----------------------------",PARAMS_NAME,args.get(index));
            return  (T) args.get(index);
        } else {
            return (T) args;
        }
    }
}