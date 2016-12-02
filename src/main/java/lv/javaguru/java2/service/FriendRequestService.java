package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.database.jdbc.UserMessageDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserMessage;

import java.util.Date;

/**
 * Created by Pavel on 21.11.2016..
 */
public class FriendRequestService extends Utils{

    public boolean addFriend (String myId,String userId) {
        UserDAO userDAOObj = new UserDAOImpl();
        UserMessageDAO userMessageObj = new UserMessageDAOImpl();
        Utils utils = new Utils();
        User sender = userDAOObj.getById(stringToLong(myId));
        User recipient = userDAOObj.getById(stringToLong(userId));
        UserMessage friendRequest =
                utils.createUserMessageByBuilder("0",sender.getUsername(),recipient.getUsername(),
                        "Friend request from " + sender.getUsername()
                                + ". <a href='/friend/accept/" + myId + "/"+ userId + "/"
                                + userDAOObj.addFriendRequest(stringToLong(myId),stringToLong(userId))
                                + "'>Click here to accept</a>",new Date(), false);
        userMessageObj.create(friendRequest);
        //userDAOObj.addFriendRequest(stringToLong(myId),stringToLong(userId));
        return true;

    }

    public String acceptFriendRequest (Long myId,String userId) {
        String message;
        UserDAO userDAOObj = new UserDAOImpl();
        if (userDAOObj.checkUserFriend(myId,stringToLong(userId)))
            message = "already";
        else {
            if  (userDAOObj.acceptFriendRequest(myId, stringToLong(userId)) > 0) {
                message = "accepted";

                UserMessageDAO userMessageObj = new UserMessageDAOImpl();
                Utils utils = new Utils();
                User sender = userDAOObj.getById(myId);
                User recipient = userDAOObj.getById(stringToLong(userId));

                UserMessage friendRequest =
                        utils.createUserMessageByBuilder("0",sender.getUsername(),recipient.getUsername(),
                                "Invitation accepted. You are now friends with " + sender.getUsername(),new Date(), false);
                userMessageObj.create(friendRequest);


            } else {
                message = "expired";
            }
        }
        return message;
    }

    public boolean removeFriend (String myId,String userId) {
        UserDAO userDAOObj = new UserDAOImpl();

        if (userDAOObj.checkUserFriend(stringToLong(myId),stringToLong(userId))) {
            sendMessage(stringToLong(myId),stringToLong(userId),"remove");

        } else if (userDAOObj.checkUserPending(stringToLong(myId),stringToLong(userId))) {
            sendMessage(stringToLong(myId),stringToLong(userId),"cancel");
        }
        userDAOObj.removeFriend(stringToLong(myId),stringToLong(userId));
        return false;

    }

    public boolean cancelInvitation (String myId,String userId) {
        UserDAO userDAOObj = new UserDAOImpl();

        if (userDAOObj.checkUserFriend(stringToLong(myId),stringToLong(userId))) {
            sendMessage(stringToLong(myId),stringToLong(userId),"remove");

        } else if (userDAOObj.checkUserPending(stringToLong(myId),stringToLong(userId))) {
            sendMessage(stringToLong(myId),stringToLong(userId),"cancel");
        }
        userDAOObj.removeFriend(stringToLong(myId),stringToLong(userId));
        return false;

    }

}
