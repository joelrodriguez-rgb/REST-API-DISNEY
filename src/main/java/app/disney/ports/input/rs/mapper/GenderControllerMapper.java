package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.Gender;
import org.mapstruct.Mapper;

@Mapper
public interface GenderControllerMapper extends CommonMapper{

    Gender genderRequestToGender(String gender);

}
