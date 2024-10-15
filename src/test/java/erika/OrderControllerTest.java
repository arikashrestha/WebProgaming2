package erika;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import erika.entity.Order;
import erika.repo.OrderRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder() throws Exception {
        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"product\":\"Test Product\", \"quantity\":5}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrders() throws Exception {
        mockMvc.perform(get("/api/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order order = new Order();
        order.setProduct("Update Test Product");
        order.setQuantity(10);
        order = orderRepository.save(order);

        mockMvc.perform(put("/api/orders/" + order.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"product\":\"Updated Product\", \"quantity\":15}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        Order order = new Order();
        order.setProduct("Delete Test Product");
        order.setQuantity(7);
        order = orderRepository.save(order);
        
        mockMvc.perform(delete("/api/orders/" + order.getId()))
                .andExpect(status().isOk());
    }
}
