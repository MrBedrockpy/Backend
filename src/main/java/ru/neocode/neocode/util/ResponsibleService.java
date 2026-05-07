package ru.neocode.neocode.util;

import org.springframework.http.ResponseEntity;

public interface ResponsibleService {

    default ResponseEntity<String> getSuccess() {
        return ResponseEntity.ok("Successful");
    }

}
