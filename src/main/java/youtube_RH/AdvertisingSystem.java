package youtube_RH;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="AdvertisingSystem_table")
public class AdvertisingSystem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long adId;
    private String adName;
    private Integer adMinute;
    private Integer adCnt;
    private String adStatus;

    @PostPersist
    public void onPostPersist(){
        AdRegistered adRegistered = new AdRegistered();
        BeanUtils.copyProperties(this, adRegistered);
        adRegistered.publishAfterCommit();


    }

    @PreUpdate
    public void onPreUpdate(){
        AdModified adModified = new AdModified();
        BeanUtils.copyProperties(this, adModified);
        adModified.publishAfterCommit();


    }

    @PreRemove
    public void onPreRemove(){
        AdDeleted adDeleted = new AdDeleted();
        BeanUtils.copyProperties(this, adDeleted);
        adDeleted.publishAfterCommit();


    }


    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }
    public Integer getAdMinute() {
        return adMinute;
    }

    public void setAdMinute(Integer adMinute) {
        this.adMinute = adMinute;
    }
    public Integer getAdCnt() {
        return adCnt;
    }

    public void setAdCnt(Integer adCnt) {
        this.adCnt = adCnt;
    }
    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }




}
