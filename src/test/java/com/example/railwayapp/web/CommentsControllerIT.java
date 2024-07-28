package com.example.railwayapp.web;

import com.example.railwayapp.model.dto.CommentAddDto;
import com.example.railwayapp.model.dto.CommentViewDto;
import com.example.railwayapp.model.entity.Picture;
import com.example.railwayapp.service.CommentService;
import com.example.railwayapp.service.PictureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentsControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PictureService pictureService;

    @MockBean
    private CommentService commentService;
    @Mock
    private Picture picture;

    private Long pictureId;

    @BeforeEach
    void setUp() {
        pictureId = 1L;
    }






    @Test
    void testCommentsWithoutAuthentication() throws Exception {
        // Prepare mock data
        List<CommentViewDto> pictureComments = new ArrayList<>();
        CommentViewDto commentViewDto = new CommentViewDto();
        commentViewDto.setContent("Nice picture!");
        commentViewDto.setAuthor("Anonymous");
        pictureComments.add(commentViewDto);

        // Mock the behavior of pictureService
        when(pictureService.getPictureComments(pictureId)).thenReturn(pictureComments);
        when(pictureService.getPictureUrlById(pictureId)).thenReturn("http://example.com/pic.jpg");

        // Perform GET request without authentication
        mockMvc.perform(get("/comments/{picture-id}", pictureId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pictureComments"))
                .andExpect(model().attributeExists("pictureUrl"))
                .andExpect(model().attributeExists("pictureId"))
                .andExpect(model().attributeDoesNotExist("user"));  // User attribute should not exist
    }
}
