package app

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings, ShardRegion}
import com.typesafe.config.ConfigFactory


object AppOne extends App with ShardDetails {

  val system = ActorSystem("test-actor-system", ConfigFactory.load("app-one"))
  def proxy = ClusterSharding(system).startProxy(
    "CounterOneEntity",
    Some("CounterOne"),
    extractEntityId = extractEntityId,
    extractShardId = extractShardId)

  val counterRegion: ActorRef = ClusterSharding(system).start(
    typeName = "CounterOneEntity",
    entityProps = Counter.props(proxy),
    settings = ClusterShardingSettings(system).withRole("CounterOne"),
    extractEntityId = extractEntityId,
    extractShardId = extractShardId)

  counterRegion ! Get(123)


  println(proxy)
}
