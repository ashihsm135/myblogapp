//package com.javasampleapproach.twitterbootstrap.api.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.javasampleapproach.twitterbootstrap.api.util.JsonParser;
//import com.javasampleapproach.twitterbootstrap.model.Response;
//import com.javasampleapproach.twitterbootstrap.model.User;
//import com.javasampleapproach.twitterbootstrap.service.api.UserService;
//
//@RestController
//@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
//public class UserController {
//
//	@Autowired
//	  private UserService userService;
//
//	  @RequestMapping(value = "{userId}")
//	  public ResponseEntity<Response<User>> getUserById(@PathVariable("userId") String userId) {
//	    return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "User fetched successfully.",
//	        userService.get(userId)), HttpStatus.OK);
//	  }
//
//	  @RequestMapping(method = RequestMethod.POST)
//	  public ResponseEntity<Response<User>> save(@RequestParam(value = "user") String userString,
//	      @RequestParam(value = "logo", required = false) MultipartFile logo) {
//	    return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "User saved successfully.",
//	        userService.save(JsonParser.toObject(userString, User.class), logo)), HttpStatus.OK);
//	  }
//
//	  @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
//	  public ResponseEntity<Response<User>> update(@PathVariable("userId") String userId, @RequestPart(
//	      value = "user", required = false) String userString, @RequestParam(value = "logo",
//	      required = false) MultipartFile logo) {
//	    if (StringUtils.isEmpty(userString)) {
//	      userString = "{}";
//	    }
//	    return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "User updated successfully.",
//	        userService.update(userId, JsonParser.toObject(userString, User.class), logo)),
//	        HttpStatus.OK);
//	  }
//
//	 /* @RequestMapping(value = "all", method = RequestMethod.GET)
//	  public ResponseEntity<Response<List<User>>> getAllClients(
//	      @RequestParam(value = "q", required = false) String query, Pageable pageable) {
//	    List<Filter> filters = QueryParser.parse(query);
//	    return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "User fetched successfully",
//	        userService.getAllUsers(pageable, filters),
//	        userService.getAllUsers(null, filters).size()), HttpStatus.OK);
//	  }*/
//
//	  @RequestMapping(value = "delete/{id}/{updatedBy}", method = RequestMethod.DELETE)
//	  public ResponseEntity<Response<Boolean>> delete(@PathVariable("id") String id,
//	      @PathVariable("updatedBy") String updatedBy) {
//	    userService.delete(id, updatedBy);
//	    return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "User deleted successfully.",
//	        true), HttpStatus.OK);
//	  }
//
//	  /*@RequestMapping(value = "status/{id}/{status}/{updatedBy}", method = RequestMethod.PUT)
//	  public ResponseEntity<Response<Boolean>> changeStatus(@PathVariable("id") String id,
//	      @PathVariable("status") int status, @PathVariable("updatedBy") String updatedBy) {
//	    userService.changeStatus(id, status, updatedBy);
//	    return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(),
//	        "User status updated successfully.", true), HttpStatus.OK);
//	  }*/
//	  
//	  /*@RequestMapping(value = "approver/{clientId}", method = RequestMethod.GET)
//	  public ResponseEntity<Response<List<User>>> getApproversByClientId(
//	      @PathVariable("clientId") String clientId,
//	      @RequestParam(value = "q", required = false) String query, Pageable pageable) {
//	    return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(),
//	        "Users fetched successfully.", userService.getApproversByClientId(clientId, pageable,
//	            QueryParser.parse(query))), HttpStatus.OK);
//	  }*/
//  
//}
