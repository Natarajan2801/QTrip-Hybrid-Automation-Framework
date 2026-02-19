package com.qtrip.models;

/**
 * User model for API requests/responses.
 *
 * @author Natarajan M
 */
public class User {

    private String id;
    private String email;
    private String password;
    private String confirmpassword;
    private String token;
    private String firstName;
    private String lastName;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.confirmpassword = password;
    }

    // Builder pattern for fluent creation
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmpassword() { return confirmpassword; }
    public void setConfirmpassword(String confirmpassword) { this.confirmpassword = confirmpassword; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", email='" + email + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
    }

    /**
     * Builder class for User.
     */
    public static class UserBuilder {
        private final User user = new User();

        public UserBuilder email(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder password(String password) {
            user.setPassword(password);
            user.setConfirmpassword(password);
            return this;
        }

        public UserBuilder firstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public UserBuilder lastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public User build() {
            return user;
        }
    }
}

