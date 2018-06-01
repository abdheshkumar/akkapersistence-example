package app

import akka.cluster.sharding.ShardRegion

trait ShardDetails {
  val extractEntityId: ShardRegion.ExtractEntityId = {
    case EntityEnvelope(id, payload) ⇒ (id.toString, payload)
    case msg@Get(id) ⇒ (id.toString, msg)
    case msg@Increment(id) =>(id.toString,msg)
  }

  val numberOfShards = 100

  val extractShardId: ShardRegion.ExtractShardId = {
    case EntityEnvelope(id, _) ⇒ (id % numberOfShards).toString
    case Get(id) ⇒ (id % numberOfShards).toString
    case Increment(id)=>(id % numberOfShards).toString
    case ShardRegion.StartEntity(id) ⇒
      // StartEntity is used by remembering entities feature
      (id.toLong % numberOfShards).toString
  }
}
