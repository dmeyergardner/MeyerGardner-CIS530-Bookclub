package com.bookclub.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;

@WebMvcTest(WishlistRestController.class)
public class WishlistRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishlistDao wishlistDao;

    @Test
    void shouldReturnWishlistJson() throws Exception {
        WishlistItem item = new WishlistItem();
        item.setId("1");
        item.setTitle("Test Book");
        item.setIsbn("1234567890");

        Mockito.when(wishlistDao.list(Mockito.anyString())).thenReturn(Arrays.asList(item));

        mockMvc.perform(get("/api/wishlist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].isbn").value("1234567890"));
    }

    @Test
    void shouldReturnItemById() throws Exception {
        WishlistItem item = new WishlistItem();
        item.setId("2");
        item.setTitle("Second Book");
        item.setIsbn("0987654321");

        Mockito.when(wishlistDao.find("2")).thenReturn(item);

        mockMvc.perform(get("/api/wishlist/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Second Book"))
                .andExpect(jsonPath("$.isbn").value("0987654321"));
    }
}
