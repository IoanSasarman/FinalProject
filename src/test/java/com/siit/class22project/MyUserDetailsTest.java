package com.siit.class22project;

import com.siit.class22project.model.User;
import com.siit.class22project.service.MyUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MyUserDetailsTest {

    private MyUserDetails myUserDetails;
    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = mock(User.class);
        when(mockUser.getUsername()).thenReturn("testUser");
        when(mockUser.getPassword()).thenReturn("testPassword");

        myUserDetails = new MyUserDetails(mockUser);
    }

    @Test
    void getPassword_ShouldReturnUserPassword() {
        String password = myUserDetails.getPassword();

        assertEquals("testPassword", password);
    }

    @Test
    void getUsername_ShouldReturnUserUsername() {
        String username = myUserDetails.getUsername();

        assertEquals("testUser", username);
    }

    @Test
    void isAccountNonExpired_ShouldReturnTrue() {
        boolean isAccountNonExpired = myUserDetails.isAccountNonExpired();

        assertTrue(isAccountNonExpired);
    }

    @Test
    void isAccountNonLocked_ShouldReturnTrue() {
        boolean isAccountNonLocked = myUserDetails.isAccountNonLocked();

        assertTrue(isAccountNonLocked);
    }

    @Test
    void isCredentialsNonExpired_ShouldReturnTrue() {
        boolean isCredentialsNonExpired = myUserDetails.isCredentialsNonExpired();

        assertTrue(isCredentialsNonExpired);
    }

    @Test
    void isEnabled_ShouldReturnTrue() {
        boolean isEnabled = myUserDetails.isEnabled();

        assertTrue(isEnabled);
    }
}
