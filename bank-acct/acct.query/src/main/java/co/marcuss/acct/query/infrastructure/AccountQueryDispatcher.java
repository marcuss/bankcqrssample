package co.marcuss.acct.query.infrastructure;

import co.marcuss.cqrs.core.domain.BaseEntity;
import co.marcuss.cqrs.core.infrastructure.QueryDispatcher;
import co.marcuss.cqrs.core.queries.BaseQuery;
import co.marcuss.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, Optional<List<QueryHandlerMethod>>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(
                type,
                (e) -> Optional.of(new LinkedList<>())
        );
        handlers.get().add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> sendQuery(BaseQuery query) {
        var handlers = routes.get(query.getClass())
                .orElseThrow(
                        () ->  new RuntimeException("No handler registered for the query: " + query.getClass().getSimpleName())
                );
        if (handlers.size() > 1) throw new RuntimeException("Can not handle a query with with multiple handlers");
        return handlers.get(0).handle(query);
    }
}
