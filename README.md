# jleopard-mvc
---
# Servlet极速开发脚手架

* 技术实现
    * [x] 基于Servlet 3.1标准改造
        * `Spring MVC`风格化
            1. 使用注解`@Controller`注解标记
            2. 请求映射方法自定义 `@RequestMapping(value = "",method = Method.POST)`(默认允许所有请求方法)
            3. 自动初始化方法内参数(复杂类型自动匹配字段名赋值)
            4. 支持返回json数据，只需标注`@RenderJson`注解即可
            ```java
           @Controller
           public class TableController {
           
               @Inject
               private DinnerTableService service;
           
               @RequestMapping("/table")
               @RenderJson
               public List<DinnerTable> list(String name){
                   return service.query();
               }
           
               @RequestMapping(value = "/table1",method = Method.POST)
               @RenderJson
               public DinnerTable table(DinnerTable t){
                   System.out.println("获取到的参数-->" + t);
                   return t;
               }
           }
             ```
        * 实现`IOC`，`DI`功能
            1. 在类上标注`@Bean`，`@Controller`注解就会扫描添加到bean容器内
            2. 运用时只需在字段上标注`@Inject`注解即可完成自动注入
            ```java
            @Bean
            public class OrderDetailService {
            
                @Inject
                private OrderDetialDao dao;
            
                public int add(OrderDetail od) {
                    return dao.insert(od);
            
                }
            
                public List<OrderDetail> query() {
                    return dao.select();
                }
            
                public List<OrderDetail> findByOrderid(Integer id) {
                    OrderDetail var1 = new OrderDetail();
                    var1.setId(id);
                    return dao.select(var1);
                }
            
            }
            ```
    * [x] [Jleopard](http:www.jleopard.org) ORM框架
        * 详细用法-->[传送门](https://www.github.com/chg122345/jleopard)
* 数据库
    * [x] MySQL数据库
* 项目结构
    ```
    -org.jleopard.ihotel
        -config  项目基础配置，数据库信息配置
        -controller Controller类
        -core 实现Spring MVC风格核心注解等
        -dao 数据持久层
        -entity 实体类
        -filter 过滤器
        -service 业务逻辑层
        -util 工具包
        -DispatcherServlet 最最最核心Servlet
    ```
    
