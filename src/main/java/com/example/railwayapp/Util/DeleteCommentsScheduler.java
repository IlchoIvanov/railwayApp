package com.example.railwayapp.Util;

import com.example.railwayapp.service.CommentService;
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
