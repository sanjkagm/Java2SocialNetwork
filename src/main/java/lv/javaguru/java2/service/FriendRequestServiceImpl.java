package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Pavel on 03.01.2017..
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class FriendRequestServiceImpl implements FriendRequestService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserMessageDAO userMessageDAO;
    @Autowired
    private Utils utils;

    public boolean addFriend (String myId,String userId) {

        if (userDAO.checkUserPending(utils.stringToLong(myId),utils.stringToLong(userId)) ||
                userDAO.checkUserFriend(utils.stringToLong(myId),utils.stringToLong(userId))) {
            return false;
        }

        User sender = userDAO.getById(utils.stringToLong(myId));
        User recipient = userDAO.getById(utils.stringToLong(userId));
        UserMessage friendRequest =
                utils.createUserMessageByBuilder("0",sender.getUsername(),recipient.getUsername(),
                        "Friend request from " + sender.getUsername()
                                + ". <a href='/friend/accept/" + myId + "/"+ userId + "/"
                                + userDAO.addFriendRequest(utils.stringToLong(myId),utils.stringToLong(userId))
                                + "'>Click here to accept</a>",new Date(), false);
        userMessageDAO.create(friendRequest);
        return true;


    }

    public String acceptFriendRequest (Long myId,String userId) {
        String message;

        if (userDAO.checkUserFriend(myId,utils.stringToLong(userId)))
            message = "already";
        else {
            if  (userDAO.acceptFriendRequest(myId, utils.stringToLong(userId)) > 0) {
                message = "accepted";



                User sender = userDAO.getById(myId);
                User recipient = userDAO.getById(utils.stringToLong(userId));

                UserMessage friendRequest =
                        utils.createUserMessageByBuilder("0",sender.getUsername(),recipient.getUsername(),
                                "Invitation accepted. You are now friends with " + sender.getUsername(),new Date(), false);
                userMessageDAO.create(friendRequest);


            } else {
                message = "expired";
            }
        }
        return message;
    }

    public boolean removeFriend (String myId,String userId) {


        if (userDAO.checkUserFriend(utils.stringToLong(myId),utils.stringToLong(userId))) {
            utils.sendMessage(utils.stringToLong(myId),utils.stringToLong(userId),"remove");

        } else if (userDAO.checkUserPending(utils.stringToLong(myId),utils.stringToLong(userId))) {
            utils.sendMessage(utils.stringToLong(myId),utils.stringToLong(userId),"cancel");
        }

        userDAO.removeFriend(utils.stringToLong(myId),utils.stringToLong(userId));
        return false;

    }


    public Integer readMsg (String msgId) {
        return userMessageDAO.readMsg(utils.stringToLong(msgId));
    }
}
