package qilianhao.blog.service;

import java.util.List;
import java.util.Map;

import qilianhao.blog.entity.Blog;
import qilianhao.blog.entity.Comment;
import qilianhao.blog.entity.Link;


/**
 * 评论Service接口
 * @author Administrator
 *
 */
public interface CommentService {

	/**
	 * 查询用户评论信息
	 * @param map
	 * @return
	 */
	
	public List<Comment> list(Map<String,Object> map);
	/**
	 * 分页查询用户评论信息
	 * @param map
	 * @return
	 */
	public List<Comment> listAdmin(Map<String,Object> map);
	/**
	 * 查询用户评论待审核信息
	 * @param map
	 * @return
	 */
	public List<Comment> listReview(Map<String,Object> map);
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	/**
	 * 根据id查找链接实体
	 * @param id
	 * @return
	 */
	public Comment findById(Integer id);
	/**
	 * 删除链接信息
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
	/**
	 * 审核评论信息
	 * @param comment
	 * @return
	 */
	public int update(Comment comment);
}
