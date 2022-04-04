package pl.mvasio.pizzeria.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import pl.mvasio.pizzeria.Order;
import pl.mvasio.pizzeria.Pizza;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderPizzaInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public OrderRepositoryImpl (JdbcTemplate jdbc){
        this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("Pizza_Order");
        orderInserter.usingGeneratedKeyColumns("id");
        this.orderPizzaInserter = new SimpleJdbcInsert(jdbc).withTableName("Ordered_Pizza");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order add(Order order) {
        order.setCreateDate(new Date());
        long id = addOrder(order);
        order.setId(id);
        List<Pizza> pizzas = order.getPizzas();
        for (Pizza pizza : pizzas) {
            addOrderedPizza(order.getId(), pizza.getId());
        }
        return order;
    }

    private long addOrder(Order order){
        Map <String, Object> values = new HashMap<>();
        values.put("date", order.getCreateDate());
        values.put("username", order.getUsername());
        values.put("name", order.getName());
        values.put("street", order.getStreet());
        values.put("city", order.getCity());
        values.put("zip_code", order.getZipCode());
        values.put("cc_number", order.getCcNumber());
        values.put("cc_expiration", order.getCcExpiration());
        values.put("cc_cvv", order.getCcCVV());

        return orderInserter.executeAndReturnKey(values).longValue();
    }

    private void addOrderedPizza(long orderId, long pizzaId){
        Map<String, Object> values = new HashMap<>();
        values.put("order_id", orderId);
        values.put("pizza_id", pizzaId);
        orderPizzaInserter.execute(values);
    }
}
