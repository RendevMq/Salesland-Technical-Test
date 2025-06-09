package salesland.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                // Evita que se lancen excepciones en respuestas de error
                System.out.println("HTTP Status Code: " + response.getStatusCode());
                System.out.println("HTTP Status Text: " + response.getStatusText());
                System.out.println("Response Headers: " + response.getHeaders());
            }
        });
        return restTemplate;
    }
}