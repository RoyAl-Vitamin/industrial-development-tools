package vi.al.ro.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vi.al.ro.entity.GroupProduct;
import vi.al.ro.repository.GroupProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("group")
public class GroupApi {

    private Logger logger = LoggerFactory.getLogger(GroupApi.class);

    private final GroupProductRepository groupRepo;

    public GroupApi(GroupProductRepository groupRepo) {
        this.groupRepo = groupRepo;
    }

//*********************************************************************************************************************/
//
//  GET (Read)
//
//*********************************************************************************************************************/

    /**
     * Возвращает всех пользователей
     * @return {@link vi.al.ro.entity.GroupProduct}'s
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getGroupEntireCollection() {
        logger.info("#getGroupEntireCollection: get all");

        List<GroupProduct> groups = new ArrayList<>();
        try {
            groupRepo.findAll().forEach(groups::add);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(groups);
    }

    /**
     * Возвращает группу по id
     * @param id группы
     * @return {@link GroupProduct}
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getGroupSpecificItem(@PathVariable int id) {
        logger.info("#getGroupSpecificItem: get Group with id == " + id);

        GroupProduct group;
        try {
            group = groupRepo.findById(id).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

//*********************************************************************************************************************/
//
//  POST (Create)
//
//*********************************************************************************************************************/

    /**
     * Вставляет в БД группу, его переданный ID не важен, т.к. генериться базой или Hibernate
     * @param responseGroup переданная для сохранения группа
     * @return если всё прошло удачно, то в хедере можно будет найти ссылку на сохранённый объект, иначе ошибка 500
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> postGroupEntireCollection(@RequestBody GroupProduct responseGroup) {
        logger.info("#postGroupEntireCollection: " + responseGroup.toString());

        GroupProduct savedGroup = groupRepo.save(responseGroup);

        if (savedGroup != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            headers.add(HttpHeaders.LOCATION, "/group/" + savedGroup.getId());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .headers(headers)
                    .body(null);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Проверяет существование группы
     * @param id группы
     * @return код 409 если группа существует, иначе код 404
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> postGroupSpecificItem(@PathVariable int id) {
        logger.info("#postGroupSpecificItem: " + id);

        boolean exists = groupRepo.existsById(id);

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
     * @param responseGroups коллекция на удаление
     * @return 405 (Method Not Allowed), unless you want to delete the whole collection—not often desirable
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGroupEntireCollection(@RequestBody List<GroupProduct> responseGroups) {
        logger.info("#deleteGroupEntireCollection: Groups count == " + responseGroups.size());

        if (responseGroups.size() == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        groupRepo.deleteAll(responseGroups);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Удаляет группу по ID
     * @param id GroupProd.id
     * @return 200 (OK). 404 (Not Found), if ID not found or invalid.
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGroupSpecificItem(@PathVariable int id) {
        logger.info("#deleteGroupSpecificItem: " + id);

        boolean exists = groupRepo.existsById(id);

        if (!exists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        groupRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
