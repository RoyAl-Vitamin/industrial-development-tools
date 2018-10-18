package vi.al.ro.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vi.al.ro.entity.User;
import vi.al.ro.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("user")
public class UserApi {

    private Logger logger = LoggerFactory.getLogger(UserApi.class);

    private final UserRepository userRepo;

    public UserApi(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

//*********************************************************************************************************************/
//
//  GET (Read)
//
//*********************************************************************************************************************/

    /**
     * Возвращает всех пользователей
     * @return {@link User}'s
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getUserEntireCollection() {
        logger.info("#getUserEntireCollection: get all");

        List<User> users = new ArrayList<>();
        try {
            userRepo.findAll().forEach(users::add);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Возвращает пользователя по id
     * @param id пользователя
     * @return {@link User}
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getUserSpecificItem(@PathVariable int id) {
        logger.info("#getUserSpecificItem: get User with id == " + id);

        User user;
        try {
            user = userRepo.findById(id).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//*********************************************************************************************************************/
//
//  POST (Create)
//
//*********************************************************************************************************************/

    /**
     * Вставляет в БД пользователя, его переданный ID не важен, т.к. генериться базой или Hibernate
     * @param responseUser переданный для сохранения пользователь
     * @return если всё прошло удачно, то в хедере можно будет найти ссылку на сохранённый объект, иначе ошибка 500
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> postUserEntireCollection(@RequestBody User responseUser) {
        logger.info("#postUserEntireCollection: " + responseUser.toString());

        User savedUser = userRepo.save(responseUser);

        if (savedUser != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            headers.add(HttpHeaders.LOCATION, "/user/" + savedUser.getId());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .headers(headers)
                    .body(null);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Проверяет существование пользователя
     * @param id пользователя
     * @return ОК
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> postUserSpecificItem(@PathVariable int id) {
        logger.info("#postUserSpecificItem: " + id);

        boolean exists = userRepo.existsById(id);

        if (exists) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//*********************************************************************************************************************/
//
//  PUT (Update/Replace)
//
//*********************************************************************************************************************/

    /**
     * Обновляет весь переданный список пользователей
     * @param responseUsers список пользователей
     * @return 405 (Method Not Allowed), unless you want to update/replace every resource in the entire collection.
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<?> putUserEntireCollection(@RequestBody List<User> responseUsers) {
        logger.info("#putUserEntireCollection: users count == " + responseUsers.size());

        if (responseUsers.size() == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        Iterable<User> savedUsers = userRepo.saveAll(responseUsers);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        savedUsers.forEach((user) -> {
            headers.add(HttpHeaders.LOCATION, "/user/" + user.getId() + "; ");
        });
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(null);
    }

    /**
     * Обновляет или заменяет, если ID пользователей совпадают
     * @param id User.id
     * @return 200 (OK) or 204 (No Content). 404 (Not Found), if ID not found or invalid.
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<?> putUserSpecificItem(@PathVariable int id, @RequestBody User responseUser) {
        logger.info("#putUserSpecificItem: " + id);

        boolean exists = userRepo.existsById(id);

        if (!exists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        responseUser.setId(id);
        User savedUser = userRepo.save(responseUser);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        headers.add(HttpHeaders.LOCATION, "/user/" + savedUser.getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(null);
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
     * удаляет коллекцию User
     * @param responseUsers коллекция на удаление
     * @return 405 (Method Not Allowed), unless you want to delete the whole collection—not often desirable
     */
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserEntireCollection(@RequestBody List<User> responseUsers) {
        logger.info("#deleteUserEntireCollection: users count == " + responseUsers.size());

        if (responseUsers.size() == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        userRepo.deleteAll(responseUsers);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Удаляет пользователея по ID
     * @param id User.id
     * @return 200 (OK). 404 (Not Found), if ID not found or invalid.
     */
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserSpecificItem(@PathVariable int id) {
        logger.info("#deleteUserSpecificItem: " + id);

        boolean exists = userRepo.existsById(id);

        if (!exists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
