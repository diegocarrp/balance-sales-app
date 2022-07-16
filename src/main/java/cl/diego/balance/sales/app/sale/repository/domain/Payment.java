package cl.diego.balance.sales.app.sale.repository.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "payment" )
@ToString
public class Payment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long       id;
    private BigDecimal amount;
    private Long       saleId;
    private Long       paymentMethodId;

}
