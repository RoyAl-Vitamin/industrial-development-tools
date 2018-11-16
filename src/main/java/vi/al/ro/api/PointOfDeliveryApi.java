package vi.al.ro.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vi.al.ro.entity.PointOfDelivery;
import vi.al.ro.repository.PointOfDeliveryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("pointOfDelivery")
public class PointOfDeliveryApi {

    private Logger logger = LoggerFactory.getLogger(PointOfDeliveryApi.class);

    private final PointOfDeliveryRepository pointRepo;

    public PointOfDeliveryApi(PointOfDeliveryRepository pointRepo) {
        this.pointRepo= pointRepo;
    }

//*********************************************************************************************************************/
//
//  GET (Read)
//
//*********************************************************************************************************************/

    /**
     * Возвращает все точки доставки
     * @return {@link vi.al.ro.entity.PointOfDelivery}'s
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getPointEntireCollection() {
        logger.info("#getPointEntireCollection: get all");

        List<PointOfDelivery> points = new ArrayList<>();
        try {
            pointRepo.findAll().forEach(points::add);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(points);
    }

    /**
     * Возвращает точку выдачи по id
     * @param id точки
     * @return {@link PointOfDelivery}
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getPointSpecificItem(@PathVariable int id) {
        logger.info("#getPointSpecificItem: get Group with id == " + id);

        PointOfDelivery point;
        try {
            point = pointRepo.findById(id).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(point, HttpStatus.OK);
    }

//*********************************************************************************************************************/
//
//  POST (Create)
//
//*********************************************************************************************************************/

    /**
     * Вставляет в БД точку выдачи, его переданный ID не важен, т.к. генериться базой или Hibernate
     * @param responsePoint переданная для сохранения группа
     * @return если всё прошло удачно, то в хедере можно будет найти ссылку на сохранённый объект, иначе ошибка 500
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> postPointEntireCollection(@RequestBody PointOfDelivery responsePoint) {
        logger.info("#postPointEntireCollection: " + responsePoint.toString());

        PointOfDelivery savedPoint = pointRepo.save(responsePoint);

        if (savedPoint != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            headers.add(HttpHeaders.LOCATION, "/group/" + savedPoint.getId());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .headers(headers)
                    .body(null);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Проверяет существование точки
     * @param id точки
     * @return код 409 если группа существует, иначе код 404
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> postPointSpecificItem(@PathVariable int id) {
        logger.info("#postPointSpecificItem: " + id);

        boolean exists = pointRepo.existsById(id);

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
     * Удаляет коллекцию GroupProduct
     * @param responsePoints коллекция на удаление
     * @return 405 (Method Not Allowed), unless you want to delete the whole collection—not often desirable
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePointEntireCollection(@RequestBody List<PointOfDelivery> responsePoints) {
        logger.info("#deletePointEntireCollection: Points count == " + responsePoints.size());

        if (responsePoints.size() == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        pointRepo.deleteAll(responsePoints);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Удаляет точку по ID
     * @param id PointOfDelivery.id
     * @return 200 (OK). 404 (Not Found), if ID not found or invalid.
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePointSpecificItem(@PathVariable int id) {
        logger.info("#deletePointSpecificItem: " + id);

        boolean exists = pointRepo.existsById(id);

        if (!exists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pointRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
