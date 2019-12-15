package br.com.resources;

import br.com.domain.OrderVO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdersResources {

    @POST
    public OrderVO addOrder(@Valid final OrderVO orderVO) {
        log.info("M=clerk orderVO={}", orderVO);

        return orderVO;
    }
}
