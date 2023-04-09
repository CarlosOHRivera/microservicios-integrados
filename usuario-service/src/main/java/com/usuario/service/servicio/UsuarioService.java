package com.usuario.service.servicio;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.modelo.Carro;
import com.usuario.service.modelo.Moto;
import com.usuario.service.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {
    @Autowired
    private MotoFeignClient motoFeignClient;
    @Autowired
    private CarroFeignClient carroFeignClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // La parte de comunicación ....con REST Template

    // Aqui voy... https://youtu.be/icTg6iTqpUk?t=3817
    // https://youtu.be/icTg6iTqpUk?t=4195
    // Debo concentrarme más!!!
    public List<Carro> getCarros(int usuarioId) {
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId) {
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
        return motos;
    }

    // Estos son métodos para ser usados con Feign

    // Estamos Guardando el nuevo dato

    // https://youtu.be/icTg6iTqpUk?t=5334--
    public Carro saveCarro(int usuarioId, Carro carro) {
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;

    }
    public Moto saveMoto(int usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        Moto nuevoMoto = motoFeignClient.save(moto);
        return nuevoMoto;
    }

public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
    Map<String, Object> resultado = new HashMap<>();
    Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
    if (usuario == null) {
        resultado.put("Mensaje","El Usuario No existe");
        return resultado;
    }
    resultado.put("Usuario",usuario);

    List<Carro> carros = carroFeignClient.getCarros(usuarioId);
    if (carros == null) {
        resultado.put("Carros","El Usuario No tiene Carros");
    } else {
        resultado.put("Carros",carros);
    }

    List<Moto> motos = motoFeignClient.getMotos(usuarioId);
    if (motos == null) {
        resultado.put("Motos","El Usuario No tiene Motos");
    } else {
        resultado.put("Motos",motos);
    }

    return resultado;
}


// Este son métodos normales que utilizan el Repositorio normal
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }

    public void delete(Integer id) {
        usuarioRepository.delete(getUsuarioById(id));
    }



}
