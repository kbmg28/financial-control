package br.com.kbmg.financialcontrol.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Expense extends AbstractEntity {

    @NotNull
    private BigDecimal price;
    private Integer installment;

    @NotNull
    private LocalDate expirationDate;

    private OffsetDateTime paymentDate;

    private BigDecimal valuePaid;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Account account;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Purchase purchase;

    public Expense(Account account, Purchase purchase) {
        this.account = account;
        this.purchase = purchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Expense expense = (Expense) o;
        return getId() != null && Objects.equals(getId(), expense.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}