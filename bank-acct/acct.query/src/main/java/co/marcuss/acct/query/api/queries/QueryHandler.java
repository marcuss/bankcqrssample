package co.marcuss.acct.query.api.queries;

import co.marcuss.cqrs.core.domain.BaseEntity;

import java.util.List;

@SuppressWarnings("unused")
public interface QueryHandler {

    List<BaseEntity> handle(FindAllAccountsQuery query);
    List<BaseEntity> handle(FindAccountByIdQuery query);
    List<BaseEntity> handle(FindAccountByHolderQuery query);
    List<BaseEntity> handle(FindAccountWithBalanceQuery query);
}
