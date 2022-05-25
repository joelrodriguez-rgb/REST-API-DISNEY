package app.disney.ports.input.rs.mapper;

import app.disney.domain.model.Gender;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Amazon.com Inc.)"
)
@Component
public class GenderControllerMapperImpl implements GenderControllerMapper {

    @Override
    public Gender genderRequestToGender(String gender) {
        if ( gender == null ) {
            return null;
        }

        Gender gender1 = new Gender();

        return gender1;
    }
}
