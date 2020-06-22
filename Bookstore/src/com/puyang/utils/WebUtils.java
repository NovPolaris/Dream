package com.puyang.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    public static <T, V> T copyParamsToBean(T bean, Map<String, V> map) {
        try {
            BeanUtils.populate(bean, map);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bean;
    }
}
