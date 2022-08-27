package co.marcuss.acct.query.api.queries;

import co.marcuss.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FindAccountByIdQuery extends BaseQuery {

    private final String id;
}
