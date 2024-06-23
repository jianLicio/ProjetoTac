// package utfpr.edu.br.t_a_c.projeto_t_a_c.eventService;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
// import org.springframework.web.reactive.function.BodyInserters;
// import org.springframework.web.reactive.function.client.WebClient;

// import lombok.Data;
// import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;
// import utfpr.edu.br.t_a_c.projeto_t_a_c.security.JwtUtil;

// @Service
// public class EventoService {
//     private static final Logger logger = LoggerFactory.getLogger(EventoService.class);

//     private final WebClient webClient;
//     private final JwtUtil jwt;
//     private final Pessoa pessoa;

//     public EventoService(WebClient.Builder webClientBuilder, JwtUtil jwt) {
//         this.webClient = webClientBuilder.baseUrl("http://localhost:3001").build();
//         this.jwt = jwt;
//         this.pessoa = new Pessoa();
//     }

//     public void emitirEvento(String method, String path, Object body) {
//         String token = jwt.generateToken(pessoa.getEmail());
//         CarregarEvento payload = new CarregarEvento(method, path, body);

//         logger.info("Emitindo evento: Method: {}, Path: {}, Body: {}", method, path, body);

//         webClient.post()
//                 .uri("/events")
//                 .header("Authorization", "Bearer " + token)
//                 .body(BodyInserters.fromValue(payload))
//                 .retrieve()
//                 .bodyToMono(Void.class)
//                 .doOnError(e -> logger.error("Erro ao emitir evento", e))
//                 .doOnSuccess(v -> logger.info("Evento emitido com sucesso"))
//                 .block();
//     }

//     @Data
//     private static class CarregarEvento {
//         private String method;
//         private String path;
//         private Object body;

//         public CarregarEvento(String method, String path, Object body) {
//             this.method = method;
//             this.path = path;
//             this.body = body;
//         }

//     }
// }
