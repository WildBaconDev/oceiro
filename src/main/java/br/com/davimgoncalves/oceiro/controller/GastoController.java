package br.com.davimgoncalves.oceiro.controller;

import br.com.davimgoncalves.oceiro.dto.ConsultaGastoResponseDTO;
import br.com.davimgoncalves.oceiro.dto.GastoRequestDTO;
import br.com.davimgoncalves.oceiro.dto.SalvarGastoRequestDTO;
import br.com.davimgoncalves.oceiro.dto.GastoResponseDTO;
import br.com.davimgoncalves.oceiro.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/gasto")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @PostMapping
    public ResponseEntity<GastoResponseDTO> cadastrarGasto(@RequestBody @Valid SalvarGastoRequestDTO salvarGastoRequestDTO) {
        return new ResponseEntity<>(gastoService.cadastrarGasto(salvarGastoRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<GastoResponseDTO> atualizarGasto(@RequestBody @Valid GastoRequestDTO gastoRequestDTO) {
        return ResponseEntity.ok(gastoService.atualizarGasto(gastoRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaGastoResponseDTO> consultarGasto(@PathVariable("id") String id) {
        return ResponseEntity.ok(gastoService.consultarGasto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerGasto(@PathVariable("id") String id) {
        gastoService.removerGasto(id);
        return ResponseEntity.ok().build();
    }

}
