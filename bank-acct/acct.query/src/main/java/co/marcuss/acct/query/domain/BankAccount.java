package co.marcuss.acct.query.domain;

import co.marcuss.acct.commons.dto.AcctType;
import co.marcuss.cqrs.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount extends BaseEntity {
    @Id
    private String id;
    private String accountHolder;
    private LocalDate creationDate;
    private AcctType type;
    private BigDecimal balance;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
