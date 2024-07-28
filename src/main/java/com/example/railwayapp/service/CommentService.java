package com.example.railwayapp.service;

import com.example.railwayapp.model.dto.CommentAddDto;

public interface CommentService {
    void postComment(CommentAddDto commentData);

    void deleteCommentById(Long id);
    public void deleteOldComments();

}
