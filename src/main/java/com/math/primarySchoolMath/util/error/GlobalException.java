package com.math.primarySchoolMath.util.error;

import com.math.primarySchoolMath.dto.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = {

           IdInvalidException.class
    })
    public ResponseEntity<RestResponse<Object>> handleIdException(IdInvalidException idException) {
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(idException.getMessage());
        res.setMessage("Exception occurs...");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<RestResponse<Object>> validationError(MethodArgumentNotValidException ex) {
//        BindingResult result = ex.getBindingResult();
//        final List<FieldError> fieldErrors = result.getFieldErrors();
//
//        RestResponse<Object> res = new RestResponse<Object>();
//        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        res.setError(ex.getBody().getDetail());
//
//        List<String> errors = fieldErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());
//        res.setMessage(errors.size() > 1 ? errors : errors.get(0));
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
//    }
}
