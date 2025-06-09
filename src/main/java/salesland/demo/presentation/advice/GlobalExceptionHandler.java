package salesland.demo.presentation.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import salesland.demo.presentation.dto.ErrorResponseDto;
import salesland.demo.service.exception.ApiStatusException;
import salesland.demo.service.exception.LeadFormatException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja errores generales no controlados
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponseDto> handleAllExceptions(Exception ex) {
//        log.error("Unhandled Exception: ", ex);
//        ErrorResponseDto error = ErrorResponseDto.builder()
//                .message("Internal server error")
//                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
//                .timestamp(LocalDateTime.now())
//                .build();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }

    // Maneja errores personalizados de formato
    @ExceptionHandler(LeadFormatException.class)
    public ResponseEntity<ErrorResponseDto> handleFormatException(LeadFormatException ex) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .code("400")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    // Maneja errores de validación de campos (anotaciones @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message("Errores de validación en los campos enviados")
                .code("400")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ApiStatusException.class)
    public ResponseEntity<Object> handleApiStatus(ApiStatusException ex) {
        Object body = ex.getBody();
        HttpStatus status = ex.getStatus();
        // Si body es String (raw JSON), lo devuelves como tal:
        if (body instanceof String) {
            return ResponseEntity.status(status)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body);
        }
        // Si es un DTO, Jackson lo serializa:
        return ResponseEntity.status(status).body(body);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
//        // Solo manejas tu excepción personalizada aquí
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(Map.of("error", ex.getMessage()));
//    }


    // Puedes agregar otros @ExceptionHandler según tus propias excepciones (por ejemplo, ResourceNotFoundException)
}

//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<ErrorResponseDto> handleConflict(Exception ex, WebRequest request) {
//        //log.error("Exception " + ex.getMessage(), ex);
//        ErrorResponseDto error = new ErrorResponseDto();
//        error.setMessage("Internal server error");
//        error.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
//        error.setTimestamp(LocalDateTime.now());
//        return ResponseEntity.internalServerError().body(error);
//    }
//
//    @ExceptionHandler(HttpClientErrorException.class)
//    protected ResponseEntity<ErrorResponseDto> handleConflict(HttpClientErrorException ex, WebRequest request)
//            throws JsonProcessingException {
//        //log.error("HttpClientErrorException " + ex.getMessage(), ex);
//        var json = convertToJson(ex.getMessage());
//        ErrorResponseDto error = new ErrorResponseDto();
//        error.setMessage(ex.getMessage());
//        error.setCode("400");
//        error.setTimestamp(LocalDateTime.now());
//        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.getRawStatusCode()));
//    }
//
//    private JsonNode convertToJson(String message) throws JsonProcessingException {
//        var messageTmp = message.split(": \"")[1];
//        messageTmp = messageTmp.substring(0, messageTmp.length() - 1);
//        return mapper.readTree(messageTmp);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        log.error("handleMethodArgumentNotValid " + ex.getMessage(), ex);
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach((error) -> {
//            String field = error.getField();
//            field = ((PropertyNamingStrategies.SnakeCaseStrategy) PropertyNamingStrategies.SNAKE_CASE).translate(field);
//            String message = error.getDefaultMessage();
//            errors.put(field, message);
//        });
//        ErrorResponseDto errorResponse = new ErrorResponseDto();
//        errorResponse.setMessage("Errores de validación en los campos enviados");
//        errorResponse.setCode("400");
//        errorResponse.setErrors(errors);
//        errorResponse.setTimestamp(LocalDateTime.now());
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//}


//
//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    // Se maneja Maneja errores generales no controlados
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponseDto> handleAllExceptions(Exception ex) {
//        log.error("Unhandled Exception: ", ex);
//        ErrorResponseDto error = ErrorResponseDto.builder()
//                .message("Internal server error")
//                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
//                .timestamp(LocalDateTime.now())
//                .build();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//
//    // Se maneja errores de formato personalizados en el mapper, por ejemplo de fechas, etc.
//    @ExceptionHandler(LeadFormatException.class)
//    public ResponseEntity<ErrorResponseDto> handleFormatException(LeadFormatException ex) {
//        ErrorResponseDto error = ErrorResponseDto.builder()
//                .message(ex.getMessage())
//                .code("400")
//                .timestamp(LocalDateTime.now())
//                .build();
//        return ResponseEntity.badRequest().body(error);
//    }
//
//    // Se manjea errores de validación de campos (anotaciones @Valid)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage())
//        );
//        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
//                .message("Errores de validación en los campos enviados")
//                .code("400")
//                .errors(errors)
//                .timestamp(LocalDateTime.now())
//                .build();
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//}
