package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.dto.user.CreateUserDto;
import com.unibuc.event.ticketing.dto.user.UpdateUserDto;
import com.unibuc.event.ticketing.dto.user.UserCreatedDto;
import com.unibuc.event.ticketing.dto.user.UserInfoDto;
import com.unibuc.event.ticketing.model.Account;
import com.unibuc.event.ticketing.model.User;
import com.unibuc.event.ticketing.repository.AccountRepository;
import com.unibuc.event.ticketing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private UserService userService;
    private final User user = new User("userId", "test@email.com", "password", new Account());
    private final Account account = new Account("accountId", "test", new User(), new ArrayList<>());

    @BeforeEach
    public void setup() {
        user.setAccount(account);
        account.setUser(user);
    }

    @Test
    public void createUserTest() {
        var createUser = new CreateUserDto(user.getEmail(), user.getPassword(), account.getName());
        var result = userService.createUser(createUser);

        Mockito.verify(userRepository).save(any(User.class));
        Mockito.verify(accountRepository).save(any(Account.class));

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(UserCreatedDto.class);
    }

    @Test
    public void updateUserTest() throws Exception {
        var updateUser = new UpdateUserDto(user.getUserId(), user.getEmail(), "newPassword",
                user.getPassword(), account.getName());

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(accountRepository.findById(account.getAccountId())).thenReturn(Optional.of(account));

        userService.updateUser(updateUser);

        Mockito.verify(userRepository).save(any(User.class));
        Mockito.verify(accountRepository).save(any(Account.class));
    }

    @Test
    public void getUserTest() throws Exception {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        var result = userService.getUser(user.getUserId());

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(User.class);
    }

    @Test
    public void getAccountTest() throws Exception {
        when(accountRepository.findById(account.getAccountId())).thenReturn(Optional.of(account));

        var result = userService.getAccount(account.getAccountId());

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Account.class);
    }

    @Test
    public void getUserInfoTest() throws Exception {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(accountRepository.findById(account.getAccountId())).thenReturn(Optional.of(account));

        var result = userService.getUserInfo(user.getUserId());

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(UserInfoDto.class);
    }
}
