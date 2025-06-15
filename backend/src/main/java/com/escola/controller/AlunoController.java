package com.escola.controller;

import com.escola.dto.AlunoRequestDTO;
import com.escola.dto.AlunoResponseDTO;
import com.escola.entidy.Aluno;
import com.escola.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/alunos")

public class AlunoController {
    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> ListarAlunos() {
        List<Aluno> alunos = alunoService.listarTodos();
        List<AlunoResponseDTO> alunosDTO = alunos.stream()
                .map(aluno -> new AlunoResponseDTO(
                        aluno.getId(),
                        aluno.getPrimeiroNome(),
                        aluno.getUltimoNome(),
                        aluno.getDataNascimento(),
                        aluno.getMatricula())
                )
                .collect(Collectors.toList());
        return ResponseEntity.ok(alunosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarAlunoPorId(@PathVariable UUID id) {
        return alunoService.buscarPorId(id)
                .map(aluno -> new AlunoResponseDTO(
                        aluno.getId(),
                        aluno.getPrimeiroNome(),
                        aluno.getUltimoNome(),
                        aluno.getDataNascimento(),
                        aluno.getMatricula())
                )
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criarAluno(@RequestBody AlunoRequestDTO alunoDTO, UriComponentsBuilder uriBuilder) {
        Aluno novoAluno = alunoService.criarAluno(alunoDTO);
        var uri = uriBuilder.path("/api/v1/alunos/{id}").buildAndExpand(novoAluno.getId()).toUri();
        AlunoResponseDTO responseDTO = new AlunoResponseDTO(
                novoAluno.getId(),
                novoAluno.getPrimeiroNome(),
                novoAluno.getUltimoNome(),
                novoAluno.getDataNascimento(),
                novoAluno.getMatricula());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(@PathVariable UUID id, @RequestBody AlunoRequestDTO alunoDTO) {
        return alunoService.atualizarAluno(id, alunoDTO)
                .map(aluno -> new AlunoResponseDTO(aluno.getId(), aluno.getPrimeiroNome(), aluno.getUltimoNome(), aluno.getDataNascimento(), aluno.getMatricula()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable UUID id) {
        if (alunoService.deletarAluno(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
