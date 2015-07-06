##知识点

###Java程序运行机制
.java文件 （Java程序的文件，人能读懂）
    ——编译——》 
.class文件 （二进制文件，机器能识别）
    ——运行——》 （Java虚拟机JVM）
结果

——————————————————————————————————————

编译时环境：
程序源文件.java文件 -》Java编译器 -》 .class文件

运行时环境
程序的class文件 + Java API的class文件
-》Java虚拟机（类装载器--字节码--》执行引擎）
-》本地方法调用
-》主机操作系统

###编译和解释的区别


###JDK & JRE & JVM
三者的关系：
- JDK是Java的开发工具包（包含Java开发工具 + 运行环境JRE），不仅提供了Java程序运行所需的JRE，还提供了一系列的编译、运行等工具，如javac、java、javaw
- JRE是Java程序的运行环境，包含Java虚拟机JVM + Java类库的class文件

JDK提供的运行环境和工具都需要进行环境变量的配置以后，才能使用。

JDK目录结构
|_bin   Java开发工具，包括编译器、调试器、文档化工具、归档工具
|_db Java DB数据库，用于学习和测试，不能用于开发
|_demo JDK自带的一些演示程序
|_include 用于调用哪个本地（底层平台）方法的C++头文件
|_jre Java运行时环境，包括JVM、类库和其他资源文件，此JRE仅供JDK使用
|_lib 类库和所需支持性文件
|_src.zip JDK类库的源代码

###JAVA_HOME
因为bin文件夹里包含了所有的工具，要执行java程序，必须要跑到bin文件夹所在的路径下，会特别的麻烦，所以我们需要配置环境，在任何目录下都可以执行java程序。
主要配置三个环境变量：

- JAVA_HOME:去哪里找JDK安装的路径，如果是默认安装，可配置（C:\Java\jdk1.7.0）（假设我的jdk是1.7.0版本）
- PATH:去哪里找编译或运行等工具(必须设置)，配置（%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;）
- CLASSPATH:去哪里找需要运行的class文件(java5开始可不设置)，配置（.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar（注意最前面有一点））

###javac & java 命令
javac编译源文件
java运行Java程序

####如何指定编译目录
如果java的类指定了包名或想重新设置编译文件的路径，则可以使用“-d”参数，在编译的时候自动生成与包相对应的目录层次。
-d参数是很好的把源程序和目标代码分离的参数。-d制定的是目标代码的根目录，源文件的包的结构将以目录形式反映到根目录上。

```

    java -d . HelloWorld.java
```     

####-sourcepath
指定用以查找类或接口定义的源代码路径。

####如何为Java程序动态的指定类搜索路径
默认情况下，Java程序在编译和运行的时候，会通过JRE下的rt.jar文件和 CLASSPATH 环境变量所指定的路径进行类的搜索和加载。
如果没有指定 CLASSPATH 又没有指定 -classpath 命令行选项，则用户路径默认为当前目录。

-cp 或 -classpath 用来设置用户类路径，动态提供类加载路径，它将覆盖 CLASSPATH 环境变量中的用户类路径。

```

    javac -cp d:\work\java\log4j.jar HelloWorld.java     
    java -cp d:\work\java\log4j.jar HelloWorld
    javac -cp 路径或路径变量 源文件
    java -cp 路径或路径变量 类文件
```

###CLASSPATH
CLASSPATH 环境变量保存的是一些目录和jar文件的地址，这些路径是为Java程序在编译和运行的时候搜索类而用的。
CLASSPATH 是在编译Java源码和运行程序时使用的，为Java程序所依赖的接口、类等指定一个搜索路径。
写法与Path变量类似，每个路径用分号进行分开，如果是一个jar文件，直接写明jar文件的绝对路径，如：

```

    .;c:\jar\logj4.jar;d:\work\java
```
以上 CLASSPATH 环境变量指定了3个搜索类的路径：当前目录、logj4.jar文件的路径和d:\work\java文件下的所有类

###如何打包成jar

###如何运行含有jar文件的程序

###IDE里面帮我们做了哪些事情


###class loader 类加载器
类加载器负责加载 Java 类的字节代码到 Java 虚拟机中。一般来说，Java 应用的开发人员不需要直接同类加载器进行交互。
Java 虚拟机默认的行为就已经足够满足大多数情况的需求了。不过如果遇到了需要与类加载器进行交互的情况，而对类加载器的机制又不是很了解的话，
就很容易花大量的时间去调试 ClassNotFoundException和 NoClassDefFoundError等异常。

顾名思义，类加载器（class loader）用来加载 Java 类到 Java 虚拟机中。
一般来说，Java 虚拟机使用 Java 类的方式如下：Java 源程序（.java 文件）在经过 Java 编译器编译之后就被转换成 Java 字节代码（.class 文件）。
类加载器负责读取 Java 字节代码，并转换成 java.lang.Class类的一个实例。每个这样的实例用来表示一个 Java 类。
通过此实例的 newInstance()方法就可以创建出该类的一个对象。实际的情况可能更加复杂，比如 Java 字节代码可能是通过工具动态生成的，也可能是通过网络下载的。
基本上所有的类加载器都是 java.lang.ClassLoader类的一个实例。下面详细介绍这个 Java 类。

java.lang.ClassLoader类介绍
java.lang.ClassLoader类的基本职责就是根据一个指定的类的名称，找到或者生成其对应的字节代码，然后从这些字节代码中定义出一个 Java 类，即 java.lang.Class类的一个实例。
除此之外，ClassLoader还负责加载 Java 应用所需的资源，如图像文件和配置文件等。不过本文只讨论其加载类的功能。为了完成加载类的这个职责，ClassLoader提供了一系列的方法。

类加载器的树状组织结构
Java 中的类加载器大致可以分成两类，一类是系统提供的，另外一类则是由 Java 应用开发人员编写的。系统提供的类加载器主要有下面三个：
引导类加载器（bootstrap class loader）：它用来加载 Java 的核心库，是用原生代码来实现的，并不继承自 java.lang.ClassLoader。
扩展类加载器（extensions class loader）：它用来加载 Java 的扩展库。Java 虚拟机的实现会提供一个扩展库目录。该类加载器在此目录里面查找并加载 Java 类。
系统类加载器（system class loader）：它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。一般来说，Java 应用的类都是由它来完成加载的。可以通过 ClassLoader.getSystemClassLoader()来获取它。

 Java 虚拟机是如何判定两个 Java 类是相同的。Java 虚拟机不仅要看类的全名是否相同，还要看加载此类的类加载器是否一样。
 只有两者都相同的情况，才认为两个类是相同的。即便是同样的字节代码，被不同的类加载器加载之后所得到的类，也是不同的。
 比如一个 Java 类 com.example.Sample，编译之后生成了字节代码文件 Sample.class。
 两个不同的类加载器 ClassLoaderA和 ClassLoaderB分别读取了这个 Sample.class文件，并定义出两个 java.lang.Class类的实例来表示这个类。
 这两个实例是不相同的。对于 Java 虚拟机来说，它们是不同的类。
 
 了解了这一点之后，就可以理解代理模式的设计动机了。代理模式是为了保证 Java 核心库的类型安全。
 所有 Java 应用都至少需要引用 java.lang.Object类，也就是说在运行的时候，java.lang.Object这个类需要被加载到 Java 虚拟机中。
 如果这个加载过程由 Java 应用自己的类加载器来完成的话，很可能就存在多个版本的 java.lang.Object类，而且这些类之间是不兼容的。
 通过代理模式，对于 Java 核心库的类的加载工作由引导类加载器来统一完成，保证了 Java 应用所使用的都是同一个版本的 Java 核心库的类，是互相兼容的。