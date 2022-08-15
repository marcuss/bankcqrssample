package co.marcuss.acct.query.domain;

import co.marcuss.cqrs.core.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<BankAccount, String> {

    Optional<BankAccount> findByAccountHolder(String accountHolder);

    List<BaseEntity> findByBalanceGreaterThan(BigDecimal amount);

    List<BaseEntity> findByBalanceLessThan(BigDecimal amount);
}
