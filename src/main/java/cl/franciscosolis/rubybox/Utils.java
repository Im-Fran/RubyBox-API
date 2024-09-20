package cl.franciscosolis.rubybox;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.repository.support.QueryMethodParameterConversionException;

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

    /**
     * Capitaliza un texto, es decir, pone la primera letra en mayúscula y el resto en minúscula.
     * @param texto Texto a capitalizar.
     * @return Texto capitalizado.
     */
    public static String capitalize(String texto) {
        if (texto == null || texto.isEmpty()) { // Si el texto es nulo o vacío
            return texto; // Devolver el texto sin modificar
        }

        if(texto.contains(" ")) {
            String[] palabras = texto.split(" "); // Dividir el texto en palabras
            StringBuilder textoCapitalizado = new StringBuilder(); // Crear un StringBuilder para almacenar el texto capitalizado
            for (String palabra : palabras) { // Iterar sobre las palabras
                textoCapitalizado.append(capitalize(palabra)).append(" "); // Capitalizar la palabra y agregarla al texto capitalizado
            }
            return textoCapitalizado.toString().trim(); // Devolver el texto capitalizado sin espacios al principio o al final
        }

        if(texto.length() == 1) { // Si el texto tiene solo un carácter
            return texto.toUpperCase(); // Devolver el carácter en mayúscula
        }

        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase(); // Devolver la primera letra en mayúscula y el resto en minúscula
    }
}
