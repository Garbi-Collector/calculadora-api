package gabri.dev.javaspringcompose.controllers;

import gabri.dev.javaspringcompose.dtos.AutorRequestDto;
import gabri.dev.javaspringcompose.dtos.AutorResponseDto;
import gabri.dev.javaspringcompose.models.AutorModel;
import gabri.dev.javaspringcompose.services.AutorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador REST para la gestión de autores.
 */
@RestController
@RequestMapping("/autores")
public class AutorController {

    /**
     * Servicio de autores.
     */
    private final AutorService autoresService;

    /**
     * ModelMapper para convertir entre DTOs y modelos.
     */
    private final ModelMapper modelMapper;

    /**
     * Constructor del controlador.
     * @param autoresService servicio de autores.
     * @param modelMapper mapper por defecto.
     */
    public AutorController(AutorService autoresService,
                           @Qualifier("modelMapper") ModelMapper modelMapper) {
        this.autoresService = autoresService;
        this.modelMapper = modelMapper;
    }


    /**
     * Lista todos los autores.
     * @return la lista de autores.
     */
    @GetMapping
    public AutorResponseDto findById(@RequestParam Long id) {
        return modelToResponse(autoresService.findById(id));
    }

    @PostMapping
    public AutorResponseDto save(
            @RequestBody AutorRequestDto autoresDto
    ) {
        //crear el modelo de autores en base al dto que nos envian
        AutorModel autormodel = new AutorModel();
        autormodel.setNombre(autoresDto.getNombre());
        autormodel.setApellido(autoresDto.getApellido());
        autormodel.setNacionalidad(autoresDto.getNacionalidad());

        //envia el modelo mapeado al metodo create del servicio
        AutorModel autorSaved = autoresService.create(autormodel);

        AutorResponseDto autordto = new AutorResponseDto();
        autordto.setId(autorSaved.getId());
        autordto.setNombre(autorSaved.getNombre());
        autordto.setApellido(autorSaved.getApellido());
        autordto.setNacionalidad(autorSaved.getNacionalidad());
        return autordto;

    }


    /**
     * METODOS AUXILIARES
     */

    private AutorModel requestToModel(AutorRequestDto autorDto) {
        AutorModel autorModel = new AutorModel();
        autorModel.setNombre(autorDto.getNombre());
        autorModel.setApellido(autorDto.getApellido());
        autorModel.setNacionalidad(autorDto.getNacionalidad());
        return autorModel;
    }
    private AutorModel responseToModel(AutorResponseDto autorDto) {
        AutorModel autorModel = new AutorModel();
        autorModel.setId(autorDto.getId());
        autorModel.setNombre(autorDto.getNombre());
        autorModel.setApellido(autorDto.getApellido());
        autorModel.setNacionalidad(autorDto.getNacionalidad());
        return autorModel;
    }

    private AutorRequestDto modelToRequest(AutorModel autorDto) {
        AutorRequestDto autorRequest = new AutorRequestDto();
        autorRequest.setNombre(autorDto.getNombre());
        autorRequest.setApellido(autorDto.getApellido());
        autorRequest.setNacionalidad(autorDto.getNacionalidad());

        return autorRequest;
    }
    private AutorResponseDto modelToResponse(AutorModel autorDto) {
        AutorResponseDto autorResponse = new AutorResponseDto();
        autorResponse.setId(autorDto.getId());
        autorResponse.setNombre(autorDto.getNombre());
        autorResponse.setApellido(autorDto.getApellido());
        autorResponse.setNacionalidad(autorDto.getNacionalidad());

        return autorResponse;
    }


}
