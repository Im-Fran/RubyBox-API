package cl.franciscosolis.rubybox.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static cl.franciscosolis.rubybox.Utils.gson;

/*
 * En Java existen 'anotaciones', sirven para anotar clases,
 * variables, métodos, etc.
 *
 * En este caso, la anotación '@Setter' y '@Getter' son de Lombok,
 * y se encargan de generar automáticamente los métodos 'setters' y
 * 'getters' para las variables de la clase.
 *
 * En este caso están declaradas en la clase, haciendo que todas
 * las variables de la clase tengan sus respectivos 'setters' y
 * 'getters'.
 *
 * La anotación '@Entity' es de JPA (una librería de Spring Boot),
 * y se encarga de indicar que la clase es una entidad, es decir,
 * que se va a mapear a una tabla en la base de datos.
 *
 * La anotación '@Table' es de JPA, y se encarga de indicar el nombre
 * de la tabla en la base de datos, y de definir restricciones únicas
 * para las columnas de la tabla. En este caso se está indicando que
 * la columna 'id' es única, además que el nombre de la tabla es 'products'.
 *
 * La anotación '@Accessors' es de Lombok, y se encarga de permitir
 * el encadenamiento de métodos, es decir, que se puedan llamar
 * varios métodos seguidos, como en el siguiente ejemplo:
 *
 * Product product = new Product()
 *    .setId(1)
 *    .setName("Macbook Pro")
 *    .setPrice(2000.0);
 *
 * En este caso, se creó un objeto de la clase Product, y se llamaron
 * los métodos 'setId', 'setName' y 'setPrice' en un solo bloque de
 * código. Porque en lugar de generar métodos 'void' con los 'setters',
 * se generan métodos que retornan la instancia de la clase, permitiendo
 * llamar a otros métodos de la clase en la misma línea.
 *
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "products")
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Product {

    /*
     * La anotación '@Id' indica que el campo es la llave primaria
     * de la tabla en la base de datos.
     */
    @Id
    private Long id;

    private String name;

    private double price;

    /*
     * 'toString' se encarga de convertir el objeto a
     * un string, en este caso, se utiliza la librería Gson para
     * convertir el objeto a un JSON.
     *
     * Gson es una librería de Google que permite convertir objetos
     * de Java a JSON y viceversa.
     *
     * La anotación '@Override' indica que la función 'toString' está
     * sobreescribiendo una función de la clase padre. 'toString' es utilizado
     * por Java para convertir un objeto a un string. Para facilidad de lectura,
     * y procesamiento de datos vamos a sobreescribirlo para retornar un texto
     * en formato JSON.
     */
    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
