package xust.ebs.interceptor;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import xust.ebs.dao.AdminDao;
import xust.ebs.entity.Admin;
import xust.ebs.util.EbsResult;

/**
 * 用于session验证拦截
 * @author Wuyk
 *
 */
public class AdminInterceptor implements HandlerInterceptor {
	
	@Resource
	private AdminDao adminDao;

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		String adminToken = req.getParameter("adminToken");
		String adminName = req.getParameter("adminName");
		
		Admin admin = adminDao.findByAdminName(adminName);
		if(admin == null) {
//			System.out.println("false");
			ServletOutputStream os = res.getOutputStream();
			EbsResult<Object> result = new EbsResult<Object>();
			result.setStatus(-1);
			JSONObject json = JSONObject.fromObject(result);
			String jsonStr = json.toString();
			os.print(jsonStr);
			os.flush();
			os.close();
			return false;
		}
		if(adminToken != null && !adminToken.equals(admin.getAdmin_token())) {
//			System.out.println("false");
			ServletOutputStream os = res.getOutputStream();
			EbsResult<Object> result = new EbsResult<Object>();
			result.setStatus(-1);
			JSONObject json = JSONObject.fromObject(result);
			String jsonStr = json.toString();
			os.print(jsonStr);
			os.flush();
			os.close();
			return false;
		}
//		System.out.println("success");
		return true;
	}

}
