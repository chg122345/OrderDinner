package org.jleopard.ihotel;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jleopard.ihotel.core.annotation.*;
import org.jleopard.ihotel.core.bean.MappingInfo;
import org.jleopard.ihotel.util.ClassUtil;
import org.jleopard.util.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.jleopard.ihotel.config.DemoConfigConstant.BASE_PACKAGE;
import static org.jleopard.ihotel.config.DemoConfigConstant.CONTROLLER_PACKAGE;
import static org.jleopard.ihotel.core.ienum.Method.ALL;

@Slf4j
@WebServlet(name = "dispatcherServlet", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends javax.servlet.http.HttpServlet {

    private Map<String, MappingInfo> map = new HashMap<>();

    private Map<String, Object> ioc = new ConcurrentHashMap<>(255);


    public DispatcherServlet() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initHandlerMapping();
        initBeanIoc();
        initInject();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String context = req.getContextPath();
        String url = req.getRequestURI();
        String uri = url.replace(context, "").replaceAll("/+", "/");
        String method = req.getMethod();
        log.info("进入baseServlet" + " url :" + url + " context :" + context);
        renderMapping(req, resp, uri, method);
    }


    @Override
    public void destroy() {
        super.destroy();
        map = null;
        ioc = null;
    }

    /**
     * 路由渲染页面
     */
    private void render(HttpServletRequest request, HttpServletResponse response, Object value, boolean renderJson)
            throws ServletException, IOException {
        if (value == null) {
            /*request.setAttribute("error","请求页面没有找到！");
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);*/
            response.getWriter().write("404 Not Found!!");
            response.getWriter().close();
            return;
        }
        if (renderJson) {
            String var = JSON.toJSONString(value);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(var);
            response.getWriter().close();
        } else {
            if (value instanceof String) {
                if (((String) value).startsWith("redirect:")) {
                    String uri = ((String) value).replace("redirect:", "");
                    response.sendRedirect(uri);
                } else {
                    String page = value + ".jsp";
                    request.getRequestDispatcher(page).forward(request, response);
                }
            } else {
                throw new RuntimeException("请求方法没有 @RenderJson注解");
            }
        }
    }

    /**
     * 匹配uri映射
     *
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
            for (Map.Entry<String, MappingInfo> $var : map.entrySet()) {
                if (uri.equals($var.getKey())) {
                    MappingInfo var1 = $var.getValue();
                    Method var2 = var1.getMethod();
                    if ((var1.getImed() == ALL) || method.equalsIgnoreCase(var1.getImed().getValue())) {
                        returnValue = var2.invoke(var1.getNewInstance(), initMethodParam(req, resp, var2));
                        renderJson = var1.isRenderJson();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().write("500 Exception:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll("\\s", "\r\n"));
            resp.getWriter().close();
        }
        render(req, resp, returnValue, renderJson);
    }

    /**
     * 初始化方法里的参数
     * @param req
     * @param resp
     * @param var2
     * @return
     */
    private Object[] initMethodParam(HttpServletRequest req, HttpServletResponse resp, Method var2) {
        Class<?>[] paraTypes = var2.getParameterTypes();
        Map<String, String[]> paraMap = req.getParameterMap();
        Object[] paraValues = new Object[paraTypes.length];
        for (int i = 0; i < paraTypes.length; i++) {
            Class<?> var$ = paraTypes[i];
            if (var$ == HttpServletRequest.class) {
                paraValues[i] = req;
                continue;
            }else if (var$ == HttpServletResponse.class) {
                paraValues[i] = resp;
                continue;
            }else if (var$ == HttpSession.class) {
                paraValues[i] = req.getSession();
                continue;
            }else if (var$ == String.class) {
                for (Map.Entry<String,String[]> param : paraMap.entrySet()){
                    String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
                    paraValues[i] = value;
                }
                continue;
            }else {  //封装类型
                Field[] fields = var$.getDeclaredFields();
                Object instance = null;
                try {
                    instance= var$.newInstance();
                for (Field field :fields){
                    String value = req.getParameter(field.getName());
                    if (StringUtil.isNotEmpty(value)){
                        field.setAccessible(true);
                        field.set(instance,ClassUtil.changeType(field,value));
                    }
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                paraValues[i] = instance;
            }
        }
        return paraValues;
    }

    /**
     * 初始化映射uri 和 method
     */
    private void initHandlerMapping() {
        Set<Class<?>> set = ClassUtil.scanPackage(CONTROLLER_PACKAGE).stream().filter(i -> i.isAnnotationPresent(Controller.class)).collect(Collectors.toSet());
        set.stream().forEach(i -> {
            String var1 = "";
            Object newInstance = null;
            try {
                newInstance = i.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            String key;
            Controller controller = i.getDeclaredAnnotation(Controller.class);
            key = controller.value();
            if (StringUtil.isEmpty(key)) {
                key = StringUtil.firstToLower(i.getSimpleName());
            }
            ioc.put(key, newInstance);
            Method[] methods = i.getDeclaredMethods();
            if (i.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = i.getDeclaredAnnotation(RequestMapping.class);
                String[] var3 = requestMapping.value();
                for (String var$ : var3) {
                    if (StringUtil.isEmpty(var$)) {
                        continue;
                    }
                    if (!var$.startsWith("/")) {
                        var1 = "/" + var$;
                    } else {
                        var1 = var$;
                    }
                    addMapping(var1, newInstance, methods);
                }
            } else {
                addMapping(var1, newInstance, methods);
            }

        });
    }

    /**
     *  获取 mapping
     * @param var1
     * @param newInstance
     * @param methods
     */
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

    /**
     * 初始化ioc容器
     */
    private void initBeanIoc() {
        Set<Class<?>> set = ClassUtil.scanPackage(BASE_PACKAGE).stream().filter(i -> i.isAnnotationPresent(Bean.class)).collect(Collectors.toSet());
        set.stream().forEach(i -> {
            Bean bean = i.getDeclaredAnnotation(Bean.class);
            String value = bean.value();
            if (StringUtil.isEmpty(value)) {
                value = StringUtil.firstToLower(i.getSimpleName());
            }
            try {
                ioc.put(value, i.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 依赖注入
     */
    private void initInject() {
        for (Map.Entry<String, Object> i : ioc.entrySet()) {
            Object target = i.getValue();
            Class<?> clazz = target.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    Inject inject = field.getAnnotation(Inject.class);
                    String value = inject.value().trim();
                    if (StringUtil.isEmpty(value)) {
                        value = StringUtil.firstToLower(field.getType().getSimpleName());
                    }
                    field.setAccessible(true);
                    try {
                        field.set(target, ioc.get(value));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
