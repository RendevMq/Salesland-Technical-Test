package salesland.demo.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salesland.demo.presentation.dto.CierreOportunidadRequestDto;
import salesland.demo.presentation.dto.CierreOportunidadResponseDto;
import salesland.demo.service.interfaces.CierreOportunidadService;

@RestController
@RequestMapping("/cierres")
@RequiredArgsConstructor
public class CierreOportunidadController {

    private final CierreOportunidadService cierreOportunidadService;

    @PostMapping
    public ResponseEntity<CierreOportunidadResponseDto> cerrarOportunidad(
            @Valid @RequestBody CierreOportunidadRequestDto request) {

        // Aquí SOLO pasas el DTO al service
        CierreOportunidadResponseDto response = cierreOportunidadService.cerrarOportunidad(request);
        return ResponseEntity.ok(response);
    }
}
