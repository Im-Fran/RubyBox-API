package cl.franciscosolis.rubybox.models;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.repository.support.QueryMethodParameterConversionException;

/**
 * Representación de un error
 * en una respuesta HTTP.
 */
@Getter
@Setter
@Accessors(chain = true)
public class HttpError {

    private int statusCode;
    private String statusMessage;
    private String exceptionType;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("status_code", statusCode);
        json.addProperty("status_message", statusMessage);
        json.addProperty("exception_type", exceptionType);
        return json.toString();
    }

    public static String localizeError(Exception ex) {
        if(ex instanceof QueryMethodParameterConversionException e) {
            return "Error en la conversión de parámetros";
        }

        return ex.getMessage();
    }
}
