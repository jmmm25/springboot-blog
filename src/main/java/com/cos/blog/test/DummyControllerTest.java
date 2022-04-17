package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DummyControllerTest {

    @Autowired  // 의존성 주입 (DI)
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건에 데이터를 return
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUsers = userRepository.findAll(pageable);
        List<User> users = pagingUsers.getContent();

        return users;
    }

    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("id: " + user.getId());
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("role: " + user.getRole());
        System.out.println("createDate: " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

//    @PostMapping("/dummy/join")
//    public String join(@RequestParam String username,
//                       @RequestParam String password,
//                       @RequestParam String email) {
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
//        System.out.println("email: " + email);
//
//        return "회원가입이 완료되었습니다.";
//    }

    // email, password
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,
                           @RequestBody User requestUser) {
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user);

        return null;
    }

}
