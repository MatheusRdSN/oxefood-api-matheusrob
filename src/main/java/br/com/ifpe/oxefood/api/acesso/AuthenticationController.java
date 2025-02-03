package br.com.ifpe.oxefood.api.acesso;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.seguranca.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag; 

@Tag(
    name = "API Autenticação",
    description = "API responsável pelos serviços de Autenticação no sistema"
)
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {

    private final JwtService jwtService;
    
    private UsuarioService usuarioService;

    public AuthenticationController(JwtService jwtService, UsuarioService usuarioService) {

        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Operation(
        summary = "Autenticação de Usuário",
        description = "Realiza a autenticação do usuário e retorna o token JWT"
    )
    // essa função é responsável por autenticar o usuário e gerar o token JWT, esse map faz o papel de um objeto JSON
    @PostMapping
    public Map<Object, Object> signin(@RequestBody AuthenticationRequest data) {
        
        // autentica o usuário
        Usuario authenticatedUser = usuarioService.authenticate(data.getUsername(), data.getPassword());

        // gera o token JWT
        String jwtToken = jwtService.generateToken(authenticatedUser);

        // cria e retorna um objeto JSON com os dados do usuário autenticado e o token JWT
        Map<Object, Object> loginResponse = new HashMap<>();
        loginResponse.put("username", authenticatedUser.getUsername());
        loginResponse.put("token", jwtToken);
        loginResponse.put("tokenExpiresIn", jwtService.getExpirationTime());

        return loginResponse;
    }    
}
