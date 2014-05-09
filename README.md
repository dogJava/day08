day08
=====

tomcat 6버전 기준
=====

----- servlet 이용
web.xml
~~~~
<servlet>
    <servlet-name>invoker</servlet-name>
    <servlet-class>
      org.apache.catalina.servlets.InvokerServlet
    </servlet-class>
    <init-param>
        <param-name>debug</param-name>
        <param-value>0</param-value>
    </init-param>
    <load-on-startup>2</load-on-startup>
</servlet>
~~~~

주석 제거
~~~~
<servlet-mapping>
    <servlet-name>invoker</servlet-name>
    <url-pattern>/servlet/*</url-pattern>
</servlet-mapping>
~~~~
주석제거

----- 한글 encding 설정
server.xml
~~~~
<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" />
URIEncoding="UTF-8" 속성 추가

servlet class

request.setCharacterEncoding("UTF-8");
~~~~