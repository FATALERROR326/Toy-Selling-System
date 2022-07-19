# Introduction
This is a course project of CS677 of UMass Amherst. The main idea of this project is to build a distributed system which has the features such as multithreading and cross platform communicating. The overall goal of the system is to sell toys in multiple threads without conflicts so that customers can always get what they have ordered.
# System Design
## Part 1. Rpc version
For part 1, we have developed the system based on thread pool written from scratch. The system consists of two ends: Server end and Client end. Within the server end, there are thread pool, request handler, blocking queue for quests and the stock structure stored in memory, as our main structure. We adopt this structure to implement our system simply because this is how it works in the Java's built-in thread pool.

The Stock is constructed in a concurrent HashMap and a HashMap which are each for storage of stock number and toy price. The two parts are separated because the action of query and buy can be done independently. Also note that the price of a stuffed toy is remaining constant, so it is not considered to be stored at a concurrent one. The stock supplies the query and buy method inside the class which can be called thread safely, though in part 1 there is no write operation that would cause a thread unsafe status.

The handler implements the runnable interface, which contains the core processing function of the server, which is to query the stock and return the price or 0 or -1 as required. This handler is created when new socket comes in, storing information for that socket and getting queued.

The MyBlockingQueue class is what queues them. The MyThreadPool has a blocking queue that waits execution, and sends it to each thread when initialization, which means different threads are going to operate on the same blocking queue to retrieve the newest task when the work on its hands is finished. As a result, the blocking queue must be thread safe. Here we applied a classical producer-consumer problem solution to it: when there’s no item in the queue, add lock on take(); when the queue is full, add lock on put(); otherwise, you can either take or put. The operator is also required to hold a lock to get access to the queue!

<img width="1056" alt="截屏2022-03-10 下午10 23 07" src="https://user-images.githubusercontent.com/59378806/157796131-6566bb68-8b67-4fe7-8fbd-4a056d19ede8.png">

The MyThreadPool is easy to explain now. Since it is a static number thread pool, it will start all threads in a row upon initialization, with each thread activated for retrieving new tasks to work on till the whole server to shut down.
The overall structure is depicted on the graph above.
## Part 2. gRpc version
Part 2 is built on the gRPC, so most multithreading work are done by it. Here we defined two services in the protocol buffer, which are the query and buy. They are implemented in a similar way of operating on the stock discussed in part 1. To make sure the stock invoked by the gRPC method is the same each time, here we applied a double check singleton design pattern to it.
## Examination.
We applied JUnit and tempus-fugit to concurrently test both parts. The test code is also included in our GitHub repo.

# How to run our program
Unfamiliarity about maven project could cause the project not easy to get worked. Dont't worry, a little bit more effort could make your day:).
## Step1. Get the source
---
The every first step is to get the source from this repository. We organize our system on Maven, so make sure the repository has the pom file, if not, please contact us right away. It also requires you to have the Maven installed on your test machine. For Edlab Linux OS, you can use 
```sudo apt install maven``` to install it and use ```maven -version``` to check if it is in the right place. Essiential Java environment on your device is required since this is a Java project.
## Step2. Make it works
---
Firstly, if you have access to a GUI IDE like Intellij IDEA, here are the steps you should go:
1. Open the repository, set the JDK to 1.8 or higher;
2. Import it as a Maven project if your IDE notifies. If not, right click the repository directory ```lab1-spring22-rpc-letsgo105``` -> add framework support -> choose maven -> you are done. If everything goes smooth, you can see the IDE is analysing indexes and dependensies in the under bar.
3. Mark the ```src/part1/java``` and ```src/part2/java``` as source directory, src/test/java as test source directory.
4. Run ```src/part1/java/com/company/Server/Server.java``` and ```src/part2/java/net/gRPCServer/GrpcServer.java``` by clicking green arrows in the main method.
5. Run the test cases in ```src/test/java```, and feel free to modify the test cases to see more results.

If you are using non-graphical OS, here are the steps for you:
1. Go to the repository directory ```lab1-spring22-rpc-letsgo105```.
2. Execute command ```mvn clean compile```.
3. Go to the repository ```target/classes/```, then run ```java com.company.Server.Server &```. If you are doing right, you can witness output from command line: ``` Server started on port 6066 with maximum 3 threads.```.
4. Go to the repository directory again, then run ```mvn package```. Please ignore the errors generated by automatic testing. We will test it rightly when both two servers are started.
5. Go to the ```target/``` directory, execute ```java -jar lab1-spring22-rpc-letsgo105-1.0-SNAPSHOT-jar-with-dependencies.jar &``` and you can see the output: ```Server started on 7077 with maximum thread pool size of 3```.
6. If all the previous steps executed correctly, you can finally return to the ```lab1-spring22-rpc-letsgo105``` repository again type ```mvn test``` to see the test case result get successfully in a similar format as our output file shows!

The second approach might be confusing because we ourselves had a hard time in not only making the package but also simply compiling the class. If there would still be any problem, please contact us either by email or through github. Thank you for reading till now!
:smile:
 
