package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import med.voll.api.domain.usuario.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("API.voll.med")
                    .withSubject(usuario.getLogin()) // campo do usuario
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
            return token;
        } catch (Exception e) {
            throw new RuntimeException("Token inválido ou expirado.", e);
        }
    }

    public String getSubject(String JWTToken) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("API.voll.med")
                    .build()
                    .verify(JWTToken)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Token inválido ou expirado.", e);
        }

    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
