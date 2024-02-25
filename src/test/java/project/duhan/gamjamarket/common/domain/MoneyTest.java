package project.duhan.gamjamarket.common.domain;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoneyTest {

    @Test
    void moneyPlus() {
        Money oneThousandWon = Money.wons(1000);
        Money twoThousandWon = Money.wons(2000);

        Money actual = oneThousandWon.plus(twoThousandWon);

        then(actual).isEqualTo(Money.wons(3000));
    }

    @Test
    void moneyMinus() {
        Money twoThousandWon = Money.wons(2000);
        Money oneThousandWon = Money.wons(1000);

        Money actual = twoThousandWon.minus(oneThousandWon);

        then(actual).isEqualTo(Money.wons(1000));
    }

    @Test
    void moneyZero() {
        Money zeroWon = Money.ZERO;
        then(zeroWon.getAmount()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void moneyEquals() {
        Money amount1 = Money.wons(1000);
        Money amount2 = Money.wons(1000);
        assertTrue(amount1.equals(amount2) && amount2.equals(amount1));
        assertEquals(amount1.hashCode(), amount2.hashCode());
        assertEquals(amount1, amount1);
    }

    @Test
    void moneyNotEquals() {
        Money amount = Money.wons(1000);
        Object object = new Object();
        assertNotEquals(amount, object);
    }

}