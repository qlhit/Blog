package qilianhao.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qilianhao.blog.dao.BloggerDao;
import qilianhao.blog.entity.Blogger;
import qilianhao.blog.service.BloggerService;


/**
 * 博主Service实现类
 * @author Administrator
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerDao bloggerDao;
	
	public Blogger getByUserName(String userName) {
		return bloggerDao.getByUserName(userName);
	}

	public Blogger find() {
		return bloggerDao.find();
	}

	public Integer update(Blogger blogger) {
		return bloggerDao.update(blogger);
	}

}
