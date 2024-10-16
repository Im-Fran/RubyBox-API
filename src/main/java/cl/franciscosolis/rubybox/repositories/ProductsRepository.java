package cl.franciscosolis.rubybox.repositories;

import cl.franciscosolis.rubybox.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
 * Esta interfaz define un repositorio de datos para la entidad Producto.
 * La interfaz extiende de JpaRepository<Producto, Long>, lo que significa que
 * hereda todos los métodos de la interfaz JpaRepository, y además define
 * una función adicional para buscar productos por nombre.
 *
 * Las clases en java tienen la posibilidad de agregar tipos genéricos, que son
 * tipos que se definen en tiempo de compilación y que se pueden utilizar para
 * parametrizar clases, interfaces y métodos. En este caso, estamos parametrizando
 * la interfaz JpaRepository con dos tipos genéricos, el primero es el tipo de la
 * entidad con la que se va a trabajar, y el segundo es el tipo del identificador
 * de la entidad.
 *
 * En este caso, estamos definiendo que el repositorio va a trabajar con entidades
 * de tipo Producto, y que el identificador de la entidad es de tipo Long.
 *
 * Finalmente, se define una función que permite buscar productos por nombre, la cual
 * recibe un string con el nombre a buscar y retorna una lista de productos que contienen
 * el nombre buscado.
 *
 * Nosotros no necesitamos implementar esta interfaz, ya que Spring Data JPA se encarga
 * de generar una implementación en tiempo de ejecución.
 *
 * Esta función implementa la anotación '@Query', que permite definir una consulta personalizada
 * en JPQL (Java Persistence Query Language). JPQL es un lenguaje de consultas orientado a objetos
 * que se utiliza para realizar consultas en bases de datos relacionales.
 *
 * La anotación '@Query' recibe como parámetro una cadena con la consulta en JPQL, y se pueden
 * definir parámetros en la consulta utilizando la notación ':nombreDelParametro'. En este caso,
 * estamos definiendo un parámetro llamado 'query' que se utiliza en la consulta para buscar productos
 * que contienen el nombre buscado.
 *
 * La anotación '@Param' se utiliza para mapear un parámetro de una función a un parámetro de una consulta
 * en JPQL. En este caso, estamos mapeando el parámetro 'query' de la función 'findProductosByNameContaining'
 * al parámetro 'query' de la consulta.
 */
public interface ProductsRepository extends JpaRepository<Product, Long> {

    /**
     * Busca productos por nombre.
     *
     * @param query Nombre a buscar
     * @return Lista de productos que contienen el nombre buscado
     */
    @Query("SELECT i FROM products i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> findProductosByNameContaining(@Param("query") String query);

    /**
     * Busca productos por precio entre un rango.
     *
     * @param min El precio mínimo
     * @param max El precio máximo
     * @return Lista de productos cuyo precio está entre el rango especificado
     */
    @Query("SELECT i FROM products i WHERE i.price >= :min AND i.price <= :max")
    List<Product> findProductosByPriceBetween(@Param("min") double min, @Param("max") double max);
}
