package cl.diego.balance.sales.app.sale.repository.model;

import cl.diego.balance.sales.app.sale.dto.PaymentDto;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "payment" )
public class Payment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long          id;
    private BigDecimal    amount;
    @ToStringExclude
    @ManyToOne
    @JoinColumn( name = "sale_id" )
    private Sale          sale;
    @OneToOne
    @JoinColumn( name = "payment_method_id" )
    private PaymentMethod paymentMethod;

    public Payment( PaymentDto paymentDto ) {
        this.amount = paymentDto.getAmount();
    }

    @Override
    public String toString( ) {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
