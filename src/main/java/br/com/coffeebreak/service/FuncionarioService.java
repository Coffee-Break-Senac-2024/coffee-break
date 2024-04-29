package br.com.coffeebreak.service;

import br.com.coffeebreak.dto.FuncionarioDTO;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Transactional
    public List<Funcionario> getFuncionarios() {
        return repository.findAll();
    }

    public FuncionarioDTO getFuncionarioPorID(String id){
        Funcionario funcionario = repository.getReferenceById(id);
        System.out.println(copiarFuncParaDto(funcionario, new FuncionarioDTO()));

        return copiarFuncParaDto(funcionario, new FuncionarioDTO());
    }

    @Transactional
    public boolean atualizarFuncionario(FuncionarioDTO funcionarioDto) {
        Funcionario funcionario = new Funcionario();
        copiarFuncDtoParaFunc(funcionarioDto, funcionario);
        repository.save(funcionario);
        return true;
    }

    @Transactional
    public boolean insert(FuncionarioDTO funcionarioDto) {
        Funcionario funcionario = new Funcionario();
        copiarFuncDtoParaFunc(funcionarioDto, funcionario);

        if (repository.existsByEmail(funcionario.getEmail())){
               return false;
            }

        try {
            repository.save(funcionario);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    @Transactional
    public boolean deletarPorId(String id){

        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
    * Copia os dados do FuncionarioDTO para Funcionario
    * @void
    */
    private void copiarFuncDtoParaFunc(FuncionarioDTO funcionarioDto, Funcionario funcionario) {
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setTipoFuncionario(funcionarioDto.getTipoFuncionario());
        funcionario.setEmail(funcionarioDto.getEmail());
        funcionario.setSenha(funcionarioDto.getSenha());
        funcionario.setEntrada(funcionarioDto.getEntrada());
    }

    /**
     * Copia os dados do Funcionario  para FuncionarioDTO
     * @void
     */
    private FuncionarioDTO copiarFuncParaDto(Funcionario funcionario, FuncionarioDTO funcionarioDTO){
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setTipoFuncionario(funcionario.getTipoFuncionario());
        funcionarioDTO.setEmail(funcionario.getEmail());
        funcionarioDTO.setSenha(funcionario.getSenha());
        funcionarioDTO.setEntrada(funcionario.getEntrada());
        funcionarioDTO.setSaida(funcionario.getSaida());

        return funcionarioDTO;
    }
}
