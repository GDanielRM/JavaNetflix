package org.example.dani.enums;

public class Enums {
    public enum UserType {
        ADMIN(1,"User administrator"),
        USER(2,"Normal user");

        private final int code;
        private final String description;

        UserType(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }
}
