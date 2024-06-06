// package utfpr.edu.br.t_a_c.projeto_t_a_c.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;
// import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.PessoaRepository;

// @Service
// public class DetalhesUsuarioService implements UserDetailsService{

//     @Autowired
//     private PessoaRepository pessoaRepository;

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         Pessoa pessoa = pessoaRepository.findByEmail(email);
//         if (pessoa == null) {
//             throw new UsernameNotFoundException("Usuario não encontrado com o email: " + email);
//         }
//         return new DetalhesUsuario(pessoa);
//     }
// }
