#Java001

##关键词
###Java
- jdk
- jre
- jvm
- JAVA_HOME
- class loader 类加载器 
- classpath
- sourcepath
- 编译命令
- 运行命令
- 打包命令
- war包

###JavaEE
- servlet
- gradle
- mysql
- hibernate

##目标
在用 **命令行** 完成Service和Client程序的同时，掌握java程序运行的机制，这就包括与Java相关的关键词。
能够回答以下问题：

- JDK、JRE、JVM分别是什么，关系是什么
- 为什么要用JAVA_HOME
- 如何打包成jar文件
- class loader 类加载器是什么 
- classpath、sourcepath分别是什么
- WAR包里面有些什么，它应该遵循哪些规范，以及如何手动打WAR包然后部署到Tomcat上

##注意
使用 **命令行** 完成以下需求

###需求一：

- 有两个包
- 一个包是service，里面有Service.java，里面有一个service方法，打印Hello World
- 一个包是client，里面有Client.java，里面有main函数，调用service方法，打印出调用结果

####学生可能遇到的问题
在没有使用IDE时，学生会遇到的错误包括：Java本身的语法错误、包错误

####提问
- Java运行机制是什么
- 如何在命令行运行一个Java程序？
    * 编译，那编译和解释的区别是什么
    
    ```
        
        javac client/Client.java 
    ```  
     
    * 运行
    
    ```
    
        java client.Client
    ```        
- javac命令、java命令分别做了什么
    * -sourcepath
    * -classpath
- 为什么这两个命令可以完成任务
    * JAVA_HOME
    * jdk jre jvm关系
- Java程序中“包”的概念，和文件夹是一一对应的
- Java程序的入口函数，main函数的写法


###需求二：

- 在需求一的基础上，有另外一个service2，里面有一个service的方法，同样被client调用
- 把service2打包成jar，然后删除service2包，使用service2.jar运行出整个程序

####提问
- 如何打包成jar

```

    jar cf service2.jar service2
```

- 如何运行含有jar依赖的程序

```

    java -cp service2.jar:. client.Client
```

- 程序打包的时候一般会只打包class文件

- 我们在IDE里面是怎么运行的，那IDE帮我们做了哪些工作
    * 在Intellij里面找到sourcepath classpath等

###需求三：

- 下载tomcat 
- 在需求二的基础上把service打成jar包，然后将所有程序打成war包，放在tomcat下面执行访问
  
提示：放在tomcat下运行，需要自己写一个servlet

####提问
- 如何打包成war

```

    jar cf hello.war hello
```
- war包的结构是怎样的

```
    WEB-INF
    |_web.xml
    |_classes
        |_*.class
    |_lib
        |_*.jar
```
- tomcat如何运行war包
    * tomcat目录结构
    
```

    tomcat
    |_bin
    ...
    |_webapps
        |_yourwar.war
```
    * 启动tomcat
    
``
    
        ./bin/startup.sh
``