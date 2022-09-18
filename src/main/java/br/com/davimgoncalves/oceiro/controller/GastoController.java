package br.com.davimgoncalves.oceiro.controller;

import br.com.davimgoncalves.oceiro.annotation.LogExecutionTime;
import br.com.davimgoncalves.oceiro.dto.ConsultaGastoResponseDTO;
import br.com.davimgoncalves.oceiro.dto.GastoRequestDTO;
import br.com.davimgoncalves.oceiro.dto.GastoResponseDTO;
import br.com.davimgoncalves.oceiro.dto.SalvarGastoRequestDTO;
import br.com.davimgoncalves.oceiro.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/gasto")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @PostMapping
    @LogExecutionTime
    public ResponseEntity<GastoResponseDTO> cadastrarGasto(@RequestBody @Valid SalvarGastoRequestDTO salvarGastoRequestDTO) {
        return new ResponseEntity<>(gastoService.cadastrarGasto(salvarGastoRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping
    @LogExecutionTime
    public ResponseEntity<GastoResponseDTO> atualizarGasto(@RequestBody @Valid GastoRequestDTO gastoRequestDTO) {
        return ResponseEntity.ok(gastoService.atualizarGasto(gastoRequestDTO));
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<ConsultaGastoResponseDTO> consultarGasto(@PathVariable("id") String id) {
        return ResponseEntity.ok(gastoService.consultarGasto(id));
    }

    @DeleteMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<Void> removerGasto(@PathVariable("id") String id) {
        gastoService.removerGasto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dia/{data}")
    @LogExecutionTime
    public ResponseEntity<List<ConsultaGastoResponseDTO>> consultarGastosPorDia(@PathVariable("data") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
        return ResponseEntity.ok(gastoService.consultarGastosPorDia(data));
    }

    @GetMapping("/mes/{data}")
    @LogExecutionTime
    public ResponseEntity<List<ConsultaGastoResponseDTO>> consultarGastosPorMes(@PathVariable("data") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
        return ResponseEntity.ok(gastoService.consultarGastosPorMes(data));
    }

}
