# Mybatis-CRUD
基于代理 Dao 实现 CRUD 操作
使用要求：
1、持久层接口和持久层接口的映射配置必须在相同的包下
2、持久层映射配置中 mapper 标签的 namespace 属性取值必须是持久层接口的全限定类名
3、SQL 语句的配置标签<select>,<insert>,<delete>,<update>的 id 属性必须和持久层接口的
方法名相同。
