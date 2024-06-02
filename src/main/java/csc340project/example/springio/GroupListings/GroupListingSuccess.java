package csc340project.example.springio.GroupListings;

public class GroupListingSuccess {
    public enum SuccessType {
        JOIN("You have successfully joined the selected group"),
        UPDATE("You have successfully updated the selected group"),
        CREATE("You have successfully created your group"),
        LEAVE("You have successfully left the selected group");

        private String message;

        private SuccessType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    private String successMessage;

    public GroupListingSuccess(SuccessType successType) {
        this.successMessage = successType.getMessage();
    }

    public String getSuccessMessage() {
        return this.successMessage;
    }
}
