package com.example.test.serviceImpl;

import com.example.test.config.CustomUserDetails;
import com.example.test.config.ERole;
import com.example.test.dto.UserDTO;
import com.example.test.dto.UserOrderDTO;
import com.example.test.entity.Role;
import com.example.test.entity.Theme;
import com.example.test.entity.User;
import com.example.test.mapper.UserAdapter;
import com.example.test.repository.ThemeRepository;
import com.example.test.repository.UserRepository;
import com.example.test.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service

@Slf4j
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleService roleService;
    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> listDto = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for(User var: list){
            listDto.add((new UserDTO(var)));
        }
        return listDto;
    }
    @Override
    public List<UserOrderDTO> getAllUser() {
        List<UserOrderDTO> listDto = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for(User var: list){
            listDto.add((new UserOrderDTO(var)));
        }
        return listDto;
    }
    @Override
    public UserDTO getById(Integer id) {
        if(userRepository.findById(id).isPresent()){
            User user = userRepository.findById(id).get();
            return new UserDTO(user);
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public User save(User entity) {
        return  userRepository.save(entity);
    }



    @Override
    public boolean checkExistPhone(String phone) {
        return userRepository.findEmployeeByPhone(phone) != null;
    }

    @Override
    public boolean checkExistId(Integer id) {
        return userRepository.findById(id).orElse(null) != null;
    }

    @Override
    public List<User> findEmployeeWhereNotId(Integer id, String email, String phone) {
        try {
            return userRepository.getEmployeeWhereNotId(id, email, phone);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user);
    }


    public boolean changeTheme(Integer userId,Integer themeId){
        User u = userRepository.findById(userId).orElse(null);
        Theme theme = themeRepository.findById(themeId).orElse(null);
        u.setThemes(theme);
        if(save(u)!=null){
            return true;
        }
        return false;
    }
    public UserOrderDTO createUser(UserDTO registerRequest) {
        User user = UserAdapter.userToUserDTO(registerRequest);
        user.setEmail(registerRequest.getEmail());
        user.setStatus(true);
        user.setCreatedAt(new Date());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(encoder.encode(registerRequest.getPassword()));

        Set<String> strRole = registerRequest.getListRole();
        Set<Role> listRole = new HashSet<>();
        if(strRole.isEmpty()){
            // nếu không truyền gì vào, thì mặc định là role USER
            Role userRole = roleService.findByName(ERole.USER).orElseThrow(()->new RuntimeException("Error: Role is not found"));
            listRole.add((userRole));
        }else{
            // lựa chọn role khi tạo user
            strRole.forEach(role->{
                switch (role) {
                    case "ADMIN" -> {
                        Role adminRole = roleService.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found" + registerRequest.getListRole()));
                        listRole.add(adminRole);
                    }
                    case "SALE" -> {
                        Role saleRole = roleService.findByName(ERole.SALE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found" + registerRequest.getListRole()));
                        listRole.add(saleRole);
                    }
                    case "MARKETING" -> {
                        Role marketingRole = roleService.findByName(ERole.MARKETING)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found" + registerRequest.getListRole()));
                        listRole.add(marketingRole);
                    }
                    case "OPERATOR" -> {
                        Role operatorRole = roleService.findByName(ERole.OPERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found" + registerRequest.getListRole()));
                        listRole.add(operatorRole);
                    }
                    case "USER" -> {
                        Role userRole = roleService.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found" + registerRequest.getListRole()));
                        listRole.add(userRole);
                    }
                }
            });

        }
        user.setListRoles(listRole);
        User usersave =  save(user);

        UserOrderDTO userOrderDTO = new UserOrderDTO(usersave);
        if (userOrderDTO == null) {
            return null;
        }
        return userOrderDTO;
    }

    @Override
    public boolean checkExistUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        return userRepository.findByUsername(username) != null;
    }
    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
