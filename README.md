# SaaS  

说明:

  多租户模式也就是实现单应用的数据库动态切换.根据前端用户带有的数据库标识访问对应的数据库. 
 
  整体实现依赖 Mycat的注解方式 /*#mycat:schema=schema_name*/ sql
 
 1.schema_name 通过dubbo的RpcContext进行传递至DAO层.   @see  com.hitler.common.dubbo.filter.AuthenticationFilter
 
 2.在DAO 层通过Hibernate jpa 拦截器在所有的 HQL/SQL 执行之前加入MyCat定义的注解.  @see com.hitler.common.aop.CheageSchemaInterceptor
 
 3.SQL的路由工作交给Mycat完成.


环境说明:
	1.项目由maven构建.于pom内修改私服地址,当然不修改也没关系.默认会索引中心库.打包运行脚本已实现,可一键运行maven命令 :clean install
	
	2.项目 框架组成: mysql、mycat、spring、jpa (hibernate jpa)、dubbo.
	3.项目内有JPA与spring结合的基础应用环境.需要对JPA与spring 有一定了解.
	4.dubbo,http://dubbo.io ,此项目用的注册中心是zookeeper.所以最少需要安装一个节点的zookeeper.
    
    5.当然最重要的点是mycat,需要安装mycat并配置出多个schema出来. 可自行查找文档配置. 
 
 
