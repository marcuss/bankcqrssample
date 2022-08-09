package co.marcuss.cqrs.core.infrastructure;

import co.marcuss.cqrs.core.events.BaseEvent;

import java.util.List;

public interface EventStore {

    void save(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(String aggregateId);

}
