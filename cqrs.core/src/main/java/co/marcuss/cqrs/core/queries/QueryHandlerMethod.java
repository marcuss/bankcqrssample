package co.marcuss.cqrs.core.queries;

import co.marcuss.cqrs.core.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery>  {

    List<BaseEntity> handle(T query);
}
