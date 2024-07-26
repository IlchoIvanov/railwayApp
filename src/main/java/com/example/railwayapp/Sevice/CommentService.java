package com.example.railwayapp.Sevice;

import com.example.railwayapp.Model.Dto.CommentAddDto;

public interface CommentService {
    void postComment(CommentAddDto commentData);

    void deleteCommentById(Long id);
    public void deleteOldComments();

}
