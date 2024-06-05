package utfpr.edu.br.t_a_c.projeto_t_a_c.eventService;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Data;
import utfpr.edu.br.t_a_c.projeto_t_a_c.security.JwtUtil;

@Service
public class EventoService {
    private final WebClient webClient;
    private final JwtUtil jwt;

    public EventoService(WebClient.Builder webClientBuilder, JwtUtil jwt) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:3000").build();
        this.jwt = jwt;
    }

    public void emitirEvento(String method, String path, Object body) {
        String token = jwt.generateToken("your_username");
        CarregarEvento payload = new CarregarEvento(method, path, body);
        webClient.post()
                .uri("/evento")
                .header("Authorization", "Bearer " + token)
                .body(BodyInserters.fromValue(payload))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Data
    private static class CarregarEvento {
        private String method;
        private String path;
        private Object body;

        public CarregarEvento(String method, String path, Object body) {
            this.method = method;
            this.path = path;
            this.body = body;
        }

    }
}
