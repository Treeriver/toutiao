package com.nowcoder.service;

import com.nowcoder.dao.CommentDAO;
import com.nowcoder.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
@Service
public class CommentService {
    @Autowired
    private CommentDAO commentDAO;

    public List<Comment> getCommentsByEnity(int entityId,int entityType){
        return commentDAO.selectByEntity(entityId,entityType);
    }
    public int addComment(Comment comment){
        return commentDAO.addComment(comment);
    }
    public int getCommentCount(int entityId,int entityType){
        return commentDAO.getCommentCount(entityId,entityType);
    }

    public void deleteComment(int entityId,int entityType){
        commentDAO.updateStatus(entityId,entityType,1);
    }
}