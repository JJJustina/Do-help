package com.doHelp.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.doHelp.model.LiveRedis;
import com.doHelp.model.Managers;
import com.doHelp.model.Remarks;
import com.doHelp.model.User;
import com.doHelp.service.LiveService;
import com.doHelp.service.ManagerService;
import com.doHelp.service.UserService;
import com.doHelp.service.UserServices;

@RestController
@EnableAutoConfiguration
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;
	
	@Autowired
	ManagerService managerService;
	
	@Autowired
	private LiveService liveService;
	
	@Autowired
	 UserServices userServices;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView toLogin(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return new ModelAndView("login");
	}
	// @ModelAttribute(value = "user") UserRedis user 注释方法参数，参数user的uge值来源与model属性
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute(value="user") User user, ModelMap model, HttpSession session) throws Exception {
		String url = "main";
		if (userService.isUser(user)) {
			Date date =new Date();
			SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			User users=userServices.findByPhone(user.getUser_phone());
			users.setUser_ip1(users.getUser_ip());
			users.setUser_usingAt1(users.getUser_usingAt());
			users.setUser_ip(InetAddress.getLocalHost().getHostAddress());
		    users.setUser_usingAt(from.format(date));
		    userServices.updateTime(users);
			session.setAttribute("userRedis", users);
		} else {
			model.addAttribute("errorMessage", "用户名或密码错误");//返回错误信息
			logger.info("用户名  = " + user.getUser_phone());//日志打印
			url = "login";
		}
		return new ModelAndView(url);
	}
	@RequestMapping(value="/main" , method=RequestMethod.GET)
	 public ModelAndView toMain(){
		return new ModelAndView("main");
	}

		@RequestMapping(value="/selfMessage" , method=RequestMethod.GET)
		 public ModelAndView toSelfMessage(User user){
			return new ModelAndView("selfMessage");
	}
		@RequestMapping(value="/changeMessage" , method=RequestMethod.GET)
		 public ModelAndView toChangeMessage(User user){
			return new ModelAndView("changeMessage");
	}
		@RequestMapping(value="/photoUpload" , method=RequestMethod.GET)
		 public ModelAndView photoUpload(User user){
			return new ModelAndView("photoUpload");
	}
		
		@RequestMapping(value="/changePassWord" , method=RequestMethod.GET)
		 public ModelAndView r(User user){
			return new ModelAndView("changePassWord");
	}
			
		
	@RequestMapping(value="/toManagerLogin",method=RequestMethod.GET )
	   public ModelAndView toManagerLogin() {
		return new ModelAndView("managerLogin");
	}
	
   @RequestMapping(value ="/managerLogin" , method= RequestMethod.POST)
      public ModelAndView managerLogin(@ModelAttribute(value="managers") Managers managers,ModelMap model,HttpSession session) {
	   String url= "liveList";
	   if(managerService.isManager((com.doHelp.model.Managers) managers)) {
		   session.setAttribute("managerRedis", managers);
		   List<LiveRedis> list = liveService.getAll();//直播列表
			model.addAttribute("liveList", list);
			Managers object = (Managers) session.getAttribute("managers");
			System.out.println(object);
	   }
	   else
	   {
		   model.addAttribute("errorMessage", "手机号或密码错误");//返回错误信息
			logger.info("用户名  = " + ((com.doHelp.model.Managers) managers).getManager_phone());//日志打印
			url = "login";
	   }
	   return new ModelAndView (url);
   }
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/login");
	}
	/**重定向 return "redirect:/user/index";
	几种情况：1.不带参数跳转，2.带参数拼接url形式跳转，3.带参数不拼接参数跳转，页面也能显示。 
	1.不带参数的重定向: 我在后台一个controller跳转到另一个controller，为什么有这种需求呢，是这样的。我有一个列表页面，然后我会进行新增操作，新增在后台完成之后我要跳转到列表页面，不需要传递参数，列表页面默认查询所有的。 
	 方式一：使用ModelAndView(这是Spring 2.0的时候所用到的方法) 
        return new ModelAndView("redirect:/toList"); 
                   这样可以重定向到toList这个方法 
           方式二：返回String 
        return "redirect:/ toList "; 
	2.带参数的重定向 :第二种情况，列表页面有查询条件，跳转后我的查询条件不能丢掉，这样就需要带参数的了，带参数可以拼接url 
	 方式一：自己手动拼接url 
        new ModelAndView("redirect:/toList？param1="+value1+"&param2="+value2); 
        这样有个弊端，就是传中文可能会有乱码问题。 

           方式二：用RedirectAttributes，这个是发现的一个比较好用的一个类 
        这里用它的addAttribute方法，这个实际上重定向过去以后你看url，是它自动给你拼了你的url。 
        使用方法： 
       public String test(RedirectAttributes attributes) 
{ 
attributes.addAttribute("test", "hello"); 
return "redirect:/test/test2"; 
} 
	这样在toController这个方法中就可以通过获得参数的方式获得这个参数，再传递到页面。过去的url还是和方式一一样的。如果你细心的看重定向之后的url地址的话，你就会发现其实和上面的地址是一样的，这样也会出现上面那个方法出现的问题。 
    重点来了，咱们介绍一个不会出现中文乱码，而且不会在你的Url上出现你所要传递的数据的，这样就可以保证你在传递数据的安全 
        public String red(RedirectAttributes attributes) 
{ 
attributes.addFlashAttribute("test", "hello"); 
return "redirect:/test/test2"; 
} 
	
	-------
	咱们用上面的方法进行数据传递你就会发现不会再Url上出现你要传递的数据，那么数据放到哪里去了，我们就来看看这是Spring 3.0新出现的特性，attributes.addFlashAttribute("test", "hello")实际存储的属性在flashmap，那么flashmap又是什么呢？ 
    Flash 属性 和 RedirectAttribute：通过FlashMap存储一个请求的输出，当进入另一个请求时作为该请求的输入，典型场景如重定向（POST-REDIRECT-GET模式，1、POST时将下一次需要的数据放在FlashMap；2、重定向；3、通过GET访问重定向的地址，此时FlashMap会把1放到FlashMap的数据取出放到请求中，并从FlashMap中删除；从而支持在两次请求之间保存数据并防止了重复表单提交）。 
     Spring Web MVC提供FlashMapManager用于管理FlashMap，默认使用SessionFlashMapManager，即数据默认存储在session中 
    既然知道了怎么回事，那么我们就可以把它提取出来，怎么提取呢，很多人会说，既然存在session中，那就从session中获取，结果发现没有，那怎么办？ 
    下面我就给大家提供两个方法让大家把addFlashAttribute中的数据提取出来 
    方法一：利用httpServletRequest 
    public String test2(HttpServletRequest request) 
{ 
Map<String,?> map = RequestContextUtils.getInputFlashMap(request); 
System.out.println(map.get("test").toString()); 
return "/test/hello"; 
} 
     方法二：利用Spring提供的标签@ModelAttribute 
public String test2(@ModelAttribute("test") String str) 
{ 
System.out.println(str); 
return "/test/hello"; 
} 
   以上两种方法是在后台Controller层获取值的方法，如果是在前台页面的话，就会比较简单，直接利用el表达式就可以取到数据 

	
**/	
}
