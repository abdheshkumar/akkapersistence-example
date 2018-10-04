# akka persistence with cluster sharding example
Steps to run this example:

Step-1: Start cassandra docker(src/main/resources/cassandra.yml) using docker-compose  
**docker-compose -f src/main/resources/cassandra.yml up** 

Step-2: Start AppOne which is seed node running on port 2551  
**sbt "runMain app.AppOne"**   
This node will be up successfully and will start /sharding/CounterOneEntityCoordinator coordinator. you can see one entry in akka.messages cassandra table.

Step-3: Start another node on port 2552 which will connect to seed node running on port 2551.  
**sbt "runMain app.AppTwo"**   
This node will join seed node successfully and will be up
