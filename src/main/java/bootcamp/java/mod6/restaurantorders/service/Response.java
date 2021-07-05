package bootcamp.java.mod6.restaurantorders.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Response<T> {
    private Map<String, String> error;
    private T data;

    private HttpStatus status;

    public Response() {
        status = HttpStatus.OK;
    }

    public Response(HttpStatus status) {
        this.status = status;
    }

    public void addError(String field, String message) {
        if (this.error == null) {
            this.error = new HashMap<>();
        }
        this.error.put(field, message);
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean hasError() {
        return error != null && error.size() > 0;
    }

    public ResponseEntity<Response<T>> createResponseEntity() {
        return createResponseEntity(null);
    }

    public ResponseEntity<Response<T>> createResponseEntity(URI uri) {
        HttpStatus s = hasError() ? HttpStatus.BAD_REQUEST : this.status;
        ResponseEntity<Response<T>> r = new ResponseEntity<>(this, s);
        if (uri != null) {
            // return ResponseEntity.created(uri).body(this);
            // r.getHeaders().setLocation(uri);
        }
        return r;
    }
}
