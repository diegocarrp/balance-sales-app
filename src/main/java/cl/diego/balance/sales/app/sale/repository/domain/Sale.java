package cl.diego.balance.sales.app.sale.repository.domain;

import cl.diego.balance.sales.app.sale.dto.PaymentDto;
import cl.diego.balance.sales.app.sale.dto.SaleItemDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "sale" )
@ToString
public class Sale {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long              id;
    private Long              clientId;
    private Long              cashierId;
    private LocalDateTime     transactionDatetime;
    private List<SaleItemDto> items;
    private List<PaymentDto>  payments;

}
