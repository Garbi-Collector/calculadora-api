package gabri.dev.javaspringcompose.controllers;

import gabri.dev.javaspringcompose.dtos.AutoresRequestDto;
import gabri.dev.javaspringcompose.dtos.AutoresResponseDto;
import gabri.dev.javaspringcompose.models.AutoresModel;
import gabri.dev.javaspringcompose.services.AutoresService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador REST para la gestión de autores.
 */
@RestController
@RequestMapping("/autores")
public class AutoresController {

    /**
     * Servicio de autores.
     */
    private final AutoresService autoresService;

    /**
     * ModelMapper para convertir entre DTOs y modelos.
     */
    private final ModelMapper modelMapper;

    /**
     * Constructor del controlador.
     * @param autoresService servicio de autores.
     * @param modelMapper mapper por defecto.
     */
    public AutoresController(AutoresService autoresService,
                             @Qualifier("modelMapper") ModelMapper modelMapper) {
        this.autoresService = autoresService;
        this.modelMapper = modelMapper;
    }

    /**
     * Lista todos los autores.
     * @return la lista de autores.
     */
    @GetMapping
    public List<AutoresResponseDto> findAll() {

        List<AutoresModel> autoresModel = autoresService.findAll();

        List<AutoresResponseDto> autoresDto = new ArrayList<>();

        for(AutoresModel autoresModelfe : autoresModel) {
            AutoresResponseDto autordto = new AutoresResponseDto();
            autordto.setId(autoresModelfe.getId());
            autordto.setNombre(autoresModelfe.getNombre());
            autordto.setNacionalidad(autoresModelfe.getApellido());
            autordto.setNacionalidad(autoresModelfe.getNacionalidad());
            autoresDto.add(autordto);
        }
        return autoresDto;
    }

    @PostMapping
    public AutoresResponseDto save(
            @RequestBody AutoresRequestDto autoresDto
    ) {
        //crear el modelo de autores en base al dto que nos envian
        AutoresModel autormodel = new AutoresModel();
        autormodel.setNombre(autoresDto.getNombre());
        autormodel.setApellido(autoresDto.getApellido());
        autormodel.setNacionalidad(autoresDto.getNacionalidad());

        //envia el modelo mapeado al metodo create del servicio
        AutoresModel autorSaved = autoresService.create(autormodel);

        AutoresResponseDto autordto = new AutoresResponseDto();
        autordto.setId(autorSaved.getId());
        autordto.setNombre(autorSaved.getNombre());
        autordto.setApellido(autorSaved.getApellido());
        autordto.setNacionalidad(autorSaved.getNacionalidad());
        return autordto;

    }

}
