package salesland.demo.service.interfaces;

import salesland.demo.presentation.dto.CierreOportunidadRequestDto;
import salesland.demo.presentation.dto.CierreOportunidadResponseDto;

public interface CierreOportunidadService {

    CierreOportunidadResponseDto cerrarOportunidad(CierreOportunidadRequestDto requestDto);

    void registrarLogErrorCierre(String origen, String error, String cuerpoJson);

}
