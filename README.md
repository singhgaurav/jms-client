While at my previous client, I worked on a hedge fund project where I wrote Generic Java-JMS Trigger Client  
For this example, I attempted to recreate that here with clean and neat design. 


Write Java client to send scheduled (using autosys for example) JMS message to execute 
ReportRunner, SyncRunner, ReconciliationRunner at the consumer side. Runner requires specific 
parameters and parameter-value to process particular report, sync or reconcilliation. 

Input :
CLI Java Client will be passed in string arguments when set up on scheduler.
Client parses the argument and send JMS Map message as key-value. 


This client should will serve all the Runners with out any code change, 
with each runner specifying their command line flags in their specific json configuration file. 

Dependency: To execute and run the project, Java 7 is required. 

Objective : 
Objective here is to demonstrate programming, and design style (service oriented and injecting dependencies). 
Gluing together important frameworks and api's like Spring DI, JMS, Embedded Active MQ Broker, JUnit, Mockito, Maven, 
Logging with Logback and Slf4j etc attempt is the demonstrate a cohesive working setup with some practical usecase. 



NOTE: Also added some incomplete work I was working upon just also touch upon concurrency in the sample. 
