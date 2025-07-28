package io.github.jsousa32.libdealsign.domain;

import io.github.jsousa32.libdealsign.core.H2ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public final class AuthenticationRepository {

    private AuthenticationRepository() {
    }

    private static final Logger log = LoggerFactory.getLogger(AuthenticationRepository.class);

    public static Optional<Authentication> findById(final String anId) {
        final var sql = "SELECT id, bearer, expiresAt FROM tb_authentication WHERE id = ?";

        try (final var conn = H2ConnectionFactory.getConnection(); final var ps = conn.prepareStatement(sql)) {

            ps.setString(1, anId);

            try (final var result = ps.executeQuery()) {
                if (result.next()) {
                    final var auth = Authentication.with(
                            result.getString("id"),
                            result.getString("bearer"),
                            result.getTimestamp("expiresAt").toLocalDateTime()
                    );

                    return Optional.of(auth);
                }

                return Optional.empty();
            }

        } catch (SQLException ex) {
            log.error("Não foi possível buscar o token de authentication do Dealsign {}", ex.getMessage());
            return Optional.empty();
        }
    }

    public static Authentication save(final Authentication anAuthentication) {
        AuthenticationRepository.findById(anAuthentication.getId()).ifPresentOrElse((auth) -> {
            final var sql = "UPDATE tb_authentication SET bearer=?, expiresAt=? WHERE id = ?;";

            try (final var conn = H2ConnectionFactory.getConnection(); final var ps = conn.prepareStatement(sql)) {
                ps.setString(1, auth.getBearer());
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().plusMinutes(55)));
                ps.setString(3, auth.getId());

                ps.execute();
            } catch (SQLException ex) {
                log.error("Não foi possível atualizar o token de authentication do Dealsign {}", ex.getMessage());
            }
        }, () -> {
            final var sql = "INSERT INTO tb_authentication (id, bearer, expiresAt) VALUES (?, ?, ?)";

            try (final var conn = H2ConnectionFactory.getConnection(); final var ps = conn.prepareStatement(sql)) {
                ps.setString(1, anAuthentication.getId());
                ps.setString(2, anAuthentication.getBearer());
                ps.setTimestamp(3, Timestamp.valueOf(anAuthentication.getExpiresAt()));

                ps.execute();
            } catch (SQLException ex) {
                log.error("Não foi possível salvar o token de authentication do Dealsign {}", ex.getMessage());
            }
        });

        return anAuthentication;
    }
}
