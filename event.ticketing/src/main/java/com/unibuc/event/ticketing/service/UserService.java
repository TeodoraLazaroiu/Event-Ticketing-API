package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.dto.user.CreateUserDto;
import com.unibuc.event.ticketing.dto.order.OrderInfoDto;
import com.unibuc.event.ticketing.dto.user.UpdateUserDto;
import com.unibuc.event.ticketing.dto.user.UserCreatedDto;
import com.unibuc.event.ticketing.dto.user.UserInfoDto;
import com.unibuc.event.ticketing.exception.AccountNotFoundException;
import com.unibuc.event.ticketing.exception.InvalidPasswordException;
import com.unibuc.event.ticketing.exception.OrderNotFoundException;
import com.unibuc.event.ticketing.exception.UserNotFoundException;
import com.unibuc.event.ticketing.model.Account;
import com.unibuc.event.ticketing.model.Order;
import com.unibuc.event.ticketing.model.Ticket;
import com.unibuc.event.ticketing.model.User;
import com.unibuc.event.ticketing.repository.AccountRepository;
import com.unibuc.event.ticketing.repository.OrderRepository;
import com.unibuc.event.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
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
        var account = accountService.getAccount(user.getAccount().getAccountId());

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
        else throw new UserNotFoundException();
    }

    public UserInfoDto getUserInfo(String userId) throws UserNotFoundException, AccountNotFoundException {
        var user = getUser(userId);
        var account = accountService.getAccount(user.getAccount().getAccountId());

        return new UserInfoDto(user.getEmail(), account.getName());
    }

    public List<OrderInfoDto> getUserOrders(String userId) throws UserNotFoundException, AccountNotFoundException, OrderNotFoundException {
        var user = getUser(userId);
        var account = accountService.getAccount(user.getAccount().getAccountId());

        var orders = account.getOrders();
        var ordersList = new ArrayList<OrderInfoDto>();

        for (Order order: orders) {
            var tickets = order.getTickets();
            int ticketsNumber = tickets.size();
            Integer orderPrice = tickets.stream().mapToInt(Ticket::getPrice).sum();

            String eventId = null;
            if (ticketsNumber != 0) {
                eventId = tickets.get(0).getEvent().getEventId();
            }
            var orderInfo = new OrderInfoDto(order.getOrderNumber(), ticketsNumber, orderPrice, eventId);
            ordersList.add(orderInfo);
        }

        return ordersList;
    }
}
