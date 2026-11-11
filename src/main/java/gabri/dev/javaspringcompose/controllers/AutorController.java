package gabri.dev.javaspringcompose.controllers;

import gabri.dev.javaspringcompose.dtos.AutorRequestDto;
import gabri.dev.javaspringcompose.dtos.AutorResponseDto;
import gabri.dev.javaspringcompose.models.AutorModel;
import gabri.dev.javaspringcompose.services.AutorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<AutorResponseDto> findAll() {

        List<AutorModel> autoresModel = autoresService.findAll();

        List<AutorResponseDto> autoresDto = new ArrayList<>();

        for(AutorModel autoresModelfe : autoresModel) {
            AutorResponseDto autordto = new AutorResponseDto();
            autordto.setId(autoresModelfe.getId());
            autordto.setNombre(autoresModelfe.getNombre());
            autordto.setNacionalidad(autoresModelfe.getApellido());
            autordto.setNacionalidad(autoresModelfe.getNacionalidad());
            autoresDto.add(autordto);
        }
        return autoresDto;
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

}
