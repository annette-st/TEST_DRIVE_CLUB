package services;

import forms.SignInForm;
import forms.SignUpForm;
import forms.UserForm;

public interface UsersService {
    void signUp(SignUpForm form);

    UserForm signIn(SignInForm form);

    boolean isExistByCookie(String cookieValue);
}
