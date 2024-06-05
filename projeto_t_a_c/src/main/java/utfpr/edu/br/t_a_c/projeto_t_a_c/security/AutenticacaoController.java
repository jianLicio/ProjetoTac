package utfpr.edu.br.t_a_c.projeto_t_a_c.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/autenticar", method = RequestMethod.POST)
    public String createAuthenticationToken(
            @RequestBody AutenticacaoRequest autenticarRequest)
            throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(autenticarRequest.getNome(),
                        autenticarRequest.getSenha()));

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(autenticarRequest.getNome());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return jwt;
    }
}
