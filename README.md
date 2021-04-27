# Project to test issues between RedHat EAP 7.3  CDI and hapi-fhir

# Setup
- Need jdk8 on your path
- Place a copy of JBoss EAP 7.3.0 in a directory called jboss-eap-7.3 at the root of this project. 

# Running
The following scripts are provided to automate the build/test cycle

- .\build-and-deploy-73.bat -- runs maven clean and install and copies the war to the jboss instance
- .\startup-73.bat -- switches to the jboss bin directory and runs standalone.bat in debug mode

Then open the following url:
   <http://localhost:8080/hapi-fhir-eap7/DiagnosticReport?identifier=7000135&_include=DiagnosticReport.subject>

This will result in an exception like this:

```
10:36:51,782 ERROR [io.undertow.request] (default task-1) UT005023: Exception handling request to /hapi-fhir-eap7/DiagnosticReport: javax.servlet.ServletException: Failed to initialize FHIR Restful server
        at ca.uhn.fhir.rest.server.RestfulServer.init(RestfulServer.java:1333)
        at javax.servlet.GenericServlet.init(GenericServlet.java:180)
        at io.undertow.servlet.core.LifecyleInterceptorInvocation.proceed(LifecyleInterceptorInvocation.java:117)
        at org.wildfly.extension.undertow.security.RunAsLifecycleInterceptor.init(RunAsLifecycleInterceptor.java:78)
        at io.undertow.servlet.core.LifecyleInterceptorInvocation.proceed(LifecyleInterceptorInvocation.java:103)
        at io.undertow.servlet.core.ManagedServlet$DefaultInstanceStrategy.start(ManagedServlet.java:305)
        at io.undertow.servlet.core.ManagedServlet.forceInit(ManagedServlet.java:212)
        at io.undertow.servlet.handlers.ServletChain.forceInit(ServletChain.java:130)
        at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:63)
        at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)
        at org.wildfly.extension.undertow.security.SecurityContextAssociationHandler.handleRequest(SecurityContextAssociationHandler.java:78)
        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:68)
        at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:132)
        at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)
        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)
        at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)
        at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)
        at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)
        at io.undertow.security.handlers.NotificationReceiverHandler.handleRequest(NotificationReceiverHandler.java:50)
        at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)
        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at org.wildfly.extension.undertow.security.jacc.JACCContextIdHandler.handleRequest(JACCContextIdHandler.java:61)        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at org.wildfly.extension.undertow.deployment.GlobalRequestControllerHandler.handleRequest(GlobalRequestControllerHandler.java:68)
        at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
        at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:269)
        at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:78)
        at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:133)
        at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:130)
        at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)
        at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)
        at org.wildfly.extension.undertow.security.SecurityContextThreadSetupAction.lambda$create$0(SecurityContextThreadSetupAction.java:105)
        at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1504)
        at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1504)
        at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1504)
        at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1504)
        at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:249)
        at io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:78)
        at io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:99)
        at io.undertow.server.Connectors.executeRootHandler(Connectors.java:376)
        at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:830)
        at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
        at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:1982)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1486)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1348)
        at java.lang.Thread.run(Thread.java:748)
Caused by: java.lang.NullPointerException
        at java.lang.Class.isAssignableFrom(Native Method)
        at ca.uhn.fhir.rest.server.method.MethodUtil.getResourceParameters(MethodUtil.java:91)
        at ca.uhn.fhir.rest.server.method.BaseMethodBinding.<init>(BaseMethodBinding.java:74)
        at ca.uhn.fhir.rest.server.method.BaseResourceReturningMethodBinding.<init>(BaseResourceReturningMethodBinding.java:79)
        at ca.uhn.fhir.rest.server.method.SearchMethodBinding.<init>(SearchMethodBinding.java:77)
        at ca.uhn.fhir.rest.server.method.BaseMethodBinding.bindMethod(BaseMethodBinding.java:429)
        at ca.uhn.fhir.rest.server.RestfulServer.findResourceMethods(RestfulServer.java:435)
        at ca.uhn.fhir.rest.server.RestfulServer.findResourceMethods(RestfulServer.java:412)
        at ca.uhn.fhir.rest.server.RestfulServer.registerProviders(RestfulServer.java:1663)
        at ca.uhn.fhir.rest.server.RestfulServer.init(RestfulServer.java:1294)
        ... 47 more
```