package salesland.demo.service.interfaces;

import salesland.demo.persistence.entity.LeadTemporalEntity;
import salesland.demo.presentation.dto.LeadDisociadoDto;
import salesland.demo.presentation.dto.LeadRequestDto;
import salesland.demo.presentation.dto.LeadResponseDto;

public interface LeadService {

    // Registrar un lead (desde /leads o /api/leads, distingue si es integración)
    LeadResponseDto registrarLead(LeadRequestDto dto, boolean usaApi);

    void procesarAltaLead(LeadTemporalEntity lead);

    void disociarLead(LeadTemporalEntity lead);

//    void reintentarCargasFallidas();

    LeadDisociadoDto verLeadDisociado(Long id);

    void registrarLogError(String origen, String error, String cuerpoJson);
}

