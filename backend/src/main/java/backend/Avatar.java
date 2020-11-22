package backend;

public class Avatar {

    private final String imageFileName;
    private final String avatarName;

    Avatar( String imageFileName, String avatarName ) {
        this.imageFileName = imageFileName;
        this.avatarName = avatarName;
    }

    public void paintAvatar() {} // draw the avatar
    public void resizePic() {} // resize the avatar image
}
