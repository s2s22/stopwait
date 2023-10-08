package com.example.stopwait.vaildation;

import com.example.stopwait.restaurant.RestaurantSaveDto;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class vaildationTest {

    @Test
     void 검증() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        RestaurantSaveDto dto = new RestaurantSaveDto();
        dto.setName("  ");
        dto.setContent("검증");

        Set<ConstraintViolation<RestaurantSaveDto>> validate = validator.validate(dto);
        for (ConstraintViolation<RestaurantSaveDto> objectConstraintViolation : validate) {
            System.out.println("validate = " + validate);
        }
    }
}
