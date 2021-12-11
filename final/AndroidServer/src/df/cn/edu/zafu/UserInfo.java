package df.cn.edu.zafu;

public class UserInfo {
    private int id;
    private String username; // 用户名
    private String password;// 密码
    private String permission;// 权限
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPermission() {
        return permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public UserInfo(String username, String password, String permission) {
        super();
        this.username = username;
        this.password = password;
        this.permission = permission;
    }
    
    public UserInfo(int id, String username, String password, String permission) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.permission = permission;
    }
    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", username=" + username + ", password=" + password + ", permission=" + permission + "]";
    }

}
