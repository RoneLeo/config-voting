package com.chiyun.voting.commons;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MustLogin {
    int[] rolerequired() default 0;//0：全部都可访问;其他则为选择角色可访问
}
