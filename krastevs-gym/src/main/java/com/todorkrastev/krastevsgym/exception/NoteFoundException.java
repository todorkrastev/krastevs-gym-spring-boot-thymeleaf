package com.todorkrastev.krastevsgym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoteFoundException extends RuntimeException {
    private final Long exerciseId;
    private final Long authorId;

    public NoteFoundException(Long exerciseId, Long authorId) {
        super(String.format("Note not found with exercise id: '%d' and author id: '%d'", exerciseId, authorId));
        this.exerciseId = exerciseId;
        this.authorId = authorId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
