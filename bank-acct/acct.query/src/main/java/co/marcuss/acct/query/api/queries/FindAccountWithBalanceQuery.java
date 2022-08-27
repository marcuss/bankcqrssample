package co.marcuss.acct.query.api.queries;

import co.marcuss.acct.query.api.dto.EqualityType;
import co.marcuss.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FindAccountWithBalanceQuery extends BaseQuery {

    private EqualityType equalityType;

    private BigDecimal balance;
}
