package cl.franciscosolis.rubybox.controllers;

import cl.franciscosolis.rubybox.Utils;
import cl.franciscosolis.rubybox.models.HttpError;
import com.google.gson.JsonObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class, HttpClientErrorException.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        JsonObject json = new JsonObject();
        HttpError httpError = new HttpError()
                .setExceptionType(ex.getClass().getName());

        if (ex instanceof HttpClientErrorException httpClientErrorException) {
            httpError.setStatusMessage(ex.getMessage())
                    .setStatusCode(httpClientErrorException.getStatusCode().value());
        } else {
            httpError.setStatusMessage(HttpError.localizeError(ex))
                    .setStatusCode(500);
        }

        json.addProperty("status", "error");
        json.add("error", Utils.gson.toJsonTree(httpError));

        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));

        return new ResponseEntity<>(json, headers, httpError.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        JsonObject json = new JsonObject();
        HttpError httpError = new HttpError()
                .setStatusCode(status.value())
                .setStatusMessage("Recurso no encontrado")
                .setExceptionType(NoHandlerFoundException.class.getName());

        json.addProperty("status", "error");
        json.add("error", Utils.gson.toJsonTree(httpError));

        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));

        return new ResponseEntity<>(json, headersResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        JsonObject json = new JsonObject();
        HttpError httpError = new HttpError()
                .setStatusCode(status.value())
                .setStatusMessage(ex.getMessage());

        json.addProperty("status", "error");
        json.add("error", Utils.gson.toJsonTree(httpError));

        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));

        return new ResponseEntity<>(json, headersResponse, status);
    }
}
