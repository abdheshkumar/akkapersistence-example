package app

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings, ShardRegion}
import com.typesafe.config.ConfigFactory


object AppOne extends App with ShardDetails {

  val system = ActorSystem("test-actor-system", ConfigFactory.load("app-one"))
  val counterRegion: ActorRef = ClusterSharding(system).start(
    typeName = "CounterOneEntity",
    entityProps = Props[Counter],
    settings = ClusterShardingSettings(system),
    extractEntityId = extractEntityId,
    extractShardId = extractShardId)
}
