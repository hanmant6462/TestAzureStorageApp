package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static String convertToString(Object value) {
        String result = null;

        try {
            result = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

}