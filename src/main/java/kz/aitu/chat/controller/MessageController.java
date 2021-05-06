package kz.aitu.chat.controller;

import kz.aitu.chat.model.Message;
import kz.aitu.chat.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/messages")
@AllArgsConstructor
public class MessageController {
    private MessageRepository messageRepository;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(messageRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(messageRepository.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addMessage(@RequestBody Message message){
        return ResponseEntity.ok(messageRepository.save(message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id){
        messageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
