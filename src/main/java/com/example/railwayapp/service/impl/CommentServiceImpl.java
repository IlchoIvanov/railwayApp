package com.example.railwayapp.service.impl;

import com.example.railwayapp.Model.Dto.CommentAddDto;
import com.example.railwayapp.Model.Entity.Comment;
import com.example.railwayapp.Model.Entity.Picture;
import com.example.railwayapp.Model.Entity.User;
import com.example.railwayapp.Repository.CommentRepository;
import com.example.railwayapp.Repository.PictureRepository;
import com.example.railwayapp.Repository.UserRepository;
import com.example.railwayapp.service.CommentService;

import com.example.railwayapp.service.PictureService;
import com.example.railwayapp.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {
    private final UserService userService;
    private final PictureService pictureService;
    private final CommentRepository commentRepository;
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(UserService userService, PictureService pictureService, CommentRepository commentRepository, PictureRepository pictureRepository, UserRepository userRepository) {
        this.userService = userService;
        this.pictureService = pictureService;
        this.commentRepository = commentRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void postComment(CommentAddDto commentData) {
        Comment comment = new Comment();
        comment.setContent(commentData.getContent());
        User author = userService.findUserByUsername(commentData.getAuthor());
        comment.setAuthor(author);
        comment.setTime(LocalDateTime.now());
        Picture picture = pictureService.getPicturebyId(commentData.getPictureId());
        comment.setPicture(picture);
        commentRepository.save(comment);
        picture.getComments().add(comment);
        pictureRepository.save(picture);
        author.getUserComments().add(comment);
        userRepository.save(author);
        pictureRepository.save(picture);
    }

    @Override
    public void deleteCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        // todo: throw if comment is null
        User author =  comment.getAuthor();
        author.getUserComments().remove(comment);
        userRepository.save(comment.getAuthor());
        Picture picture = comment.getPicture();
        picture.getComments().remove(comment);
        pictureRepository.save(picture);
        commentRepository.delete(comment);
    }

    @Override
    public void deleteOldComments() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeTwoYears = now.minusYears(2L);
        commentRepository.deleteOlderComments(beforeTwoYears);
    }
}
