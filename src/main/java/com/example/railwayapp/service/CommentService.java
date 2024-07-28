package com.example.railwayapp.service;

import com.example.railwayapp.Model.Dto.CommentAddDto;

public interface CommentService {
    void postComment(CommentAddDto commentData);

    void deleteCommentById(Long id);
    public void deleteOldComments();

}
