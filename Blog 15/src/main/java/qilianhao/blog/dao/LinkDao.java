package qilianhao.blog.dao;

import java.util.List;
import java.util.Map;

import qilianhao.blog.entity.Blog;
import qilianhao.blog.entity.Link;


/**
 * 友情链接Dao接口
 * @author Administrator
 *
 */
public interface LinkDao {

	/**
	 * 查找友情链接信息
	 * @param map
	 * @return
	 */
	public List<Link> list(Map<String,Object> map);
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	/**
	 * 根据id查找链接
	 * @param id
	 * @return
	 */
	public Link findById(Integer id);

	/**
	 * 添加链接信息
	 * @param link
	 * @return
	 */
	public Integer add(Link link);
	/**
	 * 更新链接信息
	 * @param link
	 * @return
	 */
	public Integer update(Link link);
	/**
	 * 删除链接信息
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
