package qilianhao.blog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import qilianhao.blog.entity.BlogType;
import qilianhao.blog.entity.PageBean;
import qilianhao.blog.service.BlogTypeService;
import qilianhao.blog.util.ResponseUtil;
import qilianhao.blog.util.StringUtil;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 管理员博客类别Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

	@Resource
	private BlogTypeService blogTypeService;
	
	/**
	 * 分页查询博客类别信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,BlogType blogType,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();

		System.out.println("++++++++++++++++++++"+blogType.getTypeName());
		map.put("typeName", StringUtil.formatLike(blogType.getTypeName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<BlogType> blogTypeList=blogTypeService.list(map);
		Long total=blogTypeService.getTotal(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(blogTypeList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 博客类别信息删除
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			blogTypeService.delete(Integer.parseInt(idsStr[i]));
			//blogIndex.deleteIndex(idsStr[i]);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 通过Id查找博客类别
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		BlogType blogType=blogTypeService.findById(Integer.parseInt(id));
		//System.out.println(blog.getLinkName());
		JSONObject result=JSONObject.fromObject(blogType);
		//System.out.println(result.toString());
		ResponseUtil.write(response, result);
		return null;
	}	
	/**
	 * 添加或者修改博客类别信息
	 * @param blogType
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(BlogType blogType,HttpServletResponse response)throws Exception{
		int resultTotal=0; 
		if(blogType.getId()==null){
			resultTotal=blogTypeService.add(blogType);
			//blogIndex.addIndex(blog);
		}else{
			resultTotal=blogTypeService.update(blogType);
			//blogIndex.updateIndex(blog);
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
}
