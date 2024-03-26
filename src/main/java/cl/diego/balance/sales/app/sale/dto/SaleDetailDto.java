package cl.diego.balance.sales.app.sale.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailDto {

    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private LocalDateTime           startDate;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private LocalDateTime           endDate;
    private List<SaleDetailItemDto> details;

}
