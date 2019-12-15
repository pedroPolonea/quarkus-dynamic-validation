package br.com.config.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String title;
    private int status;
    private String detail;
    private String dateTime;
    private Set<String> errorMessage;

}
