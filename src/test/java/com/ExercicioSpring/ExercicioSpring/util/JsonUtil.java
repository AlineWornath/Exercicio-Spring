package com.ExercicioSpring.ExercicioSpring.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static String toJson(Object object) {
        try{
            return mapper.writeValueAsString(object);
        } catch (Exception e){
            throw new RuntimeException("Erro na conversão para Json", e);
        }
    }
}
