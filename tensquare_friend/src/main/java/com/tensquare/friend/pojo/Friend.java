package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/10/10
 */
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable {
    @Id
    private String userId;
    @Id
    private String friendId;
    private String isLike;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Friend friend = (Friend) o;
        return Objects.equals(userId, friend.userId) &&
                Objects.equals(friendId, friend.friendId) &&
                Objects.equals(isLike, friend.isLike);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, friendId, isLike);
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userId='" + userId + '\'' +
                ", friendId='" + friendId + '\'' +
                ", isLike='" + isLike + '\'' +
                '}';
    }
}
