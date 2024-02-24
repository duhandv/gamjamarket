package project.duhan.gamjamarket.common.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Money other)) {
            return false;
        }

        return Objects.equals(amount.doubleValue(), other.amount.doubleValue());
    }

    public int hashCode() {
        return Objects.hashCode(amount);
    }

}
