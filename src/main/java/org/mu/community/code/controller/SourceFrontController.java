package org.mu.community.code.controller;

import org.mu.community.code.entity.JavaLibrary;
import org.mu.community.code.service.JavaLibraryService;
import org.mu.community.code.service.TagService;
import org.mu.community.common.dbutil.Page;
import org.mu.community.common.security.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/source")
public class SourceFrontController {
	
	private static final int LIST_SIZE = 20;

	private JavaLibraryService javaLibraryService;

	private TagService tagService;
	
	@RequestMapping(value = {"", "/", "index.html"}, method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		Page<JavaLibrary> jarFiles = javaLibraryService.getJarFiles(0, LIST_SIZE);
		model.put("page", jarFiles);
		model.put("tags", tagService.getMostTagged("java"));
		return new ModelAndView("source/index", model);
	}
	
	@RequestMapping(value = "advancedSearch.html", method = RequestMethod.GET)
	public ModelAndView search(ModelMap model) {
		model.put("test", "test");
		return new ModelAndView("source/search", model);
	}
	
	@RequestMapping(value = "search.html", method = RequestMethod.GET)
	public ModelAndView search(ModelMap model, 
			@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "project", required = false) String project, 
			@RequestParam(value = "version", required = false) String version,
			@RequestParam(value = "category", required = false) String category, 
			@RequestParam(value = "tags", required = false) String tags, 
			@RequestParam(value = "verified", required = false) boolean verified) {
		return new ModelAndView("source/search", model);
	}
	
	@RequestMapping(value = "upload.html", method = RequestMethod.GET)
	public ModelAndView upload(@AuthenticationPrincipal Authentication auth, ModelMap model) {
		return null;
	}

    @Autowired
	public void setJavaLibraryService(JavaLibraryService javaLibraryService) {
		this.javaLibraryService = javaLibraryService;
	}

    @Autowired
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
}
