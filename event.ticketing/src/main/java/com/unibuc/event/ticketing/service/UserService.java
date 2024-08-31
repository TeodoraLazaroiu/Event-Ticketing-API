package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.dto.user.CreateUserDto;
import com.unibuc.event.ticketing.dto.user.UpdateUserDto;
import com.unibuc.event.ticketing.dto.user.UserCreatedDto;
import com.unibuc.event.ticketing.dto.user.UserInfoDto;
import com.unibuc.event.ticketing.exception.AccountNotFoundException;
import com.unibuc.event.ticketing.exception.InvalidPasswordException;
import com.unibuc.event.ticketing.exception.UserNotFoundException;
import com.unibuc.event.ticketing.model.Account;
import com.unibuc.event.ticketing.model.User;
import com.unibuc.event.ticketing.repository.AccountRepository;
import com.unibuc.event.ticketing.repository.OrderRepository;
import com.unibuc.event.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OrderRepository orderRepository;

    public UserCreatedDto createUser(CreateUserDto userDto) {
        var user = new User(userDto.email, userDto.password);
        var account = new Account(userDto.name);

        user.setAccount(account);
        account.setUser(user);

        userRepository.save(user);
        accountRepository.save(account);

        return new UserCreatedDto(user.getUserId());
    }

    public void updateUser(UpdateUserDto userDto) throws UserNotFoundException, AccountNotFoundException, InvalidPasswordException {
        var user = getUser(userDto.userId);
        var account = getAccount(user.getAccount().getAccountId());

        if (!Objects.equals(user.getPassword(), userDto.getOldPassword())) throw new InvalidPasswordException();

        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getNewPassword());
        account.setName(userDto.name);

        userRepository.save(user);
        accountRepository.save(account);
    }

    public User getUser(String userId) throws UserNotFoundException {
        var user = userRepository.findById(userId);
        if (user.isPresent()) return user.get();
        else throw new UserNotFoundException("User was not found");
    }

    public Account getAccount(String accountId) throws AccountNotFoundException {
        var account = accountRepository.findById(accountId);
        if (account.isPresent()) return account.get();
        else throw new AccountNotFoundException("User account was not found");
    }

    public UserInfoDto getUserInfo(String userId) throws UserNotFoundException, AccountNotFoundException {
        var user = getUser(userId);
        var account = getAccount(user.getAccount().getAccountId());

        return new UserInfoDto(user.getEmail(), account.getName());
    }
}
