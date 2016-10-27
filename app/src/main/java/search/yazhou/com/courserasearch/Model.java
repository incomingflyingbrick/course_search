package search.yazhou.com.courserasearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by umeng on 10/26/16.
 */

public class Model {

    @SerializedName("id")
    private String Id = "";

    @SerializedName(value = "photoUrl",alternate = {"logo"})
    private String photoUrl = "";

    @SerializedName("description")
    private String discription = "";

    private String universityName = "";

    @SerializedName("partnerIds")
    private String[] partnerIds;

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

    public String[] getPartnerId() {
        return partnerIds;
    }

    public void setPartnerId(String[] partnerId) {
        this.partnerIds = partnerId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

}
