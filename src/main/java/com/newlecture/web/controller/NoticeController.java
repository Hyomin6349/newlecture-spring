package com.newlecture.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@Controller
@RequestMapping("/customer/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/detail")
	public String Detail() {
		return "notice.detail";
	}
	
	@RequestMapping("/list")
	public String List() {
		List<NoticeView> list = noticeService.getNoticeList("TITLE", "",1);
		return "notice.list";
	}
}
