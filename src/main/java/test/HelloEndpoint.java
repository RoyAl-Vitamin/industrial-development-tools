package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.entity.TestEntity;
import test.repository.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class HelloEndpoint {

    Logger logger = LoggerFactory.getLogger(HelloEndpoint.class);

    private final Repository testRepo;

    public HelloEndpoint(Repository repo) {
        this.testRepo = repo;
    }

    @RequestMapping("/hello")
    public List<TestEntity> hello() {

        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        TestEntity testEntity = new TestEntity();
        testEntity.setName("name" + testEntity.hashCode());

        testRepo.save(testEntity);

        testEntity = null;
        StringBuilder text = new StringBuilder();
        Iterator<TestEntity> iterator = testRepo.findAll().iterator();
        List<TestEntity> listForReturn = new ArrayList<>();
        while (iterator.hasNext()) {
            testEntity = iterator.next();
            listForReturn.add(testEntity);
            text.append(testEntity.toString()).append('\n');
        }
        if (text.toString().trim().length() == 0) {
            System.out.println("is empty");
        }
        System.out.println(text.toString());

        return listForReturn;
    }
}
