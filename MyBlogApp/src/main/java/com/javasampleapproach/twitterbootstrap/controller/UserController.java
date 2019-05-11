package com.javasampleapproach.twitterbootstrap.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.javasampleapproach.twitterbootstrap.service.api.UserService;
import com.javasampleapproach.twitterbootstrap.service.impl.CustomUserDetailService;
import com.javasampleapproach.twitterbootstrap.model.User;
import com.javasampleapproach.twitterbootstrap.model.constants.Constant;

@Controller
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = customUserDetailService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else {
			customUserDetailService.saveUser(user);
			modelAndView.addObject("successMessage",
					"User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("login");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(
			@RequestParam("uploadfile") MultipartFile uploadfile) {

		Map<String, String> responseMap = new LinkedHashMap<String, String>();
		String fileName = null;
		String newFileName = null;
		try {

			if (null != uploadfile && !uploadfile.isEmpty()) {
				fileName = uploadfile.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf('.'),
						fileName.length());
				/*
				 * if (checkForExtensionForGuideline(fileExt)) {
				 * responseMap.put("status_code", "404");
				 * responseMap.put("error_msg",
				 * "Uploaded File Type is not allowed!"); } else {
				 */
				responseMap.put("status_code", "200");
				newFileName = "GAndE_" + System.currentTimeMillis() + fileExt;
				// properties = properties.getInstance();
				String downloadUrl = "";
				responseMap.put("user_file_name", fileName); // userFileName
				responseMap.put("new_file_name", newFileName); // newFileName
				responseMap.put("download_name", downloadUrl);
				responseMap.put("downloadUrl", downloadUrl);
				File filePath = new File(Constant.USER_IMAGE_PATH);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				InputStream inputStream = null;
				OutputStream outputStream = null;
				inputStream = uploadfile.getInputStream();
				outputStream = new FileOutputStream(Constant.USER_IMAGE_PATH
						+ newFileName);
				int readBytes = 0;
				byte[] buffer = new byte[100];

				while ((readBytes = inputStream.read(buffer, 0, 100)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}

				outputStream.close();
				inputStream.close();

			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		return gson.toJson(responseMap);
	}
}
