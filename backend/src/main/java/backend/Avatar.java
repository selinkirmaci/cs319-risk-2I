package backend;

import java.io.Serializable;

public class Avatar implements Serializable {

    private final String imageFileName;

    Avatar( String imageFilePath ) {
        this.imageFileName = imageFilePath;
    }


    public String getImageFileName(){return imageFileName;}
    public void paintAvatar() {} // draw the avatar
    public void resizePic() {} // resize the avatar image
}
