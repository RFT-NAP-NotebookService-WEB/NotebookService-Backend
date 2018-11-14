package hu.unideb.inf.notebookservice.backend.rest;

import hu.unideb.inf.notebookservice.service.domain.User;
import hu.unideb.inf.notebookservice.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserRestController {

//    private final UserService userService;
//
//    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
//    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
//        System.out.println("Fetching User with username " + username);
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            System.out.println("User with username " + username + " not found");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }


}
