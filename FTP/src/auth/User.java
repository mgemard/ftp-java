package auth;

public class User {
    
    private String login;
    
    private String password;
    
    private String directory;

    public User(String login, String password, String directory) {
        this.login = login;
        this.password = password;
        this.directory = directory;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

}
