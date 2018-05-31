# akkapersistence-example
Steps to run this example:

Step-1: Start cassandra docker(src/main/resources/cassandra.yml) using docker-compose  
**docker-compose -f src/main/resources/cassandra.yml up** 

Step-2: Start AppOne which is seed node running on port 2551  
**sbt "runMain app.AppOne"**   
This node will be up successfully and will start /sharding/CounterOneEntityCoordinator coordinator. you can see one entry in akka.messages cassandra table.

Step-3: Start another node on port 2552 which will connect to seed node running on port 2551.  
**sbt "runMain app.AppTwo"**   
This node will join seed node successfully and will be up. But it will not create any shard coordinator like node one did and there
will not be any entry for /sharding/CounterTwoEntityCoordinator in akka.messages table thats the issue.

On node one console, you can see below log messags:

[INFO] [05/31/2018 01:11:22.260] [test-actor-system-akka.actor.default-dispatcher-90] [akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] Message [akka.cluster.sharding.ShardCoordinator$Internal$Register] from Actor[akka.tcp://test-actor-system@127.0.0.1:2552/system/sharding/CounterTwoEntity#112629614] to Actor[akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] was not delivered. [2] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

[INFO] [05/31/2018 01:11:24.243] [test-actor-system-akka.actor.default-dispatcher-17] [akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] Message [akka.cluster.sharding.ShardCoordinator$Internal$Register] from Actor[akka.tcp://test-actor-system@127.0.0.1:2552/system/sharding/CounterTwoEntity#112629614] to Actor[akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] was not delivered. [3] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

[INFO] [05/31/2018 01:11:26.240] [test-actor-system-akka.actor.default-dispatcher-3] [akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] Message [akka.cluster.sharding.ShardCoordinator$Internal$Register] from Actor[akka.tcp://test-actor-system@127.0.0.1:2552/system/sharding/CounterTwoEntity#112629614] to Actor[akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] was not delivered. [4] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

[INFO] [05/31/2018 01:11:28.242] [test-actor-system-akka.actor.default-dispatcher-3] [akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] Message [akka.cluster.sharding.ShardCoordinator$Internal$Register] from Actor[akka.tcp://test-actor-system@127.0.0.1:2552/system/sharding/CounterTwoEntity#112629614] to Actor[akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] was not delivered. [5] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

[INFO] [05/31/2018 01:11:30.241] [test-actor-system-akka.actor.default-dispatcher-18] [akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] Message [akka.cluster.sharding.ShardCoordinator$Internal$Register] from Actor[akka.tcp://test-actor-system@127.0.0.1:2552/system/sharding/CounterTwoEntity#112629614] to Actor[akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] was not delivered. [6] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

[INFO] [05/31/2018 01:11:32.239] [test-actor-system-akka.actor.default-dispatcher-17] [akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] Message [akka.cluster.sharding.ShardCoordinator$Internal$Register] from Actor[akka.tcp://test-actor-system@127.0.0.1:2552/system/sharding/CounterTwoEntity#112629614] to Actor[akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] was not delivered. [7] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

[INFO] [05/31/2018 01:11:34.241] [test-actor-system-akka.actor.default-dispatcher-2] [akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] Message [akka.cluster.sharding.ShardCoordinator$Internal$Register] from Actor[akka.tcp://test-actor-system@127.0.0.1:2552/system/sharding/CounterTwoEntity#112629614] to Actor[akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] was not delivered. [8] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

[INFO] [05/31/2018 01:11:36.243] [test-actor-system-akka.actor.default-dispatcher-18] [akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] Message [akka.cluster.sharding.ShardCoordinator$Internal$Register] from Actor[akka.tcp://test-actor-system@127.0.0.1:2552/system/sharding/CounterTwoEntity#112629614] to Actor[akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] was not delivered. [9] dead letters encountered. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.

[INFO] [05/31/2018 01:11:38.242] [test-actor-system-akka.actor.default-dispatcher-2] [akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] Message [akka.cluster.sharding.ShardCoordinator$Internal$Register] from Actor[akka.tcp://test-actor-system@127.0.0.1:2552/system/sharding/CounterTwoEntity#112629614] to Actor[akka://test-actor-system/system/sharding/CounterTwoEntityCoordinator/singleton/coordinator] was not delivered. [10] dead letters encountered, no more dead letters will be logged. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
