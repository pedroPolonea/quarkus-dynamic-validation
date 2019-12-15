package br.com.config.error;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException>  {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        final ErrorDetails response = ErrorDetails.builder()
                .title("Erro ao validar payload")
                .dateTime(LocalDateTime.now().toString())
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .errorMessage(getMessages(e))
                .build();


        e.getConstraintViolations().forEach(constraintViolation -> {
        });

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }

    private Set<String> getMessages(ConstraintViolationException e){
        return e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
    }
}
