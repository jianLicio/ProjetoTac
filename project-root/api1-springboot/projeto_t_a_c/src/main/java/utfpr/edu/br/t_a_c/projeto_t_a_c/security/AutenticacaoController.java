package utfpr.edu.br.t_a_c.projeto_t_a_c.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private UserDetailsService userDetailsService;

        @PostMapping(value = "/autenticar")
        public String createAuthenticationToken(
                        @RequestBody AutenticacaoRequest autenticarRequest)
                        throws Exception {
                try {
                        authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        autenticarRequest.getEmail(),
                                                        autenticarRequest.getSenha()));
                } catch (BadCredentialsException e) {
                        throw new Exception("Credenciais inválidas", e);
                }

                final UserDetails userDetails = userDetailsService
                                .loadUserByUsername(autenticarRequest.getEmail());

                final String jwt = jwtUtil.generateToken(userDetails.getUsername());

                return jwt;
        }
}
