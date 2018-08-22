package net.palm7.raa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.palm7.raa.model.Role;
import net.palm7.raa.repository.RoleRepository;
import net.palm7.raa.util.RentalException;

@Service
public class RoleService extends BaseService<Role> {

    private RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Role findByRole(Role.Roles role) throws RentalException {
        return repository.findByRole(role).orElseThrow(() -> new RentalException(RentalException.RentalErrors.ROLE_NOT_EXISTS));
    }

}
