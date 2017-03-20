package qilianhao.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qilianhao.blog.dao.BlogTypeDao;
import qilianhao.blog.entity.BlogType;
import qilianhao.blog.entity.Link;
import qilianhao.blog.service.BlogTypeService;


/**
 * 博客类型Service实现类
 * @author Administrator
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Resource
	private BlogTypeDao blogTypeDao;
	
	public List<BlogType> countList() {
		return blogTypeDao.countList();
	}

	public List<BlogType> list(Map<String, Object> map) {
		return blogTypeDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return blogTypeDao.getTotal(map);
	}

	

	public Integer delete(Integer id) {
		return blogTypeDao.delete(id);
	}

	public BlogType findById(Integer id) {
		return blogTypeDao.findById(id);
	}

	public Integer add(BlogType blogType) {
		return blogTypeDao.add(blogType);
	}

	public Integer update(BlogType blogType) {
		return blogTypeDao.update(blogType);
	}

	
}
