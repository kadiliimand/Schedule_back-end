package com.example.demo.security;

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