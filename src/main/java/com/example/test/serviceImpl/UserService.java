package com.example.test.serviceImpl;

import com.example.test.dto.UserDTO;
import com.example.test.dto.UserOrderDTO;
import com.example.test.entity.Theme;
import com.example.test.entity.User;
import com.example.test.repository.ThemeRepository;
import com.example.test.repository.UserRepository;
import com.example.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ThemeRepository themeRepository;

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
    public boolean save(User entity) {
        userRepository.save(entity);
        return  true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.findEmployeeByEmail(email) != null;
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
    public UserDetails loadUserByUsername(String username) {
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
        if(save(u)){
            return true;
        }
        return false;
    }
}
