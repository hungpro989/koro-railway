package com.example.test.controller;

import com.example.test.config.*;
import com.example.test.dto.ResponseObject;
import com.example.test.dto.UserDTO;
import com.example.test.dto.UserOrderDTO;
import com.example.test.jwt.JwtTokenProvider;
import com.example.test.entity.LogHistory;
import com.example.test.repository.LogHistoryRepository;
import com.example.test.serviceImpl.LogHistoryService;
import com.example.test.serviceImpl.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import utils.CommonFunction;
import utils.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@CrossOrigin
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
@Autowired
private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    LogHistoryService logHistoryService;
    @Autowired
    private LogHistoryRepository logHistoryRepository;
    @Autowired
            private UserService userService;
    Gson gson = new Gson();
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
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
            //lấy ra đối tượng CustomerUserDetails
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            // Trả về jwt cho người dùng.
            String jwt = tokenProvider.generateToken(customUserDetails);
            // Lấy  các quyền của user
            List<String> listRoles =  customUserDetails.getAuthorities().stream()
                    .map(item->item.getAuthority())
                    .collect(Collectors.toList());
            //ghi lại lịch sử đăng nhập
            LogHistory logHistory = new LogHistory();
            logHistory.setLevel("INFO");
            logHistory.setLogger("Username: '" + loginRequest.getUsername() + "' has logged into the system");
            logHistory.setTimestamp(new Date());
            logHistory.setMessage("LOGIN");
            logHistoryRepository.save(logHistory);
            return ResponseEntity.ok().body(new ResponseObject("SUCCESS","Login successfully",
                    new LoginResponse(jwt,
                            customUserDetails.getUsername(),
                            customUserDetails.getEmail(),
                            customUserDetails.getStatus(),
                            listRoles)));
        } catch (AuthenticationException e) {
            // Xử lý khi sai tài khoản hoặc mật khẩu
            return ResponseEntity.ok().body(new ResponseObject("ERROR","Error username or password",null));
        }
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
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Access token field", null));
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
    @PostMapping("/register")
    public ResponseEntity<?>registerUser(@RequestBody String registerRequestString){
        //validate chuỗi string đầu vào registerRequestString với file validator/createUser.schema.json
        InputStream inputStream = UserController.class.getClassLoader().getResourceAsStream(Constant.JSON_REQ_CREATE_USER);
        CommonFunction.jsonValidate(inputStream,registerRequestString);
        //convert dữ liệu từ string thành RegisterRequest
        UserDTO registerRequest = gson.fromJson(registerRequestString,UserDTO.class);
        if(userService.checkExistUsername(registerRequest.getUsername())){
            return ResponseEntity.badRequest().body(new ResponseObject("ERROR","Username already exists",null));
        }
        if(userService.checkExistEmail(registerRequest.getEmail())){
            return ResponseEntity.badRequest().body(new ResponseObject("ERROR","Username already exists",null));
        }
        UserOrderDTO userOrderDTO= userService.createUser(registerRequest);
        if(userOrderDTO != null){
            return new ResponseEntity<>(new ResponseObject("SUCCESS","Registered successfully", userOrderDTO), HttpStatus.CREATED);

        }
        return new ResponseEntity<>(new ResponseObject("ERROR","Create account of user error",null), HttpStatus.BAD_REQUEST);

    }

}
