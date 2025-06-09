package salesland.demo.service.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import salesland.demo.presentation.dto.LeadRequestDto;
import salesland.demo.presentation.dto.LeadResponseDto;

@Component
public class LeadClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.lead.api.url}")
    private String apiUrl;

    public ResponseEntity<LeadResponseDto> fromApi(LeadRequestDto request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LeadRequestDto> entity = new HttpEntity<>(request, headers);

        return restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                LeadResponseDto.class
        );
    }
}
