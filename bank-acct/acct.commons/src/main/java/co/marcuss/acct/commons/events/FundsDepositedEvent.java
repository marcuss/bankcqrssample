package co.marcuss.acct.commons.events;

import co.marcuss.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FundsDepositedEvent extends BaseEvent {
    private BigDecimal amount;
}
