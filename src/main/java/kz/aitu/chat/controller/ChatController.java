package kz.aitu.chat.controller;

import kz.aitu.chat.model.Chat;
import kz.aitu.chat.model.Participant;
import kz.aitu.chat.repository.ChatRepository;
import kz.aitu.chat.repository.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/chats")
@AllArgsConstructor
public class ChatController {
    private ChatRepository chatRepository;

    private ParticipantRepository participantRepository;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(chatRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(chatRepository.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addChat(@RequestBody Chat chat){
        return ResponseEntity.ok(chatRepository.save(chat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChat(@PathVariable Long id){
       chatRepository.deleteById(id);
       return ResponseEntity.noContent().build();
    }
    @GetMapping("/find/{userId}")
    public ResponseEntity<?> findUserChats(@PathVariable Long userId){
        List<Participant> participants = participantRepository.findAllByUserId(userId);
        List<Chat> chatList = new ArrayList<>();
        for(Participant participant : participants){
            Optional<Chat> chat = chatRepository.findById(participant.getChatId());
            chatList.add(chat.get());
        }

        return ResponseEntity.ok(chatList);
    }

}
