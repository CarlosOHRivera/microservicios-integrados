package com.usuario.service.controlador;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelo.Carro;
import com.usuario.service.modelo.Moto;
import com.usuario.service.repositorio.UsuarioRepository;
import com.usuario.service.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    /*  @Autowired
      private UsuarioRepository usuarioRepository;*/
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            //String mymessage ="No lo encontré";
            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.ok(usuario);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevUsuario);
    }

    // Puedo guardar Bloques de DATOS que lleguen en el RequestBody
    @PostMapping("/guardar-usuarios")
    public void guardarUsuarios(@RequestBody List<Usuario> listaUsuarios) {
        for (Usuario usuario : listaUsuarios
        ) {
            usuarioService.save(usuario);
        }

    }

//---- Manejo de los datos que llegan por Servicio desde otra API
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId") Integer id){
    Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        List<Carro> carros = usuarioService.getCarros(id);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") Integer id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        List<Moto> motos = usuarioService.getMotos(id);
        return ResponseEntity.ok(motos);
    }

// Métodos usados con Feign
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro (@PathVariable("usuarioId") int usuarioId,
                                               @RequestBody Carro carro) {
    Carro nuevoCarro = usuarioService.saveCarro(usuarioId,carro);
    return ResponseEntity.ok(nuevoCarro);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto)
    {
        Moto nuevamoto = usuarioService.saveMoto(usuarioId,moto);
        return ResponseEntity.ok(nuevamoto);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId)
    {
        Map<String, Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }
}
