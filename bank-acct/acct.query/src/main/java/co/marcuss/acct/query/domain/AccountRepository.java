package co.marcuss.acct.query.domain;

import co.marcuss.cqrs.core.domain.BaseEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface AccountRepository extends ListCrudRepository<BankAccount, String> {

    List<BaseEntity> findByAccountHolder(String accountHolder);

    List<BaseEntity> findByBalanceGreaterThan(BigDecimal amount);

    List<BaseEntity> findByBalanceLessThan(BigDecimal amount);
}
