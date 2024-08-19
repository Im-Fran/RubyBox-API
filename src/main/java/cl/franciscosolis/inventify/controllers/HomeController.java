package cl.franciscosolis.inventify.controllers;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * Home controller. Es encargado de manejar las peticiones a la raíz de la API.
 * La anotación @RestController indica que esta clase es un controlador de Spring.
 */
@RestController("/")
public class HomeController {

    /*
     * Función que maneja las peticiones a la raíz de la API.
     * La anotación @GetMapping indica que esta función maneja peticiones GET.
     * La anotación @ResponseBody indica que el valor de retorno de esta función debe ser vinculado
     * directamente al cuerpo de la respuesta HTTP.
     *
     * Retorna un objeto JSON con un mensaje de bienvenida.
     */
    @GetMapping("/")
    public @ResponseBody JsonObject index() {
        JsonObject json = new JsonObject();
        json.addProperty("status", "ok");
        json.addProperty("message", "Te damos la bienvenida a Inventify");
        return json;
    }
}
