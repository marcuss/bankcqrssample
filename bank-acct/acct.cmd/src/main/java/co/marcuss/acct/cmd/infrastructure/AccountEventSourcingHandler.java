package co.marcuss.acct.cmd.infrastructure;

import co.marcuss.acct.cmd.domain.AccountAggregate;
import co.marcuss.cqrs.core.domain.AggregateRoot;
import co.marcuss.cqrs.core.events.BaseEvent;
import co.marcuss.cqrs.core.handlers.EventSourcingHandler;
import co.marcuss.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    private final EventStore eventStore;

    @Autowired
    public AccountEventSourcingHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.save(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new AccountAggregate();
        List<BaseEvent> events = eventStore.getEvents(id);
        if (!CollectionUtils.isEmpty(events)) {
            aggregate.replyEvents(events);
            //noinspection OptionalGetWithoutIsPresent
            aggregate.setVersion(
                    events.stream()
                            .map(BaseEvent::getVersion)
                            .max(Comparator.naturalOrder()).get()
            );
        }
        return aggregate;
    }
}
