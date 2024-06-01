package csc340project.example.springio.GroupListings;

public class GroupListingError {
    public enum ErrorType {
        JOIN_FULL("The group you have attempted to join is full"),
        JOIN_INGROUP("You are already registered in this group"),
        UNDEFINED("Undefined Error");

        private String message;

        private ErrorType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    private String errMessage;

    public GroupListingError(ErrorType errorType) {
        this.errMessage = errorType.getMessage();
    }

    public String getErrorMessage() {
        return this.errMessage;
    }
}
