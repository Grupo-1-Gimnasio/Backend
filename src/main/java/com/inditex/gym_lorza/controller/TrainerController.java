package com.inditex.gym_lorza.controller;

import com.inditex.gym_lorza.exception.ObjectNotFoundException;
import com.inditex.gym_lorza.model.Trainer;
import com.inditex.gym_lorza.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainers")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@Valid @RequestBody Trainer newTrainer){
        Trainer savedTrainer = trainerService.addTrainer(newTrainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrainer);
    }

    @GetMapping
    public List<Trainer> getAllTrainers() {
        return  trainerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id){
        Optional<Trainer> foundTrainer = trainerService.findTrainer(id);
        if(foundTrainer.isPresent()) {
            return new ResponseEntity<>(foundTrainer.get(), HttpStatus.FOUND);
        }
        throw new ObjectNotFoundException("trainer", id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updatedTrainerById(@PathVariable Long id, @Valid @RequestBody Trainer trainer) {
        Trainer updatedTrainer = trainerService.updateTrainer(id, trainer);
        return ResponseEntity.ok(updatedTrainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        if (trainerService.findTrainer(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            trainerService.deleteTrainer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
