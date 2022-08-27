package co.marcuss.acct.query.api.queries;

import co.marcuss.acct.query.domain.AccountRepository;
import co.marcuss.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler{

    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        return null;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {
        return null;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        return null;
    }

    @Override
    public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {
        return null;
    }
}
