# 描述
springmvc+mybatis +springboot practise

###2019年12月15日

####integrate with  springboot
注意点 
>@SpringBootApplication(scanBasePackages = {"org.creasypita.User","org.creasypita.xxx"})  
>>需要注意的是如果在启动类不加scanBasePackages，则Spring 容器默认只扫描启动类所在包及其子包中的类并识别为Spring bean。如果需要扫描其他非启动类所在的包及子包的组件，需要手动指定包的路径。scanBasePackages = {“org.creasypita.User”}
>
>@MapperScan("org.creasypita.User.mappers")
>>MapperScan 目前只找到在 SpringBoot 启动类中配置方式，没有找到在  properties中配置的方式
>>mybatis configuration reference : https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
### 2019年12月13日

#### spring-context.xml 说明 springmvc 启用的配置的注意点
+ context:component-scan 可以参考注释
    Scans the classpath for annotated components that will be auto-registered as
    Spring beans. By default, the Spring-provided @Component, @Repository,
    @Service, and @Controller stereotypes will be detected.
+ mvc:annotation-driven
    Configures the annotation-driven Spring MVC Controller programming model.
        Note that this tag works in Web MVC only, not in Portlet MVC!
####  integrate mybatis with spring
springconfig.xml 中 dataSource 不同  mybatis-spring 版本 实现类不同
+ 1 add the dependencies which are required by java spring + mybatis application
+ 2 Modify web.xml
    use org.springframework.web.servlet.DispatcherServlet
    set the init spring-config.xml 
+ 3 add  
<mvc:annotation-driven />  
<context:component-scan base-package="com.creasypita.learning" />
 to indicate that the application is mvc annoation dirven and base-package  for the context component scan
 
+ 4 the bean InternalResourceViewResolver of Spring to locate the jsp files
 <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
   <property name="prefix" value="/WEB-INF/jsp/" />
   <property name="suffix" value=".jsp" />
 </bean>  
 
+ 5 add mysql dirven datasource 
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://localhost:3306/huangyongsmartbookcodedemo" />
        <property name="username" value="huangyongsmartbookcodedemo" />
        <property name="password" value="root" />
    </bean>
+ 6 Include the bean for transaction manager for scoping/controlling the transactions, that takes the data source defined above as reference (dependent)
    <tx:annotation-driven transaction-manager="transactionManager" />
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>
+ 7 The MyBatis specific configurations
    6.1 include the bean for sqlSessionFactory which is the central configuration in a MyBatis application.
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
      <property name="dataSource" ref="dataSource" />
      <property name="typeAliasesPackage" value="com.creasypita.learning.model"/>
      <property name="mapperLocations" value="classpath*:com/creasypita/learning/mappers/*.xml" />
    </bean>
    
    the mapperLocations property will not used for the mapper with scan by mybatis:scan node
+ 8 Include the bean for sqlSession
  <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <constructor-arg index="0" ref="sqlSessionFactory" />
  </bean>
    
+ 9 mybatis scan
  <mybatis:scan base-package="com.creasypita.learning.mappers"/>  
#### mapper 采用 interface + xml 形式来配置
+ 1 scan mapperLocations of xml file 
     <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <property name="dataSource" ref="dataSource" />
          <property name="typeAliasesPackage" value="com.creasypita.learning.model"/>
          <property name="mapperLocations" value="classpath*:mybatis/*.xml" />
      </bean>
          
      注释
        typeAliasesPackage  用于确定mybatis sql statement 中使用到的 type,parametertype ...
        他们的名称需要一致
        比如 mybatisStudent.xml 中的配置 type="Student" 与 class com.creasypita.learning.model.Student
        
        如果 不一致会提示 如一下错误：  java.lang.ClassNotFoundException: Cannot find class: Student
+ 2  scan mapper base package  of interface file
    <mybatis:scan base-package="com.creasypita.learning.mappers"/>