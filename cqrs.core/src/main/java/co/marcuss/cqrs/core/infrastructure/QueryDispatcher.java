package co.marcuss.cqrs.core.infrastructure;

import co.marcuss.cqrs.core.domain.BaseEntity;
import co.marcuss.cqrs.core.queries.BaseQuery;
import co.marcuss.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {

    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> sendQuery(BaseQuery query);
}
