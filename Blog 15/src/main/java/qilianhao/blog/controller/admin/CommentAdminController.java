package qilianhao.blog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qilianhao.blog.entity.Blog;
import qilianhao.blog.entity.Comment;
import qilianhao.blog.entity.Link;
import qilianhao.blog.entity.PageBean;
import qilianhao.blog.service.CommentService;
import qilianhao.blog.util.ResponseUtil;
import qilianhao.blog.util.StringUtil;

/**
 * CommentAdminController层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
	@Resource
	private CommentService commentService;
	/**
	 * 分页查询评论信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAdmin")
	public String listAdmin(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Comment comment,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		System.out.println("++++++wwwwwwwwwwwwwwwwwwwwwww+++++"+comment.getContent());
		map.put("content", StringUtil.formatLike(comment.getContent()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Comment> commentList=commentService.listAdmin(map);
		Long total=commentService.getTotal(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(commentList, jsonConfig);
		result.put("rows", jsonArray);
		System.out.println("++++++++++++++++++++"+jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 分页查询评论待审核评论信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listReview")
	public String listReview(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Comment comment,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		System.out.println("++++++8888888888888888888888++++"+comment.getContent());
		map.put("content", StringUtil.formatLike(comment.getContent()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Comment> commentList=commentService.listReview(map);
		Long total=commentService.getTotal(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(commentList, jsonConfig);
		result.put("rows", jsonArray);
		System.out.println("待审核评论信息测试"+jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 评论信息删除
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			commentService.delete(Integer.parseInt(idsStr[i]));
			//blogIndex.deleteIndex(idsStr[i]);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 评论信息审核
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/review")
	public String review(@RequestParam(value="ids")String ids,@RequestParam(value="state")Integer state,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Comment comment=new Comment();
			comment.setState(state);
			comment.setId(Integer.parseInt(idsStr[i]));
			commentService.update(comment);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 通过Id查找评论
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Comment comment=commentService.findById(Integer.parseInt(id));
		//System.out.println(blog.getLinkName());
		JSONObject result=JSONObject.fromObject(comment);
		//System.out.println(result.toString());
		ResponseUtil.write(response, result);
		return null;
	}	

}
