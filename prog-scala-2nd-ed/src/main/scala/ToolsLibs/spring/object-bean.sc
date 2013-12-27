// src/main/scala/ToolsLibs/spring/object-bean.sc

import toolslibs.spring._
import org.springframework.context.support._

val context = new ClassPathXmlApplicationContext("spring/scala-spring.xml");

val bean = context.getBean("factoryUsingBean").asInstanceOf[FactoryUsingBean] 
println("Factory Name: " + bean.factory.nameOfFactory)

val obj  = bean.factory.make("Dean Wampler")
println("Object: " + obj)
