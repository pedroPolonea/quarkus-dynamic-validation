package br.com.domain;

import br.com.annotation.OrderValid;
import br.com.annotation.RequiredAttributeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@OrderValid
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

    private Long id;

    private String statusCurrent;

    @RequiredAttributeStatus(statuses={OrderStatus.CANCELED,OrderStatus.OPEN})
    private String clerk;

    @RequiredAttributeStatus(statuses = {OrderStatus.OPEN})
    private String openDate;

    private String updateDate;

    private String client;

    public boolean isStatusCurrent(){
        return statusCurrent != null;
    }
}
