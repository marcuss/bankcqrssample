package co.marcuss.acct.cmd.infrastructure;

import co.marcuss.acct.cmd.domain.AccountAggregate;
import co.marcuss.acct.cmd.domain.EventStoreRepository;
import co.marcuss.cqrs.core.events.BaseEvent;
import co.marcuss.cqrs.core.events.EventModel;
import co.marcuss.cqrs.core.exceptions.AggregateNotFoundException;
import co.marcuss.cqrs.core.exceptions.ConcurrencyException;
import co.marcuss.cqrs.core.infrastructure.EventStore;
import co.marcuss.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AccountEventStore implements EventStore {
    @Autowired
    private EventProducer eventProducer;
    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Override
    public void save(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        List<EventModel> eventStream = getFullEventList(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        AtomicInteger version = new AtomicInteger(expectedVersion);
        events.forEach(
                e -> {
                    version.getAndIncrement();
                    e.setVersion(version.get());
                    var eventModel = EventModel.builder()
                            .aggregateIdentifier(aggregateId)
                            .timeStamp(LocalDateTime.now())
                            .aggregateTye(AccountAggregate.class.getName())
                            .version(version.get())
                            .eventType(e.getClass().getTypeName())
                            .eventData(e)
                            .build();
                    var persistedEvent = eventStoreRepository.save(eventModel);
                    if (!persistedEvent.getId().isEmpty()) {
                        eventProducer.produce(e.getClass().getSimpleName(), e);
                    }
                }
        );


    }

    private List<EventModel> getFullEventList(String aggregateId) {
        return eventStoreRepository.findByAggregateIdentifier(aggregateId);
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        List<EventModel> events = getFullEventList(aggregateId);
        if (CollectionUtils.isEmpty(events)) {
            throw new AggregateNotFoundException("Incorrect acct id provided");
        }
        return events.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }
}
