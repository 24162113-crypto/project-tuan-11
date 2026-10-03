package vn.hcmute.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "Favorite")
@Table(name = "Favorites")
public class Favorite_24162113 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteId")
    private Integer favoriteId;

    @Column(name = "LikedDate")
    private LocalDate likedDate;

    @ManyToOne
    @JoinColumn(name = "Username")
    private User_24162113 user;

    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video_24162113 video;

    public Favorite_24162113() {
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public LocalDate getLikedDate() {
        return likedDate;
    }

    public void setLikedDate(LocalDate likedDate) {
        this.likedDate = likedDate;
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
