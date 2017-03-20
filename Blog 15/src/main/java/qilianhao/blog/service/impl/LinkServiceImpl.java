package qilianhao.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qilianhao.blog.dao.LinkDao;
import qilianhao.blog.entity.Link;
import qilianhao.blog.service.LinkService;


/**
 * 友情链接Service实现类
 * @author Administrator
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkDao linkDao;

	public List<Link> list(Map<String, Object> map) {
		return linkDao.list(map);
	}
	public Long getTotal(Map<String, Object> map) {
		return linkDao.getTotal(map);
	}
	public Link findById(Integer id) {
		return linkDao.findById(id);
	}
	public Integer delete(Integer id) {
		return linkDao.delete(id);
	}
	public Integer add(Link link) {
		return linkDao.add(link);
	}
	public Integer update(Link link) {
		return linkDao.update(link);
	}
	

	
	
	

}
