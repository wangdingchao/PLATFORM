package com.example.platform.aop;

import com.example.platform.config.JedisConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by xiaoxuwang on 2018/2/21.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JedisConfiguration.class)
public @interface EnableJedis {
}
