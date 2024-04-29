package br.com.coffeebreak.service;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import br.com.coffeebreak.service.exception.EmailCadastradoException;
import br.com.coffeebreak.service.exception.FuncionarioIdNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    /**
     * Retorna uma lista de  funcionarios.
     * @Return List Funcionario
     */
    @Transactional(readOnly = true)
    public List<Funcionario> getFuncionarios() {
        return repository.findAll();
    }

    /**
     * Retorna um funcionarios por id.
     * @Return Funcionario
     */
    @Transactional(readOnly = true)
    public Funcionario getFuncionarioPorID(String id) {
        if (repository.existsById(id)) {
            return repository.getReferenceById(id);
        }
        return null;
    }

    /**
     * Atualiza funcionario no banco de daoos.
     * @Return void
     */
    @Transactional
    public void atualizarFuncionario(Funcionario funcionario) {
        repository.save(funcionario);
    }

    /**
     * Verifica se o email não existe e salva o funcionario no banco de dados.
     * @Return void
     */
    @Transactional
    public void insert(Funcionario funcionario) {
        Optional<Funcionario> funcionarioOptional = repository.findByEmail(funcionario.getEmail());

        if (funcionarioOptional.isPresent()){
            throw new EmailCadastradoException("Email já cadastrado");
        }
        repository.save(funcionario);
    }

    /**
     * Deleta um funcionario por id.
     * @Return void
     */
    @Transactional
    public void deletarPorId(String id){
        if (!repository.existsById(id)){
           throw new FuncionarioIdNaoEncontradoException("ID do funcionário não encontrado!");
        }
        repository.deleteById(id);
    }

    /**
     * Retorna uma lista de tipos de funcionário.
     * @Return List TipoFuncionario
     */
    public List<String> tiposFuncionario() {
        List<String> tiposFuncionario = new ArrayList<>();
        for (TipoFuncionario tipo : TipoFuncionario.values()) {
            tiposFuncionario.add(tipo.name());
        }
        return tiposFuncionario;
    }
}
