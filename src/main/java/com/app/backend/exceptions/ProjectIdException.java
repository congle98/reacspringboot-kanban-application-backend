package com.app.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//thực ra không cần bad_resquest ở đây vì chỗ handler có rồi
// với cả đây là cách xử lý exception khá loằng ngoằng, tham khảo thôi :d
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException{
    public ProjectIdException(String message) {
        super(message);
    }
}
