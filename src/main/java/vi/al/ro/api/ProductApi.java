package vi.al.ro.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vi.al.ro.entity.Product;
import vi.al.ro.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("product")
public class ProductApi {

    private Logger logger = LoggerFactory.getLogger(ProductApi.class);

    private final ProductRepository prodRepo;

    public ProductApi(ProductRepository prodRepo) {
        this.prodRepo = prodRepo;
    }

//*********************************************************************************************************************/
//
//  GET (Read)
//
//*********************************************************************************************************************/

    /**
     * Возвращает все продукты
     * @return {@link vi.al.ro.entity.Product}'s
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getProdEntireCollection() {
        logger.info("#getProdEntireCollection: get all");

        List<Product> prods = new ArrayList<>();
        try {
            prodRepo.findAll().forEach(prods::add);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(prods, HttpStatus.OK);
    }

    /**
     * Возвращает продукт по id
     * @param id группы
     * @return {@link Product}
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getProdSpecificItem(@PathVariable int id) {
        logger.info("#getProdSpecificItem: get Group with id == " + id);

        Product prod;
        try {
            prod = prodRepo.findById(id).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

//*********************************************************************************************************************/
//
//  POST (Create)
//
//*********************************************************************************************************************/

    /**
     * Вставляет в БД группу, его переданный ID не важен, т.к. генериться базой или Hibernate
     * @param responseProd переданная для сохранения группа
     * @return если всё прошло удачно, то в хедере можно будет найти ссылку на сохранённый объект, иначе ошибка 500
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> postProdEntireCollection(@RequestBody Product responseProd) {
        logger.info("#postProdEntireCollection: " + responseProd.toString());

        Product savedProd = prodRepo.save(responseProd);

        if (savedProd != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            headers.add(HttpHeaders.LOCATION, "/product/" + savedProd.getId());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .headers(headers)
                    .body(null);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Проверяет существование продукта
     * @param id продукта
     * @return код 409 если группа существует, иначе код 404
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> postProdSpecificItem(@PathVariable int id) {
        logger.info("#postProdSpecificItem: " + id);

        boolean exists = prodRepo.existsById(id);

        if (exists) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//*********************************************************************************************************************/
//
//  PATCH (Update/Modify)
//
//*********************************************************************************************************************/

//*********************************************************************************************************************/
//
//  DELETE (Delete)
//
//*********************************************************************************************************************/

    /**
     * Удаляет коллекцию Product
     * @param responseProds коллекция на удаление
     * @return 405 (Method Not Allowed), unless you want to delete the whole collection—not often desirable
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGroupEntireCollection(@RequestBody List<Product> responseProds) {
        logger.info("#deleteGroupEntireCollection: prods count == " + responseProds.size());

        if (responseProds.size() == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        prodRepo.deleteAll(responseProds);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Удаляет товар по ID
     * @param id Prod.id
     * @return 200 (OK). 404 (Not Found), if ID not found or invalid.
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProdSpecificItem(@PathVariable int id) {
        logger.info("#deleteProdSpecificItem: " + id);

        boolean exists = prodRepo.existsById(id);

        if (!exists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prodRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
