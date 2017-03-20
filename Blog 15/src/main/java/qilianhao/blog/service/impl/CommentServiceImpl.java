package qilianhao.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qilianhao.blog.dao.CommentDao;
import qilianhao.blog.entity.Comment;
import qilianhao.blog.service.CommentService;


/**
 * 评论Service实现类
 * @author Administrator
 *
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Resource
	private CommentDao commentDao;
	
	public List<Comment> list(Map<String, Object> map) {
		return commentDao.list(map);
	}

	public int add(Comment comment) {
		return commentDao.add(comment);
	}
	public Long getTotal(Map<String,Object> map){
		return commentDao.getTotal(map);
	}

	public Comment findById(Integer id) {
		return commentDao.findById(id);
	}

	public Integer delete(Integer id) {
		return commentDao.delete(id);
	}

	public List<Comment> listReview(Map<String, Object> map) {
		return commentDao.listReview(map);
	}

	public int update(Comment comment) {
		return commentDao.update(comment);
	}

	public List<Comment> listAdmin(Map<String, Object> map) {
		return commentDao.listAdmin(map);
	}
}
