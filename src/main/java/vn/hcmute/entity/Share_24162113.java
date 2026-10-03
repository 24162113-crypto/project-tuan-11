package vn.hcmute.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "Share")
@Table(name = "Shares")
public class Share_24162113 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShareId")
    private Integer shareId;

    @Column(name = "Emails", length = 50)
    private String emails;

    @Column(name = "SharedDate")
    private LocalDate sharedDate;

    @ManyToOne
    @JoinColumn(name = "Username")
    private User_24162113 user;

    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video_24162113 video;

    public Share_24162113() {
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public LocalDate getSharedDate() {
        return sharedDate;
    }

    public void setSharedDate(LocalDate sharedDate) {
        this.sharedDate = sharedDate;
    }

    public User_24162113 getUser() {
        return user;
    }

    public void setUser(User_24162113 user) {
        this.user = user;
    }

    public Video_24162113 getVideo() {
        return video;
    }

    public void setVideo(Video_24162113 video) {
        this.video = video;
    }
}
