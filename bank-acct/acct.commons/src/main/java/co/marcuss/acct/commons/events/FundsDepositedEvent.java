package co.marcuss.acct.commons.events;

import co.marcuss.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public class FundsDepositedEvent extends BaseEvent {
    private BigDecimal amount;
}
