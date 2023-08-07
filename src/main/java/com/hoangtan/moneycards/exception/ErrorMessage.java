package com.hoangtan.moneycards.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {

    //SKILL
    public static final String SKILL_NOT_FOUND = "Skill not found";
    public static final String KEY_SKILL_NOT_FOUND = "exception.resource.not.found.skill";
    public static final String SKILL_ALREADY_EXISTED = "Skill already existed";
    public static final String KEY_SKILL_ALREADY_EXISTED = "exception.input.validation.skill.already.existed";
    public static final String SKILL_NAME_LENGTH_CONSTRAINT = "Skill Name length cannot exceed 255 characters";
    public static final String KEY_SKILL_NAME_LENGTH_CONSTRAINT = "exception.input.validation.skill.name.length.over.max.length";
    public static final String SKILL_DESCRIPTION_LENGTH_CONSTRAINT = "Skill Description length cannot exceed 2000 characters";
    public static final String KEY_SKILL_DESCRIPTION_LENGTH_CONSTRAINT = "exception.input.validation.skill.description.over.max.length";
    public static final String SKILL_NAME_NULL_OR_BLANK = "Skill Name cannot be null or blank";
    public static final String KEY_SKILL_NAME_NULL_OR_BLANK = "exception.input.validation.skill.name.blank.or.null";

    //TOPIC
    public static final String TOPIC_NOT_FOUND = "Topic not found";
    public static final String KEY_TOPIC_NOT_FOUND = "exception.resource.not.found.topic";
    public static final String DUPLICATED_TOPIC_NAME = "Topic Name duplicated";
    public static final String KEY_DUPLICATED_TOPIC_NAME = "exception.input.validation.duplicated.topic.name";
    public static final String TOPIC_NAME_BLANK_OR_NULL = "Topic Name cannot be null or blank";
    public static final String KEY_TOPIC_NAME_BLANK_OR_NULL = "exception.input.validation.topic.name.null.or.blank";
    public static final String TOPIC_NAME_LENGTH_CONSTRAINT = "Topic Name must have at least 3 characters and less than 255 characters";
    public static final String KEY_TOPIC_NAME_LENGTH_CONSTRAINT = "exception.input.validation.topic.name.length.invalid";
    public static final String TOPIC_DESCRIPTION_LENGTH_CONSTRAINT = "Topic Description length cannot exceed 2000 characters";
    public static final String KEY_TOPIC_DESCRIPTION_LENGTH_CONSTRAINT = "exception.input.validation.topic.description.over.max.length";

    //USER
    public static final String KEY_USER_ALREADY_EXISTED = "exception.input.validation.user.already.existed";
    public static final String USER_ALREADY_EXISTED = "User already existed";
    public static final String USER_NAME_LENGTH_CONSTRAINT = "User Name cannot exceed 255 characters";
    public static final String KEY_USER_NAME_LENGTH_CONSTRAINT = "exception.input.validation.user.name.over.max.length";
    public static final String EMAIL_BLANK_OR_NULL = "Email cannot be blank or null";
    public static final String KEY_EMAIL_BLANK_OR_NULL = "exception.input.validation.email.blank.or.null";
    public static final String PASSWORD_BLANK_OR_NULL = "Password cannot be blank or null";
    public static final String KEY_PASSWORD_BLANK_OR_NULL = "exception.input.validation.password.blank.or.null";
    public static final String EMAIL_WRONG_FORMAT = "Email is not the right format";
    public static final String KEY_EMAIL_WRONG_FORMAT = "exception.input.validation.email.wrong.format";
    public static final String PASS_EMAIL_INVALID = "Email or Password is wrong.";
    public static final String KEY_PASS_EMAIL_INVALID = "exception.input.validation.user.email.password.wrong";

    //Authorization
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized Access";
    public static final String KEY_UNAUTHORIZED_ACCESS = "exception.security.unauthorized.access";
    public static final String FORBIDDEN_ACCESS = "Forbidden Access";
    public static final String KEY_FORBIDDEN_ACCESS = "exception.security.forbidden.access";
    public static final String PASSWORD_NOT_MATCH_PATTERN = "Password must be at least 6 characters and at least 1 number";
    public static final String KEY_PASSWORD_NOT_MATCH_PATTERN = "exception.input.validation.password.not.match.pattern";
    public static final String PASSWORD_NOT_ENCODED = "Password is not encoded";
    public static final String KEY_PASSWORD_NOT_ENCODED = "exception.input.validation.password.not.encoded";

    //Department
    public static final String DEPARTMENT_NOT_FOUND = "Department not found";
    public static final String KEY_DEPARTMENT_NOT_FOUND = "exception.resource.not.found.department";
    public static final String DEPARTMENT_NAME_NULL_OR_BLANK = "Department Name cannot be null, empty or blank";
    public static final String KEY_DEPARTMENT_NAME_NULL_OR_BLANK = "exception.input.validation.department.name.null.empty.or.blank";
    public static final String DEPARTMENT_NAME_LENGTH_CONSTRAINT = "Department Name cannot exceed 255 characters";
    public static final String KEY_DEPARTMENT_NAME_LENGTH_CONSTRAINT = "exception.input.validation.department.name.over.max.length";

    //Team
    public static final String TEAM_NAME_NULL_OR_BLANK = "Team Name cannot be null, empty or blank";

    private ErrorMessage() {
    }

    static Map<String, String> errorKeyAndMessageMap() {
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put(SKILL_NAME_NULL_OR_BLANK, KEY_SKILL_NAME_NULL_OR_BLANK);
        errorMap.put(SKILL_NAME_LENGTH_CONSTRAINT, KEY_SKILL_NAME_LENGTH_CONSTRAINT);
        errorMap.put(SKILL_DESCRIPTION_LENGTH_CONSTRAINT, KEY_SKILL_DESCRIPTION_LENGTH_CONSTRAINT);

        errorMap.put(TOPIC_NAME_BLANK_OR_NULL, KEY_TOPIC_NAME_BLANK_OR_NULL);
        errorMap.put(TOPIC_NAME_LENGTH_CONSTRAINT, KEY_TOPIC_NAME_LENGTH_CONSTRAINT);
        errorMap.put(TOPIC_DESCRIPTION_LENGTH_CONSTRAINT, KEY_TOPIC_DESCRIPTION_LENGTH_CONSTRAINT);

        errorMap.put(USER_NAME_LENGTH_CONSTRAINT, KEY_USER_NAME_LENGTH_CONSTRAINT);
        errorMap.put(EMAIL_BLANK_OR_NULL, KEY_EMAIL_BLANK_OR_NULL);
        errorMap.put(EMAIL_WRONG_FORMAT, KEY_EMAIL_WRONG_FORMAT);
        errorMap.put(PASSWORD_BLANK_OR_NULL, KEY_PASSWORD_BLANK_OR_NULL);

        errorMap.put(DEPARTMENT_NAME_NULL_OR_BLANK, KEY_DEPARTMENT_NAME_NULL_OR_BLANK);
        errorMap.put(DEPARTMENT_NAME_LENGTH_CONSTRAINT, KEY_DEPARTMENT_NAME_LENGTH_CONSTRAINT);

        return errorMap;
    }
}
