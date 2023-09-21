package az.abb.etaskifyservice.mapper;


import az.abb.etaskifyservice.entity.Organisation;
import az.abb.etaskifyservice.entity.User;
import az.abb.etaskifyservice.requests.SignupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganisationMapper {

    OrganisationMapper INSTANCE = Mappers.getMapper(OrganisationMapper.class);

    @Mapping(source = "organisation_name", target = "name")
    Organisation toOrganisation(SignupRequest request);
}
