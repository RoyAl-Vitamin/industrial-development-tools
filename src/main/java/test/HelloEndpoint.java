package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.entity.Order;
import test.entity.OrderPos;
import test.entity.TestEntity;
import test.repository.OrderPosRespository;
import test.repository.OrderRepository;
import test.repository.Repository;

import java.util.*;

@RestController
public class HelloEndpoint {

    Logger logger = LoggerFactory.getLogger(HelloEndpoint.class);

    private final Repository testRepo;

    private final OrderRepository orderRepository;

    private final OrderPosRespository orderPosRespository;

    public HelloEndpoint(Repository repo, OrderRepository orderRepository, OrderPosRespository orderPosRespository) {
        this.testRepo = repo;
        this.orderRepository = orderRepository;
        this.orderPosRespository = orderPosRespository;
    }

    @RequestMapping("/world")
    public String world() {
        Random r = new Random();
        Order order = new Order();
        order.setPhone(String.valueOf(r.nextInt(999999999)));
        order.setRemark(String.valueOf(r.nextInt(999999999)) + ":" + String.valueOf(r.nextInt(999999999)));
        orderRepository.save(order);
        Set<OrderPos> orederPoses = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            OrderPos orderPos = new OrderPos();
            orderPos.setOrder(order);
            orderPos.setPrice(r.nextDouble());
            orderPos.setQuantity(r.nextInt());
            orderPos.setGoodName(String.valueOf(r.nextInt(999999999)) + ":" + String.valueOf(r.nextInt(999999999)));
            orederPoses.add(orderPos);

            orderPosRespository.save(orderPos);
        }
        order.setOrderPoses(orederPoses);

        orderRepository.save(order);
        return "";
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
