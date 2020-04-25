package com.czbank.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.*;

/**
 * @author foreverActiveBoy
 * @date 2020/4/25 18:39
 * @apiNote 实现mybatis 执行的sql记录
 */
@Intercepts(value = {
        @Signature(type = Executor.class,method = "query",args ={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class,method = "query",args ={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class,method = "update",args ={MappedStatement.class, Object.class})
})
@Slf4j
@Component
public class MybatisLogInterceptor implements Interceptor {
    /**
     * 参数长度
     */
    private final int ARGS_LENGTH = 6;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //  获取拦截方法的参数
        Object[] args = invocation.getArgs();
        //  存放的是SQL的参数[它是一个实例对象]
        Object parameterObject  = args[1];
        //  MappedStatement对象对应Mapper.xml配置文件中的一个select/update/insert/delete节点，描述的就是一条SQL语句
        MappedStatement mappedStatement = (MappedStatement) args[0];
        //  mapper包路径
        String mapperName = mappedStatement.getResource();
        //  dao接口包路径+dao方法名字
        String daoMethodName = mappedStatement.getId();
        //  sql对应的java类
        BoundSql boundSql = null;
        if (args.length == ARGS_LENGTH){
            boundSql = (BoundSql)args[5];
        }else{
            boundSql = mappedStatement.getBoundSql(parameterObject);
        }
        //  获取带占位符的sql
        String sql = boundSql.getSql();

        Configuration configuration = mappedStatement.getConfiguration();

        String targetSql = assembleSql(configuration, boundSql);

        long startTime = System.nanoTime();
        //  放行
        Object o = invocation.proceed();
        long endTime = System.nanoTime();
        //  执行耗时
        double appleTime = (endTime - startTime) / 1E9;
        log.info("mapper名字mapperName:[{}] and dao方法名字:[{}] and sql耗时applyTime:[{}]秒 and targetSql:[{}]",mapperName,daoMethodName,appleTime,targetSql);
        return o;
    }

    /**
     * 组装sql信息
     *
     * @param configuration
     * @param boundSql
     * @return
     */
    private String assembleSql(Configuration configuration, BoundSql boundSql) {
        Object sqlParameter = boundSql.getParameterObject();
        //  这个ParameterMapping表示当前SQL绑定的是哪些参数,及参数类型,但并不是参数本身
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //  更改sql样式在这个更改
        String sql = boundSql.getSql().replaceAll("[\\s+]", "").replaceAll("from", "\tfrom\t").replaceAll("select", "\tselect\t");
        if (parameterMappings.size() > 0 && sqlParameter != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(sqlParameter.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(sqlParameter));
            } else {
                MetaObject metaObject = configuration.newMetaObject(sqlParameter);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }

        }
        return sql;
    }


    /**
     * 获取参数对应string值
     *
     * @param obj
     * @return
     */
    private String getParameterValue(Object obj) {
        String value = "";
        if (obj instanceof String) {
            value = "'".concat(obj.toString()).concat("'");
        } else if (obj instanceof Date) {
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'".concat(dateFormat.format(new Date())) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value != null ? makeStringAllRegExp(value) : value;
    }

    /**
     * 转义正则特殊字符串
     *
     * @param str
     * @return
     */
    private String makeStringAllRegExp(String str) {
        if (str != null && !str.equals("")) {
            return str.replace("\\", "\\\\").replace("*", "\\*")
                    .replace("+", "\\+").replace("|", "\\|")
                    .replace("{", "\\{").replace("}", "\\}")
                    .replace("(", "\\(").replace(")", "\\)")
                    .replace("^", "\\^").replace("$", "\\$")
                    .replace("[", "\\[").replace("]", "\\]")
                    .replace("?", "\\?").replace(",", "\\,")
                    .replace(".", "\\.").replace("&", "\\&");
        }
        return str;
    }

    /**
     * plugin方法是拦截器用于封装目标对象的，通过该方法我们可以返回目标对象本身，也可以返回一个它的代理。
     * 当返回的是代理的时候我们可以对其中的方法进行拦截来调用intercept方法，当然也可以调用其他方法
     * 对于plugin方法而言，其实Mybatis已经为我们提供了一个实现。Mybatis中有一个叫做Plugin的类，
     * 里面有一个静态方法wrap(Object target,Interceptor interceptor)，通过该方法可以决定要返回的对象是目标对象还是对应的代理。
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        if(target instanceof Executor){
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
