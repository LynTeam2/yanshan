package com.xingkong.lyn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lyn on 2017/6/20.
 * 管理后台操作日志注解
 * @Target 注解的对象范围
 * ElementType
 * 1.CONSTRUCTOR:用于描述构造器
 * 2.FIELD:用于描述域
 * 3.LOCAL_VARIABLE:用于描述局部变量
 * 4.METHOD:用于描述方法
 * 5.PACKAGE:用于描述包
 * 6.PARAMETER:用于描述参数
 * 7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 *
 * @RetentionPolicy 注解的存在时间
 * 1.SOURCE 只存在于源代码中，编译时忽略
 * 2.CLASS 在编译时保留，但只存在于class文件中，JVM忽略
 * 3.RUNTIME JVM保留
 *
 * @Documented
 * 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
 *
 * @Inherited
 * annotation类型是被标注过的class的子类所继承。类并不从它所实现的接口继承annotation，方法并不从它所重载的方法继承annotation。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminLog {
    String value() default "";
}
