#  # 说明
##方案1:
###在nginx中配置ip_hash;使用粘性session,这样nginx会把同一个ip的请求送给同一个tomcat.但是如果nginx前后还有其他负载均衡的话将会无效.
##方案2:
###nginx中不使用ip_hash;使用redis来存储session.
- 需要redis
- 需要配置Tomcat
- 将context.xml替换Tomcat中的conf文件夹的context.xml(或根据自己的需求配置)
- 复制tomcat-redis-session到Tomcat的lib下
