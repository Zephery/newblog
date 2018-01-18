



```xml
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>${spring-framework.version}</version>
        </dependency>
```




```html
Caused by: org.springframework.beans.factory.BeanDefinitionStoreException: Invalid bean definition with name 'customKeyGenerator' defined in com.myblog.cache.CustomKeyGenerator: factory-bean reference points back to the same bean definition
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.getTypeForFactoryMethod(AbstractAutowireCapableBeanFactory.java:675)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.determineTargetType(AbstractAutowireCapableBeanFactory.java:640)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.predictBeanType(AbstractAutowireCapableBeanFactory.java:609)
	at org.springframework.beans.factory.support.AbstractBeanFactory.isFactoryBean(AbstractBeanFactory.java:1484)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doGetBeanNamesForType(DefaultListableBeanFactory.java:425)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanNamesForType(DefaultListableBeanFactory.java:395)
	at org.springframework.beans.factory.BeanFactoryUtils.beanNamesForTypeIncludingAncestors(BeanFactoryUtils.java:220)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.findAutowireCandidates(DefaultListableBeanFactory.java:1267)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveMultipleBeans(DefaultListableBeanFactory.java:1180)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1096)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1066)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredMethodElement.inject(AutowiredAnnotationBeanPostProcessor.java:659)
	... 69 common frames omitted
```

```html
18-Jan-2018 09:19:48.988 严重 [http-nio-9090-exec-2] org.apache.catalina.core.StandardWrapperValve.invoke Servlet.service() for servlet [springMVC] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.expression.spel.SpelEvaluationException: EL1008E: Property or field 'customKeyGenerator' cannot be found on object of type 'org.springframework.cache.interceptor.CacheExpressionRootObject' - maybe not public?] with root cause
 org.springframework.expression.spel.SpelEvaluationException: EL1008E: Property or field 'customKeyGenerator' cannot be found on object of type 'org.springframework.cache.interceptor.CacheExpressionRootObject' - maybe not public?
	at org.springframework.expression.spel.ast.PropertyOrFieldReference.readProperty(PropertyOrFieldReference.java:226)
```
解决办法：
注意单引号



参考:
1.[http://blog.csdn.net/lsgqjh/article/details/54381202](http://blog.csdn.net/lsgqjh/article/details/54381202)


2.[http://blog.csdn.net/a454832841/article/details/72674212](http://blog.csdn.net/a454832841/article/details/72674212)