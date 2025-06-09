package salesland.demo.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salesland.demo.presentation.dto.LeadRequestDto;
import salesland.demo.presentation.dto.LeadResponseDto;
import salesland.demo.service.interfaces.LeadService;

@RestController
@RequestMapping("/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    @PostMapping
    public ResponseEntity<LeadResponseDto> registrarLeadLocal(@Valid @RequestBody LeadRequestDto dto) {
        LeadResponseDto response = leadService.registrarLead(dto, false);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api")
    public ResponseEntity<LeadResponseDto> registrarLeadApi(@Valid @RequestBody LeadRequestDto dto) {
        // NO pongas ResponseEntity.ok(...), deja que el handler gestione el error.
        LeadResponseDto respuesta = leadService.registrarLead(dto, true);
        return ResponseEntity.ok(respuesta); // Solo llega aquí si es OK
    }
}

