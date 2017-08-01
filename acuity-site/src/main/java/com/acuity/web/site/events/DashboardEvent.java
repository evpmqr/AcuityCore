package com.acuity.web.site.events;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */
public class DashboardEvent {

    public static final class UserLoginRequestedEvent {

        private final String userName, password;

        public UserLoginRequestedEvent(final String username, final String password) {
            this.userName = username;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }

    public static final class ViewChange {

        private String view;

        public ViewChange(String view) {
            this.view = view;
        }

        public String getView() {
            return view;
        }
    }

    public static class CloseOpenWindowsEvent {
    }

    public static class ProfileUpdatedEvent {
    }

    public static class BrowserResizeEvent {

    }

    public static class UserLoggedOutEvent {

    }

}
