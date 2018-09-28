/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-28  下午5:32
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.util;

import org.jleopard.ihotel.core.annotation.Bean;
import org.jleopard.util.CollectionUtil;
import org.jleopard.util.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class BeanUtil {

    private static volatile Map<String, Object> map = new HashMap<>();

    private static Object getInstance(String name) {
        Lock lock = new ReentrantLock();
        if (CollectionUtil.isEmpty(map) || map.size() == 0){
            if (lock.tryLock()){
                try {
                    initMap();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
        return map.get(name);
    }

    public static <T> T getInstance(Class<T> clazz) {
       return (T) getInstance(StringUtil.firstToLower(clazz.getSimpleName()));
    }

    public static void initMap() {
        Set<Class<?>> set = ClassUtil.scanPackage("org.jleopard.ihotel").stream().filter(i -> i.isAnnotationPresent(Bean.class)).collect(Collectors.toSet());
        set.stream().forEach(i->{
            Bean bean = i.getDeclaredAnnotation(Bean.class);
            String value = bean.value();
            if (StringUtil.isEmpty(value)){
                value = StringUtil.firstToLower(i.getSimpleName());
            }
            try {
                map.put(value,i.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }

}
