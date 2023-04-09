package com.usuario.service.feignclients;

import com.usuario.service.modelo.Carro;
import com.usuario.service.modelo.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carro-service",url="http://localhost:8002")
@RequestMapping("/carro")
public interface CarroFeignClient {

    @PostMapping
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
   public List<Carro> getCarros(@PathVariable("usuarioId") int usuarioId);

}
