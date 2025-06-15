package com.escola.service;

import com.escola.dto.AlunoRequestDTO;
import com.escola.entidy.Aluno;
import com.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(UUID id) {
        return alunoRepository.findById(id);
    }

    public Aluno criarAluno(AlunoRequestDTO alunoDTO) {
        Aluno novoAluno = new Aluno();
        novoAluno.setPrimeiroNome(alunoDTO.primeiroNome());
        novoAluno.setUltimoNome(alunoDTO.ultimoNome());
        novoAluno.setDataNascimento(alunoDTO.dataNascimento());
        novoAluno.setMatricula(alunoDTO.matricula());

        return alunoRepository.save(novoAluno);
    }

    public Optional<Aluno> atualizarAluno(UUID id, AlunoRequestDTO alunoDTO) {
        return alunoRepository.findById(id)
                .map(alunoExistente -> {
                    alunoExistente.setPrimeiroNome(alunoDTO.primeiroNome());
                    alunoExistente.setUltimoNome(alunoDTO.ultimoNome());
                    alunoExistente.setDataNascimento(alunoDTO.dataNascimento());
                    alunoExistente.setMatricula(alunoDTO.matricula());
                    return alunoRepository.save(alunoExistente);
                });
    }

    public boolean deletarAluno(UUID id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
