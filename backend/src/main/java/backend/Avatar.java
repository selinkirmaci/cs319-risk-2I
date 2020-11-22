package backend;

public class Avatar {

    private final String imageFileName;

    Avatar( String imageFilePath ) {
        this.imageFileName = imageFilePath;
    }

    public void paintAvatar() {} // draw the avatar
    public void resizePic() {} // resize the avatar image
}
