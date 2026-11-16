package gabri.dev.javaspringcompose.services.impl;

import static org.mockito.ArgumentMatchers.any;
import gabri.dev.javaspringcompose.entities.Autor;
import gabri.dev.javaspringcompose.models.AutorModel;
import gabri.dev.javaspringcompose.repositories.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorServiceImplTest {

    /**
     * sector de preparacion  mocks
     */

    @Mock
    private AutorRepository autoresRepository;

    @InjectMocks
    private AutorServiceImpl autorService;


    /**
     * sector de testeos de los metodos PUBLICOS
     */

    @Test
    void findAll() {
        /*
        lo primero que hacemos es crear una entidad
        con esto es con lo que respondera mockito
         */
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setNombre("Pedro");
        autor.setApellido("Pascal");
        autor.setNacionalidad("chileno");

        /*
        paso siguiente le decimos que cuando se ejecute el metodo findAll()
        solo responda con lo que nosotros queremos (la entidad que creamos)
         */
        when(autoresRepository.findAll()).thenReturn(List.of(autor));

        /*
        ahora vamos a testear que este metodo funcione
         */
        List<AutorModel> resultado = autorService.findAll();

        /*
        si llegamos aca significa que al menos el metodo no exploto, asi que
        ahora solo falta saber si trajo lo que nosotros le dijimos que tenia que traer
         */
        assertEquals(1,resultado.size());
        assertEquals("Pedro",resultado.get(0).getNombre());
        assertEquals("Pascal",resultado.get(0).getApellido());

        /*
        le preguntamos a mockito si el metodo findAll() fue utilizado
         */
        verify(autoresRepository).findAll();
    }

    @Test
    void findById() {
        Long id = 1L;

        Autor autor = new Autor();
        autor.setId(id);
        autor.setNombre("Jose");
        autor.setApellido("Alejandro");
        autor.setNacionalidad("español");

        when(autoresRepository.findById(id))
                .thenReturn(Optional.of(autor));

        AutorModel resultado = autorService.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Jose", resultado.getNombre());

        verify(autoresRepository).findById(id);
    }

    @Test
    void findById_WhenAuthorDoesNotExist_ThrowsException() {
        Long id = 1L;

        when(autoresRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> autorService.findById(id)
        );

        verify(autoresRepository).findById(id);
    }



    @Test
    void create() {
        AutorModel autorModel = new AutorModel();
        autorModel.setNombre("Gabriel");
        autorModel.setApellido("Castillo");
        autorModel.setNacionalidad("Argentina");

        when(autoresRepository.save(any(Autor.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AutorModel resultado = autorService.create(autorModel);

        assertNotNull(resultado);
        assertEquals("Gabriel", resultado.getNombre());
        assertEquals("Castillo", resultado.getApellido());
        assertEquals("Argentina", resultado.getNacionalidad());

        verify(autoresRepository).save(any(Autor.class));
    }


    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}