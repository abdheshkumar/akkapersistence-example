package app

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings, ShardRegion}
import app.AppOne.system
import com.typesafe.config.ConfigFactory

object AppTwo extends App with ShardDetails {
  val system = ActorSystem("test-actor-system", ConfigFactory.load("app-two"))
  def proxy = ClusterSharding(system).startProxy(
    "CounterOneEntity",
    Some("CounterTwo"),
    extractEntityId = extractEntityId,
    extractShardId = extractShardId)

  val counterRegion: ActorRef = ClusterSharding(system).start(
    typeName = "CounterOneEntity",
    entityProps = Counter.props(proxy),
    settings = ClusterShardingSettings(system).withRole("CounterTwo"),
    extractEntityId = extractEntityId,
    extractShardId = extractShardId)

  /*val counterRegionOne: ActorRef = ClusterSharding(system).start(
    typeName = "CounterOneEntity",
    entityProps = Props[Counter],
    settings = ClusterShardingSettings(system).withRole("CounterOne"),
    extractEntityId = extractEntityId,
    extractShardId = extractShardId)*/
}
