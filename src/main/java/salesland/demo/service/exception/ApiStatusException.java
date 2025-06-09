package salesland.demo.service.exception;

import org.springframework.http.HttpStatus;

public class ApiStatusException extends RuntimeException {
    private final HttpStatus status;
    private final Object body; // Puede ser LeadResponseDto o String

    public ApiStatusException(HttpStatus status, Object body) {
        super("API status error: " + status);
        this.status = status;
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Object getBody() {
        return body;
    }
}
