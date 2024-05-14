package br.com.coffeebreak.service.funcionario;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import br.com.coffeebreak.service.exception.EmailCadastradoException;
import br.com.coffeebreak.service.exception.FuncionarioIdNaoEncontradoException;
import br.com.coffeebreak.service.funcionario.strategy.AdminStrategy;
import br.com.coffeebreak.service.funcionario.strategy.AtendenteStrategy;
import br.com.coffeebreak.service.funcionario.strategy.GerenteStrategy;
import br.com.coffeebreak.service.funcionario.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthFuncionarioService authFuncionarioService;

    /**
     * Retorna uma paginação de funcionarios.
     * @Return Page Funcionario
     */
    @Transactional(readOnly = true)
    public Page<Funcionario> getFuncionariosPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void authenticate(Funcionario funcionario, String senha) {
        LoginStrategy loginStrategy;

        if (funcionario.getTipoFuncionario().equals(TipoFuncionario.ADMIN)) {
            loginStrategy = new AdminStrategy(passwordEncoder, authenticationManager, authFuncionarioService);
            System.out.println("Chegou aqui no strategy");
            loginStrategy.login(funcionario.getEmail(), senha);
        }
        else if (funcionario.getTipoFuncionario().equals(TipoFuncionario.GERENTE)) {
            loginStrategy = new GerenteStrategy();
            loginStrategy.login(funcionario.getEmail(), funcionario.getSenha());
        } else {
            loginStrategy = new AtendenteStrategy();
            loginStrategy.login(funcionario.getEmail(), funcionario.getSenha());
        }
    }

    /**
     * Retorna uma paginação de funcionarios por nome.
     * @Return Page Funcionario
     */
    @Transactional(readOnly = true)
    public Page<Funcionario> getFuncionariosPage(String nome, Pageable pageable) {
        return repository.findAllByNomeIgnoreCase(nome, pageable);
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
     *
     * @Return void
     */
    @Transactional
    public void atualizarFuncionario(Funcionario funcionario) {
        repository
                .findById(funcionario.getId())
                .ifPresent(funcionarioUpdated -> {
                    funcionarioUpdated.setNome(funcionario.getNome());
                    funcionarioUpdated.setEmail(funcionario.getEmail());
                    funcionarioUpdated.setSenha(funcionario.getSenha());
                    funcionarioUpdated.setTipoFuncionario(funcionario.getTipoFuncionario());
                    funcionarioUpdated.setEntrada(funcionario.getEntrada());

                    repository.save(funcionarioUpdated);
                });
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

        String passwordEncoded = passwordEncoder.encode(funcionario.getSenha());

        funcionario.setSenha(passwordEncoded);

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

    public Funcionario getFuncionarioByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }
}
