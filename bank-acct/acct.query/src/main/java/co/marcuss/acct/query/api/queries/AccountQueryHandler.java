package co.marcuss.acct.query.api.queries;

import co.marcuss.acct.query.domain.AccountRepository;
import co.marcuss.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static co.marcuss.acct.query.api.dto.EqualityType.GreaterThan;

@Service
public class AccountQueryHandler implements QueryHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        return accountRepository.findAll()
                .stream().parallel()
                .map(BaseEntity.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {
        return accountRepository.findById(query.getId())
                .stream().parallel()
                .map(BaseEntity.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        return accountRepository.findByAccountHolder(query.getAccountHolder())
                .stream().parallel()
                .map(BaseEntity.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {
        Function<BigDecimal, List<BaseEntity>> filterFunc =
                GreaterThan == query.getEqualityType() ?
                        accountRepository::findByBalanceGreaterThan :
                        accountRepository::findByBalanceLessThan;
        return filterFunc.apply(query.getBalance())
                .stream().parallel()
                .map(BaseEntity.class::cast)
                .collect(Collectors.toList());
    }
}
