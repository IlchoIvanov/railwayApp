package com.example.railwayapp.service;

import com.example.railwayapp.Model.Dto.CommentAddDto;
import com.example.railwayapp.Model.Entity.Comment;
import com.example.railwayapp.Model.Entity.Picture;
import com.example.railwayapp.Model.Entity.User;
import com.example.railwayapp.Repository.CommentRepository;
import com.example.railwayapp.Repository.PictureRepository;
import com.example.railwayapp.Repository.UserRepository;
import com.example.railwayapp.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CommentServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private PictureService pictureService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PictureRepository pictureRepository;

    @Mock
    private UserRepository userRepository;

  @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void postComment() {
        // Given
        CommentAddDto commentAddDto = new CommentAddDto();
        commentAddDto.setContent("Test comment");
        commentAddDto.setAuthor("testuser");
        commentAddDto.setPictureId(1L);

        User user = new User();
        user.setUsername("testuser");
        Picture picture = new Picture();
        picture.setId(1L);

        when(userService.findUserByUsername("testuser")).thenReturn(user);
        when(pictureService.getPicturebyId(1L)).thenReturn(picture);


        commentService.postComment(commentAddDto);


        ArgumentCaptor<Comment> commentCaptor = ArgumentCaptor.forClass(Comment.class);
        Mockito.verify(commentRepository, times(1)).save(commentCaptor.capture());
        assertEquals("Test comment", commentCaptor.getValue().getContent());
        assertEquals(user, commentCaptor.getValue().getAuthor());
        assertEquals(picture, commentCaptor.getValue().getPicture());
    }

    @Test
    void deleteCommentById() {

        Comment comment = new Comment();
        comment.setId(1L);

        User user = new User();
        user.setUsername("testuser");
        comment.setAuthor(user);

        Picture picture = new Picture();
        picture.setId(1L);
        comment.setPicture(picture);

        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));


        commentService.deleteCommentById(1L);

        Mockito.verify(commentRepository, times(1)).delete(comment);
        Mockito.verify(userRepository, times(1)).save(user);
        Mockito.verify(pictureRepository, times(1)).save(picture);
    }

    @Test
    void deleteOldComments() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeTwoYears = now.minusYears(2L);


        commentService.deleteOldComments();


        Mockito.verify(commentRepository, times(1)).deleteOlderComments(beforeTwoYears);
    }
}
