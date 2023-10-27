package org.project.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.project.entity.UserDataEntity;
import org.project.service.dto.UserDTO;

import java.util.regex.Pattern;

public class Util {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static UserDTO createObjectUserDTO(UserDataEntity userDataEntity) {
        return UserDTO.builder()
                .id(userDataEntity.getId())
                .name(userDataEntity.getName())
                .email(userDataEntity.getEmail())
                .password(userDataEntity.getPassword())
                .phones(userDataEntity.getPhones())
                .created(userDataEntity.getCreated())
                .modified(userDataEntity.getModified())
                .lastLogin(userDataEntity.getLastLogin())
                .token(userDataEntity.getToken())
                .isActive(userDataEntity.getIsActive())
                .build();
    }

    public static ModelMapper modelMapperUtil() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        modelMapper.getConfiguration()
                .setPropertyCondition(context -> {
                    Object sourceValue = context.getSource();
                    return sourceValue != null;
                });

        return modelMapper;
    }
}
