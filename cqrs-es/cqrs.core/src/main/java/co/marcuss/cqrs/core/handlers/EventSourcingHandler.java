package co.marcuss.cqrs.core.handlers;

import co.marcuss.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler <T> {

    void save(AggregateRoot aggregate);

    T getById(String id);

}
