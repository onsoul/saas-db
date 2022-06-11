## @Deprecated
## SAAS 原型
## saas-mycat

> 这是15年选择的预研方案,程序框架采用了spring、doubbo、mycat、mysql.
> 整体方案采用mycat做为数据路由,由spring应用在执行SQL前加入mycat的路由注解。以达到数据库动态切换的目的.
> 但是在实践过程中,发现mycat的框架体系把结构化数据库的事务性完全丢失，还包括MYSQL的存储过程无法得到有效满足. 
> 所以整个应用 在doubbo的上下文传递线程变量存在一些参考价值.也许mycat 2.0 或者 TiDB 可以解决这个问题.
 

## saas-mongo

> 近期采用的SAAS结构.
> 依赖于mongodb API的高度扩展与可用,加上spring boot/spring data mongo底层采用了netty实现，在数据连接管理上已完美实现动态切换。

> 所以我们简单实现了通过线程变量传递DB NAME,并重写掉mongoTemplate，此组件具有了以下功能提供参考。

- mongo docment 可任意加字段读出写入.实际上是重写映射结构.
- 可配合前端域名动态切换数据库.
- 注解式实现mongodb在自增主键上的缺陷.

