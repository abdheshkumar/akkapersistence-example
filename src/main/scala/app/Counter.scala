package app

import akka.actor.Actor.Receive
import akka.actor.{ActorRef, Props, ReceiveTimeout}
import akka.cluster.sharding.ShardRegion.Passivate
import akka.persistence.PersistentActor

import scala.concurrent.duration._
case class Increment(id: Long)
case object Decrement
final case class Get(counterId: Long)
final case class EntityEnvelope(id: Long, payload: Any)

case object Stop
final case class CounterChanged(delta: Int)

class Counter(ac: ActorRef) extends PersistentActor {
  import akka.cluster.sharding.ShardRegion.Passivate

  context.setReceiveTimeout(120.seconds)

  // self.path.name is the entity identifier (utf-8 URL-encoded)
  override def persistenceId: String = "Counter-" + self.path.name

  var count = 0

  def updateState(event: CounterChanged): Unit =
    count += event.delta

  override def receiveRecover: Receive = {
    case evt: CounterChanged ⇒ updateState(evt)
  }

  override def receiveCommand: Receive = {
    case Increment(id)      ⇒ println(s"Received Increment here - ${id} with persistence id ${persistenceId}")
      persist(CounterChanged(+1))(updateState)
    case Decrement      ⇒ persist(CounterChanged(-1))(updateState)
    case Get(id)         ⇒ sender() ! count; ac ! Increment(id)
      ;println(s"Received Get command with id ${id}  with persistence id ${persistenceId}")
    case ReceiveTimeout ⇒ context.parent ! Passivate(stopMessage = Stop)
    case Stop           ⇒ context.stop(self)
  }
}

object Counter {
  def props(ac: ActorRef): Props = Props(new Counter(ac))
}

