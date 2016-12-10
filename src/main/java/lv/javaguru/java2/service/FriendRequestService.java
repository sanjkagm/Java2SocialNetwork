package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Pavel on 21.11.2016..
 */
@Service
public class FriendRequestService extends Utils{

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserMessageDAO userMessageDAO;

    public boolean addFriend (String myId,String userId) {

        if (userDAO.checkUserPending(stringToLong(myId),stringToLong(userId)) ||
                userDAO.checkUserFriend(stringToLong(myId),stringToLong(userId))) {
            return false;
        }

        User sender = userDAO.getById(stringToLong(myId));
        User recipient = userDAO.getById(stringToLong(userId));
        UserMessage friendRequest =
                createUserMessageByBuilder("0",sender.getUsername(),recipient.getUsername(),
                        "Friend request from " + sender.getUsername()
                                + ". <a href='/friend/accept/" + myId + "/"+ userId + "/"
                                + userDAO.addFriendRequest(stringToLong(myId),stringToLong(userId))
                                + "'>Click here to accept</a>",new Date(), false);
        userMessageDAO.create(friendRequest);
        return true;


    }

    public String acceptFriendRequest (Long myId,String userId) {
        String message;

        if (userDAO.checkUserFriend(myId,stringToLong(userId)))
            message = "already";
        else {
            if  (userDAO.acceptFriendRequest(myId, stringToLong(userId)) > 0) {
                message = "accepted";



                User sender = userDAO.getById(myId);
                User recipient = userDAO.getById(stringToLong(userId));

                UserMessage friendRequest =
                        createUserMessageByBuilder("0",sender.getUsername(),recipient.getUsername(),
                                "Invitation accepted. You are now friends with " + sender.getUsername(),new Date(), false);
                userMessageDAO.create(friendRequest);


            } else {
                message = "expired";
            }
        }
        return message;
    }

    public boolean removeFriend (String myId,String userId) {


        if (userDAO.checkUserFriend(stringToLong(myId),stringToLong(userId))) {
            sendMessage(stringToLong(myId),stringToLong(userId),"remove");

        } else if (userDAO.checkUserPending(stringToLong(myId),stringToLong(userId))) {
            sendMessage(stringToLong(myId),stringToLong(userId),"cancel");
        }

        userDAO.removeFriend(stringToLong(myId),stringToLong(userId));
        return false;

    }


    public Integer readMsg (String msgId) {
        return userMessageDAO.readMsg(stringToLong(msgId));
    }

}
