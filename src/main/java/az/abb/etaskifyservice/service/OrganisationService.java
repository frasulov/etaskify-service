package az.abb.etaskifyservice.service;

import az.abb.etaskifyservice.entity.Organisation;
import az.abb.etaskifyservice.mapper.OrganisationMapper;
import az.abb.etaskifyservice.repository.OrganisationRepository;
import az.abb.etaskifyservice.requests.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public class OrganisationService {

    private OrganisationRepository repository;

    public OrganisationService(OrganisationRepository repository) {
        this.repository = repository;
    }

    public Organisation save(SignupRequest request) {
        Organisation organisation = OrganisationMapper.INSTANCE.toOrganisation(request);

        return repository.save(organisation);
    }
}
