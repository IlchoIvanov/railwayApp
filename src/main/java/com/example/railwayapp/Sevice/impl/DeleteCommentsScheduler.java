package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Sevice.CommentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;




@Component
public class DeleteCommentsScheduler {
    private final CommentService commentService;

    public DeleteCommentsScheduler(CommentService commentService) {
        this.commentService = commentService;
    }
    @Scheduled(cron = "00 00 * * * FRI")
    public void deleteOldRecords() {
        commentService.deleteOldComments();
    }
}
