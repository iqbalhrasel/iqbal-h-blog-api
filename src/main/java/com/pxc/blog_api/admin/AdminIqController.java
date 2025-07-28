package com.pxc.blog_api.admin;

import com.pxc.blog_api.blogs.exceptions.TopicNotFoundException;
import com.pxc.blog_api.iqs.InterviewQuestionNotFoundException;
import com.pxc.blog_api.iqs.dtos.IqDraftDto;
import com.pxc.blog_api.iqs.dtos.IqDraftResponse;
import com.pxc.blog_api.iqs.dtos.IqSubmitRequest;
import com.pxc.blog_api.iqs.services.InterviewQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/iq")
public class AdminIqController {
    private final InterviewQuestionService interviewQuestionService;

    public AdminIqController(InterviewQuestionService interviewQuestionService) {
        this.interviewQuestionService = interviewQuestionService;
    }

    //delete
    @DeleteMapping("/{iqId}")
    public ResponseEntity<Void> deleteQna(@PathVariable(name = "iqId") Integer iqId){
        interviewQuestionService.deleteQna(iqId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private List<IqDraftResponse> getAllDraftedIqs(){
        return interviewQuestionService.getAllDraftedIqs();
    }

    @PostMapping("/publish")
    public ResponseEntity<Void> publishQna(@RequestBody IqSubmitRequest request){
        interviewQuestionService.publishQna(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/publish/{iqId}")
    public ResponseEntity<Void> updatePublishedQna(@PathVariable(name = "iqId") Integer iqId,
                                           @RequestBody IqSubmitRequest request){
        interviewQuestionService.updateQna(iqId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("draft/{iqId}")
    private ResponseEntity<IqDraftDto> getDraftedIq(@PathVariable(name = "iqId") Integer iqId){
        IqDraftDto iqDraftDto = interviewQuestionService.getDraftedIq(iqId);
        return ResponseEntity.ok(iqDraftDto);
    }

    @PostMapping("/draft")
    public ResponseEntity<IqDraftResponse> draftQna(@RequestBody IqSubmitRequest request){
        IqDraftResponse response = interviewQuestionService.draftQna(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/draft/{iqId}")
    public ResponseEntity<IqDraftResponse> updateDraftQna(@PathVariable(name = "iqId") Integer iqId,
                                                          @RequestBody IqSubmitRequest request){
        IqDraftResponse response = interviewQuestionService.updateQna(iqId, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/draft/{iqId}/publish")
    public ResponseEntity<Void> publishDraftedQna(@PathVariable(name = "iqId") Integer iqId){
        interviewQuestionService.publishDraftedQna(iqId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/draft/{iqId}/publish-content")
    public ResponseEntity<Void> publishDraftQna(@PathVariable(name = "iqId") Integer iqId,
                                                          @RequestBody IqSubmitRequest request){
        interviewQuestionService.publishDraftQna(iqId, request);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTopicNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "topic not found"));
    }

    @ExceptionHandler(InterviewQuestionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleIqNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "question not found"));
    }
}
