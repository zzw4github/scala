package learning.concurrent.programming;

import akka.actor.{ Terminated, Props, Actor}
import learning.concurrent.programming.pingpong.{Pingy, Pongy}

/**
 * Created by infosea on 2016-08-03.
 */
class GracefulPingy extends Actor {
    val pongy = context.actorOf(Props[Pongy], "pongy")
    context.watch(pongy)
    def receive = {
    case "Die, Pingy!" =>
            context.stop(pongy)
            case Terminated(`pongy`) =>
            context.stop(self)
}

}


