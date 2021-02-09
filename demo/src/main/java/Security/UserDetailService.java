package Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class UserDetailService implements UserDetailsService {
//    @Autowired
//    private BankingService bankingRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        String password = bankingRepository.findPasswordByIdNumber(idNumber);
//        return User.withUsername(userName)
//                .password(password)
//                .roles("USER").build();
//    }
//}