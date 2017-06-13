# zigbee-greenhouse
zigbee课程设计 大棚监控系统 Java服务端(为上位机提供接口API) 

将 \src\main\resources\applicationContext-datasource.xml 里的mysql参数替换为自身的

db.url=jdbc:mysql://127.0.0.1:3306/zigbee?characterEncoding=utf-8 //jdbc连接url
db.username=root  //mysql登录帐号
db.password=root  //mysql登录密码

将 \src\main\resources\logback.xml 里的File/fileNamePattern字段参数替换本地日志保存位置
<File>${CATALINA_HOME}/logs/zigbee_error.log</File>
<!--<File>f:/zigbeelog/error.log</File>-->
<fileNamePattern>${CATALINA_HOME}/logs/zigbee_error.log.%d{yyyy-MM-dd}.gz</fileNamePattern>