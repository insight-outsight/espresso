package org.ootb.espresso.springcloud.infrastructure;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *  NDC和MDC
 *  
    Java中使用的日志的实现框架有很多种，常用的log4j和logback以及java.util.logging，而log4j是apache实现的一个开源日志组件（Wrapped implementations），
    logback是slf4j的原生实现（Native implementations）。需要说明的slf4j是Java简单日志的门面(The Simple Logging Facade for Java)，
    如果使用slf4j日志门面，必须要用到slf4j-api，而logback是直接实现的，所以不需要其他额外的转换以及转换带来的消耗，而slf4j要调用log4j的实现，
    就需要一个适配层，将log4j的实现适配到slf4j-api可调用的模式。
    
    说完基本的日志框架的区别之后，我们再看看NDC和MDC。
    
    不管是log4j还是logback，打印的日志要能体现出问题的所在，能够快速的定位到问题的症结，就必须携带上下文信息（context information），
    那么其存储该信息的两个重要的类就是NDC（Nested Diagnostic Context）和MDC（Mapped Diagnositc Context）。
    
    NDC采用栈的机制存储上下文，线程独立的，子线程会从父线程拷贝上下文。其调用方法如下：
    
    1.开始调用
    NDC.push(message);
    
    2.删除栈顶消息
    NDC.pop();
    
    3.清除全部的消息，必须在线程退出前显示的调用，否则会导致内存溢出。
    NDC.remove();
    
    4.输出模板，注意是小写的[%x]
    log4j.appender.stdout.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ssS}] [%x] : %m%n
    
    MDC采用Map的方式存储上下文，线程独立的，子线程会从父线程拷贝上下文。
    //设置子线程读取MDC变量
    //System.setProperty("log4j2.isThreadContextMapInherimeble", "true");默认应该是true不用设置
    其调用方法如下：
    
    1.保存信息到上下文
    MDC.put(key, value);
    
    2.从上下文获取设置的信息
    MDC.get(key);
    
    3.清楚上下文中指定的key的信息
    MDC.remove(key);
    
    4.清除所有
    clear()
    
    5.输出模板，注意是大写[%X{key}]
    log4j.appender.consoleAppender.layout.ConversionPattern = %-4r [%t] %5p %c %x - %m - %X{key}%n
    
    最后需要注意的是：
    
    Use %X Map中全部数据
    Use %X{key} 指定输出Map中的key的值
    Use %x 输出Stack中的全部内容

 * @author xuzhengchao
 *
 */
public class MDCInterceptor implements HandlerInterceptor {
    
    private Logger logger = LoggerFactory.getLogger(MDCInterceptor.class);
    
    /**
     * 在控制器（Controller)方法调用前执行
     * 返回值为是否中断，true,表示继续执行（下一个拦截器或处理器）
     * false则会中断后续的所有操作，所以当返回false时需要使用response来响应请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("preHandle :" +request.getContextPath());
//        HttpSession session = request.getSession();
//        if (session.getAttribute("user") != null)
//            return true;
//        
        if (DispatcherType.ASYNC.equals(request.getDispatcherType())) {
            return true;
        }
        
        String gender = request.getParameter("gender");
        MDC.put("gender", gender);

        // 跳转登录
//        String url = request.getContextPath() + "/login";
//        response.sendRedirect(url);
        return true;
    }
    /**
     * 在控制器方法调用后，解析视图前调用，我们可以对视图和模型做进一步渲染或修改
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
    }
    /**
     * 整个请求完成，即视图渲染结束后调用，这个时候可以做些资源清理工作，或日志记录等
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
   
    }
    
}