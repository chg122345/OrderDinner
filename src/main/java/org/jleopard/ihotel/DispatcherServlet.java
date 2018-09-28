package org.jleopard.ihotel;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jleopard.ihotel.core.annotation.Controller;
import org.jleopard.ihotel.core.annotation.RenderJson;
import org.jleopard.ihotel.core.annotation.RequestMapping;
import org.jleopard.ihotel.core.bean.MappingInfo;
import org.jleopard.ihotel.util.BeanUtil;
import org.jleopard.ihotel.util.ClassUtil;
import org.jleopard.util.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.jleopard.ihotel.config.DemoConfigConstant.CONTROLLER_PACKAGE;
import static org.jleopard.ihotel.core.ienum.Method.ALL;

@Slf4j
@WebServlet(name = "dispatcherServlet",urlPatterns = "/",loadOnStartup = 1)
public class DispatcherServlet extends javax.servlet.http.HttpServlet {

    private Map<String, MappingInfo> map = new HashMap<>();


    public DispatcherServlet() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initHandlerMapping();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String context = req.getContextPath();
        String url = req.getRequestURI();
        String uri = url.replace(context,"");
        String method = req.getMethod();
        log.info("进入baseServlet"+" url :" + url +" context :" +context);
        renderMapping(req, resp, uri, method);
    }



    @Override
    public void destroy() {
        super.destroy();
    }

    /**
     * 路由渲染页面
     */
    private void render(HttpServletRequest request, HttpServletResponse response, Object value,boolean renderJson)
            throws ServletException, IOException {
        if (value == null) {
            request.setAttribute("error","请求页面没有找到！");
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
            return;
        }
        if (renderJson){
            String var = JSON.toJSONString(value);
            response.getWriter().write(var);
            response.getWriter().close();
        } else {
            if (value instanceof String){
                if (((String) value).startsWith("redirect:")){
                    String uri = ((String) value).replace("redirect:","");
                    response.sendRedirect(uri);
                }else {
                    String page = value + ".jsp";
                    request.getRequestDispatcher(page).forward(request, response);
                }
            } else {
               throw new RuntimeException("请求方法没有 @RenderJson注解");
            }
        }
    }

    /**
     *  匹配uri映射
     * @param req
     * @param resp
     * @param uri
     * @param method
     * @return
     */
    private void renderMapping(HttpServletRequest req, HttpServletResponse resp, String uri, String method) throws ServletException, IOException {
        Object returnValue = null;
        boolean renderJson = false;
        try {
            for (Map.Entry<String, MappingInfo> $var : map.entrySet()){
                if (uri.equals($var.getKey())){
                    MappingInfo var1 = $var.getValue();
                    Method var2 = var1.getMethod();
                    if ((var1.getImed() == ALL) || method.equalsIgnoreCase(var1.getImed().getValue())){
                        returnValue = var2.invoke(var1.getNewInstance(), req,resp);
                        renderJson = var1.isRenderJson();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnValue = "/error/error.jsp";
        }
        render(req,resp,returnValue,renderJson);
    }

    private void initHandlerMapping(){
        Set<Class<?>> set = ClassUtil.scanPackage(CONTROLLER_PACKAGE).stream().filter(i -> i.isAnnotationPresent(Controller.class)).collect(Collectors.toSet());
        set.stream().forEach(i->{
            String var1 = "";
            Object newInstance = null;
            try {
                newInstance = i.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Method[] methods = i.getDeclaredMethods();
            if (i.isAnnotationPresent(RequestMapping.class)){
                RequestMapping requestMapping = i.getDeclaredAnnotation(RequestMapping.class);
                String[] var3 = requestMapping.value();
                for (String var$ : var3){
                    if (StringUtil.isEmpty(var$)){
                        continue;
                    }
                    if (!var$.startsWith("/")){
                        var1 = "/" + var$;
                    }else {
                        var1 = var$;
                    }
                    addMapping(var1, newInstance, methods);
                }
            }else {
                addMapping(var1, newInstance, methods);
            }

        });
    }

    private void addMapping(String var1, Object newInstance, Method[] methods) {
        String var2;
        for (Method method : methods) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping$ = method.getDeclaredAnnotation(RequestMapping.class);
                String[] var4 = requestMapping$.value();
                boolean renderJson = false;
                if (method.isAnnotationPresent(RenderJson.class)) {
                    renderJson = true;
                }
                for (String var$1 : var4) {
                    if (StringUtil.isNotEmpty(var$1) && !var$1.startsWith("/")) {
                        var2 = "/" + var$1;
                    } else {
                        var2 = var$1;
                    }
                    String url = var1 + var2;
                    MappingInfo mappingInfo = new MappingInfo(url, requestMapping$.method(), newInstance, method, renderJson);
                    map.put(url, mappingInfo);
                }
            }
        }
    }

}
