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
import qilianhao.blog.entity.Link;
import qilianhao.blog.entity.PageBean;
import qilianhao.blog.lucene.BlogIndex;
import qilianhao.blog.service.LinkService;
import qilianhao.blog.util.ResponseUtil;
import qilianhao.blog.util.StringUtil;

/**
 * 管理员博主Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/link")
public class linkController {
	@Resource
	private LinkService linkService;
	
	/**
	 * 添加或者修改链接信息
	 * @param link
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Link link,HttpServletResponse response)throws Exception{
		int resultTotal=0; 
		String no1="<a style=\"text-decoration: none;\" href=\"";
		String no2="\" target=\"_blank\">";
		String no3="</a>";
		String linkName=null;
		if(link.getId()==null){
			resultTotal=linkService.add(link);
			System.out.println("++++++++++++链接添加操作测试+++++++++++");
		}else{
			linkName=no1+link.getLinkUrl()+no2+link.getLinkName()+no3;
			System.out.println("友情链接名称测试"+linkName);
			link.setLinkName(linkName);
			resultTotal=linkService.update(link);
			System.out.println("++++++++++++链接修改操作测试+++++++++++");

		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 分页查询友情链接信息
	 * @param page
	 * @param rows
	 * @param link
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Link link,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		System.out.println("++++++++++++++++++++"+link.getLinkName());
		map.put("linkName", StringUtil.formatLike(link.getLinkName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Link> blogList=linkService.list(map);
		Long total=linkService.getTotal(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(blogList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 链接信息删除
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			linkService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 通过Id查找链接
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Link link=linkService.findById(Integer.parseInt(id));
		System.out.println("链接修改测试+++++++++++++++++"+id);
		JSONObject result=JSONObject.fromObject(link);
		//System.out.println(result.toString());
		ResponseUtil.write(response, result);
		return null;
	}	
		
		

}
