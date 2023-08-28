# Reactive Programming
* It is a new programming paradigm
* It is going to be a new shift from the way that current development of applications
* It supports Asychronous and non blocking,Data Flow as event driven stream and Backpressure on dataStreams.
-----------------------------------------------------------------------------------------
* Traditional REST API - Thread per request model. 
* We can send n number of requests but there is a thread pool limit. Let's take an example in a real time application there is a thread pool limit of 20. That means it can handle only 20 concurrent requests at a time.
* If 21st request come that should wait until any of the thread becomes free. It is called Sychronous and Blocking method.
* To eliminate that Reactive Programming came into the picture.
-----------------------------------------------------------------------------------------
# Reactive Programming Specification
1. Publisher - It is a data source who will always publish an event.
2. Subsriber - It will subscribe/consume the events from publisher.
3. Subscription - It represents the unique relationship between Publisher and Subscriber.
4. Processor - It represents a processing stage which is a Subscriber and Publisher and MUST obey the contracts of both.
* These 4 are the pillars of the reactive programming.
* There are three libraries which support reactive programming.
1. Reactor(Recommended)
2. RxJava
3. JDK9 Flow Reactive Stream
* There are two reactor data types
1. Flux
2. Mono
-----------------------------------------------------------------------------------------
* Traditional API is a synchronous and blocking
* 