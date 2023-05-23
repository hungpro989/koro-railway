package com.example.test.controller;

import com.example.test.config.LoginRequest;
import com.example.test.config.LoginResponse;
import com.example.test.config.RandomStuff;
import com.example.test.dto.ResponseObject;
import com.example.test.jwt.JwtTokenProvider;
import com.example.test.models.LogHistory;
import com.example.test.repository.LogHistoryRepository;
import com.example.test.service.CustomUserDetails;
import com.example.test.service.LogHistoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping
@CrossOrigin
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    LogHistoryService logHistoryService;
    @Autowired
    private LogHistoryRepository logHistoryRepository;
    Logger logger = LogManager.getLogger(LoginController.class);
    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

            // Xác thực thông tin người dùng Request lên
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            //ghi lại lịch sử đăng nhập
            LogHistory logHistory = new LogHistory();
            logHistory.setLevel("INFO");
            logHistory.setLogger("Username: '" + loginRequest.getUsername() + "' has logged into the system");
            logHistory.setTimestamp(new Date());
            logHistory.setMessage("LOGIN");
            logHistoryRepository.save(logHistory);
            return new LoginResponse(jwt);
    }
    //kiểm tra access token có hợp lệ, còn hạn sử dụng hay không
    @GetMapping("/auth/check-token")
    public ResponseEntity<ResponseObject> checkToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        // Kiểm tra header Authorization
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Lấy chuỗi token từ header Authorization
        String token = header.substring(7);

        if(tokenProvider.validateToken(token)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Access token successfully", token));
        }else{
            return ResponseEntity.status(400).body(new ResponseObject("error", "Access token field", null));
        }
    }


    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }

    @GetMapping (value="/logout")
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
}
