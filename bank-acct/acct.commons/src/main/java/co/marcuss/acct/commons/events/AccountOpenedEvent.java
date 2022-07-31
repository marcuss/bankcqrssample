package co.marcuss.acct.commons.events;

import co.marcuss.acct.commons.dto.AcctType;
import co.marcuss.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountOpenedEvent extends BaseEvent {
    private String accountHolder;
    private AcctType acctType;
    private LocalDate createdDate;
    private BigDecimal openingBalance;
}
