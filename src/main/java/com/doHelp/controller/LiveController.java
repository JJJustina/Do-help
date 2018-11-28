package com.doHelp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.doHelp.model.LiveRedis;
import com.doHelp.service.LiveService;


/**
 *  @RestController和@Controller是我们在前端控制层用在类上面的一个注解，这个我们大家都很熟悉。
 *  @RestController这个注解相当于@Controller和@ResponseBody的一个组合，
 *  当我们使用@RestController注解定义一个类的时候，这个类中方法传递给前端的数据会自动转换为json类型。
 *  这里需要注意@RestController只是标识类传递给前端的数据类型为json，
 *  而类中的方法如果想接受前端的数据还是需要在对应的方法参数中添加@RequestBody注解。这里一定要记住的是@RestController=@Controller+@ResponseBody，
 *  不包括@RequestBody注解，如果我们想要获取前端传递过来的json数据不管使用@RestController还是@Controller注解都要在类的方法中加上@RequestBody注解
 *  
**/

@RestController
public class LiveController {

	@Autowired
	private LiveService liveService;

	@RequestMapping("/liveList")
	//@RequestMapping主要作用是告诉spring，所有与路径相关的都被映射到home方法中，
   //可以作用于类处或方法处，主要是做了地址映射 
	public ModelAndView toLiveList(ModelMap model) {
		List<LiveRedis> list = liveService.getAll();
		model.addAttribute("liveList", list);
		return new ModelAndView("liveList");
	}

	@RequestMapping(value = "/live", method = RequestMethod.GET)
	public ModelAndView live(ModelMap model, HttpServletRequest request) {
		// 只需要获取stream name ，即可通过它访问到直播间
		String keyname = request.getParameter("keyname");
		model.addAttribute("keyname", keyname);
		return new ModelAndView("live");
	}

	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
	public ModelAndView toPublisher(ModelMap model) {
		LiveRedis liveResdis = new LiveRedis();
		model.addAttribute("live", liveResdis);
		return new ModelAndView("publisher");
	}

	@RequestMapping(value = "/publisher", method = RequestMethod.POST)
	public ModelAndView publisher(@ModelAttribute(value = "live") LiveRedis liveRedis, ModelMap model) {
		liveService.save(liveRedis);
		List<LiveRedis> list = liveService.getAll();
		model.addAttribute("liveList", list);
		return new ModelAndView("liveList");
	}
	/**   @RestController
    @RequestMapping(value="user",produces="application/json")
    public class UserController {
 @Autowired
private UserService userService;
 //根据用户Id查询用户对象
 //@param userId

@RequestMapping(value = "queryUserByUserId",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public ResponseEntity<User> queryUserByUserId(int userId) {
	// test
	User user = userService.queryUserByUserId(userId);
	
	return new ResponseEntity<User>(user,HttpStatus.OK);
}

}
@RestController表明该类为控制类，在类处使用@RequestMapping使用value指定路径，produces指定请求数据格式，

 代码解释：
在方法处通过@RequestMapping的value指定路径，method指定请求方式。
请求访问路径: http://ip:port/类处路径/方法处路径，eg:http://localhost:8080/user/queryUserByUserId,
如果类处没有使用@RequestMapping注解，请求路径中对应省掉类处路径，直接到方法处路径。
在使用spring,spring mvc,spring boot时，我们很想严格区分某个注解是属于什么技术范畴，

例2：

 在HelloController类下：
@Controller
@RequestMapping("home")
public class HelloController{

@RequestMapping(value="/hey" ,method=RequestMethod.GET)
 public String sayHey(){
 return "hello";

 }
}
运行主配置类，当在浏览器中输入 :   localhost:8080/home/hey时会报错 ： This application has no explicit mapping for/error，so you are seeing this as a fallback.
也就是说没有hello.jsp或者没有hello.html这个页面
 所以在template下面创建一个hellp.jsp/html页面  再次输入localhost:8080/home/hey  会显示hello.jsp/html页面上的内容。
**/
	
	
	
}
