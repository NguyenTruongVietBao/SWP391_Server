package com.math.mathcha.controller;

import com.math.mathcha.Util.Error.IdInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @ExceptionHandler(value = IdInvalidException.class)
    public ResponseEntity<String> handleBlogAlreadyExistsException(IdInvalidException idException) {
        return ResponseEntity.badRequest().body(idException.getMessage());
    }

    @GetMapping("/")
    public String getHelloWorld() throws IdInvalidException {
        if (true)
        throw new IdInvalidException("hello");
        return "Hello World (Hỏi Dân IT & Eric)";
    }
}
