package fullbuild.de.surveysitereloaded.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/debug")
public class DebugController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("Hello world");
    }

}
