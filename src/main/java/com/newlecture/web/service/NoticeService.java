package com.newlecture.web.service;
import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public interface NoticeService {

public int pubNoticeAll(int[] oids, int[] cids);
	
	public int pubNoticeAll(List<String> oids, List<String> cids);
	
	public int pubNoticeAll(String oidsCSV, String cidsCSV);
	
	public int removeNoticeAll(int[] ids);
	
	public int insertNotice(Notice notice);
	
	public int deleteNotice(int id);
	
	public int updateNotice(Notice notice);
	
	public List<Notice> getNoticeNewestList();
	
	public List<NoticeView> getNoticeList();
	
	public List<NoticeView> getNoticeList(int page);
	
	public List<NoticeView> getNoticeList(String field, String query, int page);
	
	public List<NoticeView> getNoticePubList(String field, String query, int page) ;
	
	public int getNoticeCount();
	
	public int getNoticeCount(String field, String query);
	public Notice getNotice(int id);
	
	public Notice getPrevNotice(int id);
	
	public Notice getNextNotice(int id);

	public int deleteNoticeAll(int[] ids);
}
