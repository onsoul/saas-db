# SaaS

说明:

  多租户模式也就是实现单应用的数据库动态切换.根据前端用户带有的数据库标识访问对应的数据库. 
 
  整体实现依赖 Mycat的注解方式 /*#mycat:schema=schema_name*/ sql
 
 1.schema_name 通过dubbo的RpcContext进行传递至DAO层.   @see  com.hitler.common.dubbo.filter.AuthenticationFilter
 
 2.在DAO 层通过Hibernate jpa 拦截器在所有的 HQL/SQL 执行之前加入MyCat定义的注解.  @see com.hitler.common.aop.CheageSchemaInterceptor
 
 3.SQL的路由工作交给Mycat完成.

 
 