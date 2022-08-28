package co.marcuss.acct.query.infrastructure.consumers;

import co.marcuss.acct.commons.events.AccountClosedEvent;
import co.marcuss.acct.commons.events.AccountOpenedEvent;
import co.marcuss.acct.commons.events.FundsDepositedEvent;
import co.marcuss.acct.commons.events.FundsWithdrawnEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

@SuppressWarnings("unused")
public interface EventConsumer {

    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);

    void consume(@Payload FundsDepositedEvent event, Acknowledgment ack);

    void consume(@Payload FundsWithdrawnEvent consumedEvent, Acknowledgment ack);

    void consume(@Payload AccountClosedEvent consumedEvent, Acknowledgment ack);

}
