package net.palm7.raa.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import net.palm7.raa.model.Token;

@Repository
public interface TokenRepository extends BaseRepository<Token> {

    Optional<Token> findByToken(String token);

}
