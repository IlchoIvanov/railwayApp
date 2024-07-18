package com.example.railwayapp.Web;

import com.example.railwayapp.Model.Dto.CommentAddDto;
import com.example.railwayapp.Model.Dto.CommentViewDto;
import com.example.railwayapp.Model.Dto.UserRegisterDto;
import com.example.railwayapp.Model.User.RailwayAppUserDetails;
import com.example.railwayapp.Sevice.CommentService;
import com.example.railwayapp.Sevice.PictureService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CommentsController {
    private final PictureService pictureService;
    private final CommentService commentService;

    public CommentsController(PictureService pictureService, CommentService commentService) {
        this.pictureService = pictureService;
        this.commentService = commentService;
    }

    @GetMapping("/comments/{picture-id}")
    public String comments(@PathVariable("picture-id") Long pictureId, Model model){
        List<CommentViewDto> pictureComments = pictureService.getPictureComments(pictureId);
        model.addAttribute("pictureComments", pictureComments);
        model.addAttribute("pictureUrl", pictureService.getPictureUrlById(pictureId));
        model.addAttribute("pictureId", pictureId);
        return "comments";
    }

    @ModelAttribute("commentData")
    public CommentAddDto commentAddDto() {
        return new CommentAddDto();
    }

    @PostMapping("/comments/{picture-id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String postComment(@Valid CommentAddDto commentData, BindingResult result, RedirectAttributes redirectAttributes, @PathVariable("picture-id") Long pictureId, @AuthenticationPrincipal RailwayAppUserDetails userDetails){
        ModelAndView modelAndView = new ModelAndView("comments");
        modelAndView.addObject("commentData", commentData);
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentData", commentData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentData", result);
            return "redirect:/comments/" + pictureId;
        }
        commentData.setAuthor(userDetails.getName());
        commentData.setPictureId(pictureId);

           commentService.postComment(commentData);
        return "redirect:/comments/" + pictureId;
    }
}
