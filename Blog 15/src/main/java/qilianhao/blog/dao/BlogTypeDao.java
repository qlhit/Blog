package qilianhao.blog.dao;

import java.util.List;
import java.util.Map;

import qilianhao.blog.entity.BlogType;
import qilianhao.blog.entity.Link;


/**
 * 博客类型Dao接口
 * @author Administrator
 *
 */
public interface BlogTypeDao {

	/**
	 * 查询所有博客类型，以及对应的博客数量
	 * @return
	 */
	public List<BlogType> countList();
	
	/**
	 * 通过id查找博客类型实体
	 * @param id
	 * @return
	 */
	public BlogType findById(Integer id);
	
	/**
	 * 分页查询博客类别信息
	 * @param map
	 * @return
	 */
	public List<BlogType> list(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);

	/**
	 * 添加博客类别信息
	 * @param BlogType
	 * @return
	 */
	public Integer add(BlogType blogType);
	/**
	 * 删除博客类别信息
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
	/**
	 * 更新博客类别信息
	 * @param blogType
	 * @return
	 */
	public Integer update(BlogType blogType);
}
