package cl.franciscosolis.inventify;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.repository.support.QueryMethodParameterConversionException;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.logging.Logger;

public class Utils {

    private static Map<Class<?>, String> typeNames = Map.of(
            String.class, "texto",
            Integer.class, "número",
            Long.class, "número",
            Float.class, "número",
            Double.class, "número",
            Boolean.class, "booleano",
            Character.class, "carácter"
    );
    private static Map<Class<? extends Throwable>, String> errorMessages = Map.of(
            QueryMethodParameterConversionException.class, "Error al convertir %s en %s"
    );
    public static Logger logger = Logger.getLogger("Inventify");
    public static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .create();
}
