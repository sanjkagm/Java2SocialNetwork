package lv.javaguru.java2.service;



/**
 * Created by Pavel on 21.11.2016..
 */

public interface FriendRequestService {
    boolean addFriend (String myId,String userId);
    String acceptFriendRequest (Long myId,String userId);
    boolean removeFriend (String myId,String userId);
    Integer readMsg (String msgId);


}
