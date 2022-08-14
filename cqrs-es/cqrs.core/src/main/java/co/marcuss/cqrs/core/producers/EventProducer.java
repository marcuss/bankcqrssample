package co.marcuss.cqrs.core.producers;

import co.marcuss.cqrs.core.events.BaseEvent;

public interface EventProducer {

    void produce(String topic, BaseEvent event);
}
