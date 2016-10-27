package search.yazhou.com.courserasearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by umeng on 10/26/16.
 */

public class Model {

    @SerializedName("id")
    private String Id = "";

    @SerializedName(value = "photoUrl",alternate = {"logo","photoUrl"})
    private String photoUrl = "";

    @SerializedName("discription")
    private String discription = "";

    private String universityName = "";

    private String partnerId = "";

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

}
