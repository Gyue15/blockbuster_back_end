package nju.blockbuster.service.impl;

import nju.blockbuster.entities.Follow;
import nju.blockbuster.entities.FollowPK;
import nju.blockbuster.entities.Message;
import nju.blockbuster.entities.User;
import nju.blockbuster.models.MessageModel;
import nju.blockbuster.models.UserModel;
import nju.blockbuster.repository.FollowRepository;
import nju.blockbuster.repository.MessageRepository;
import nju.blockbuster.repository.UserRepository;
import nju.blockbuster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.ResultMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

    @Autowired
    MessageRepository messageRepository;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public UserModel findUser(String email) {
        UserModel userModel = new UserModel();
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            return userModel;
        }
        userModel.setAvatar(user.getAvatar());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setUsername(user.getUsername());
        return userModel;
    }

    @Override
    public ResultMessage addUser(UserModel userModel) {
        User user = new User();
        user.setAvatar(userModel.getAvatar());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setUsername(userModel.getUsername());
        user = userRepository.saveAndFlush(user);
        System.out.println("!!!!!!!!!!!!in save: " + user.toString());
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyUser(UserModel userModel) {
        User user = userRepository.findUserByEmail(userModel.getEmail());
        if (user == null || user.getEmail() == null) {
            return ResultMessage.FAILURE;
        }

        if (userModel.getAvatar() != null) {
            user.setAvatar(userModel.getAvatar());
        }
        if (userModel.getUsername() != null) {
            user.setUsername(userModel.getUsername());
        }
        if (userModel.getPassword() != null) {
            user.setPassword(userModel.getPassword());
        }

        user = userRepository.saveAndFlush(user);
        return (user == null || user.getEmail() == null) ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public UserModel getUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        UserModel userModel = new UserModel();
        if (user == null) {
            return userModel;
        }
        userModel.setAvatar(user.getAvatar());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setUsername(user.getUsername());
        return userModel;
    }

    @Override
    public List<UserModel> getFollowedUser(String email) {
        List<Follow> followList = followRepository.findByFollowPK_FollowerEmail(email);
        List<UserModel> userModelList = new ArrayList<>();
        for (Follow follow: followList) {
            User user = userRepository.findUserByEmail(follow.getFollowPK().getFollowedEmail());
            UserModel userModel = new UserModel();
            if (user == null) {
                continue;
            }
            userModel.setAvatar(user.getAvatar());
            userModel.setEmail(user.getEmail());
            userModel.setPassword(user.getPassword());
            userModel.setUsername(user.getUsername());
            userModelList.add(userModel);
        }
        return userModelList;
    }

    @Override
    public ResultMessage follow(String followerEmail, String followedEmail) {
        if (isFollow(followerEmail, followedEmail)) {
            return ResultMessage.FAILURE;
        }
        FollowPK followPK = new FollowPK();
        followPK.setFollowedEmail(followedEmail);
        followPK.setFollowerEmail(followerEmail);
        Follow follow = new Follow();
        follow.setFollowPK(followPK);
        followRepository.save(follow);

        // 存储消息
        User follower = userRepository.findUserByEmail(followerEmail);
        Message message = new Message();
        message.setUsername(follower.getUsername());
        message.setText("关注了你");
        message.setUsername(follower.getUsername());
        message.setOwner(followedEmail);
        message.setEmail(followerEmail);
        message.setFlag(false);
        message.setAvatar(follower.getAvatar());
        message.setDate(new Date());
        messageRepository.save(message);

        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage unfollow(String followerEmail, String followedEmail) {
        if (!isFollow(followerEmail, followedEmail)) {
            return ResultMessage.FAILURE;
        }
        FollowPK followPK = new FollowPK();
        followPK.setFollowerEmail(followerEmail);
        followPK.setFollowedEmail(followedEmail);
        Follow follow = new Follow();
        follow.setFollowPK(followPK);
        followRepository.delete(follow);
        return ResultMessage.SUCCESS;
    }

    @Override
    public Boolean isFollow(String followerEmail, String followedEmail) {
        FollowPK followPK = new FollowPK();
        followPK.setFollowedEmail(followedEmail);
        followPK.setFollowerEmail(followerEmail);
        Follow follow = followRepository.findByFollowPK(followPK);

        return follow != null && follow.getFollowPK() != null && follow.getFollowPK().getFollowedEmail() != null;
    }

    @Override
    public List<MessageModel> getMessageList(String email) {
        List<Message> messageList = messageRepository.findByOwnerOrderByDateDesc(email);
        List<MessageModel> messageModels = new ArrayList<>();
        for (Message message: messageList) {
            MessageModel messageModel = new MessageModel();
            messageModel.setAvatar(message.getAvatar());
            messageModel.setEmail(message.getEmail());
            messageModel.setFlag(message.getFlag());
            message.setMid(message.getMid());
            messageModel.setText(message.getText());
            messageModel.setUsername(message.getUsername());
            messageModel.setDate(message.getDate());
            messageModel.setFormatDate(dateFormat.format(message.getDate()));
            messageModels.add(messageModel);
        }

        // 将所有消息改为已读
        List<Message> toSave = new ArrayList<>();
        for (Message message: messageList) {
            if (!message.getFlag()) {
                message.setFlag(true);
                toSave.add(message);
            }
        }
        messageRepository.save(toSave);

        return messageModels;
    }

    @Override
    public Boolean hasNewMessage(String email) {
        return !messageRepository.findByOwnerAndFlag(email, false).isEmpty();
    }

}
