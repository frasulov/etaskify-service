package az.abb.etaskifyservice.repository;

import az.abb.etaskifyservice.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {
}
