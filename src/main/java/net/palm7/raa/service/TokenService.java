package net.palm7.raa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.palm7.raa.model.Token;
import net.palm7.raa.repository.TokenRepository;
import net.palm7.raa.util.RentalException;

@Service
public class TokenService extends BaseService<Token> {

    private TokenRepository repository;

    @Autowired
    public TokenService(TokenRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Token findByToken(String token) throws RentalException {
        return repository.findByToken(token).orElseThrow(() -> new RentalException(RentalException.RentalErrors.TOKEN_NOT_EXISTS));
    }

}
