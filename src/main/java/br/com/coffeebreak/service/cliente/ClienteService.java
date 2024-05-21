package br.com.coffeebreak.service.cliente;

import br.com.coffeebreak.enums.SituacaoPedido;
import br.com.coffeebreak.model.cliente.Cliente;
import br.com.coffeebreak.model.cliente.exceptions.ClientAlreadyRegistered;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.repositories.ClienteRepository;
import br.com.coffeebreak.repositories.PedidoRepository;
import br.com.coffeebreak.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteLogadoService clienteLogadoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Transactional
    public void insertCliente(Cliente cliente) {
        Optional<Cliente> clienteOP = clienteRepository.findByCpfOrEmailOrTelefone(cliente.getCpf(), cliente.getEmail(), cliente.getTelefone());

        if (clienteOP.isPresent()) {
            throw new ClientAlreadyRegistered("Cliente já foi cadastrado!");
        }

        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));

        clienteRepository.save(cliente);
    }

    public void login(String email, String senha) {
        UserDetails userDetails = this.authService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new RuntimeException("Email/Senha errados.");
        }

        boolean matches = passwordEncoder.matches(senha, userDetails.getPassword());

        if (!matches) {
            throw new RuntimeException("Email/Senha errados.");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(email, senha);
        auth = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    @Transactional
    public boolean getCliente(String email) {
        Optional<Cliente> clienteOP = clienteRepository.findByEmail(email);
        return clienteOP.isPresent();
    }

    public List<Pedido> getPedidosCliente(SituacaoPedido situacaoPedido){
        Cliente cliente = clienteLogadoService.getLoggedInCliente();
        return pedidoRepository.getPedidosPorClienteStatus(cliente.getId(), situacaoPedido.toString());
    }
}
