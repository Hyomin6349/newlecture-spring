package com.newlecture.web.service.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.util.JDBCClose;

@Service
public class JDBCNoticeService implements NoticeService{
	
	@Autowired
	private DataSource dataSource;
	
	public int pubNoticeAll(int[] oids, int[] cids) {
		
		List<String> oidsList = new ArrayList<>();
		for(int i=0;i<oids.length;i++) oidsList.add(String.valueOf(oids[i]));
		List<String> cidsList = new ArrayList<>();
		for(int i=0;i<cids.length;i++) cidsList.add(String.valueOf(cids[i]));
		
		return pubNoticeAll(oidsList, cidsList);
	}
	
	public int pubNoticeAll(List<String> oids, List<String> cids) {

		String oidsCSV = String.join(",", oids);
		String cidsCSV = String.join(",", cids);
		
		return pubNoticeAll(oidsCSV, cidsCSV);
	}
	
	public int pubNoticeAll(String oidsCSV, String cidsCSV) {

		int result = 0;
		Connection con = null;
		Statement stOpen = null;
		Statement stClose = null;
		
		String sqlOpen = "UPDATE NOTICE SET PUB=1 WHERE ID IN ("+oidsCSV+")";
		String sqlClose = "UPDATE NOTICE SET PUB=0 WHERE ID IN ("+cidsCSV+")";
		
		try {
			con = dataSource.getConnection();
			stOpen = con.createStatement();
			result = stOpen.executeUpdate(sqlOpen);
			

			stClose = con.createStatement();
			result += stOpen.executeUpdate(sqlClose);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, stOpen, stClose);
		}
		
		return result;
	}
	
	public int removeNoticeAll(int[] ids) {
		return 0;
	}
	
	public int insertNotice(Notice notice) {
		
		int result = 0;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID, PUB, FILES) VALUES (?, ?, ?, ?, ?)";
		

		try {
			con = dataSource.getConnection();
			st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getWriter_id());
			st.setBoolean(4, notice.getPub());
			st.setString(5, notice.getFiles());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, st, rs);
		}
		
		return result;
	}
	
	public int deleteNotice(int id) {
		return 0;
	}
	
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	public List<Notice> getNoticeNewestList(){
		return null;
	}
	
	
	public List<NoticeView> getNoticeList(){
		
		return getNoticeList("title", "", 1);
	}
	
	public List<NoticeView> getNoticeList(int page){
		
		return getNoticeList("title", "", page);
	}
	
	public List<NoticeView> getNoticeList(String field, String query, int page){
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		List<NoticeView> list = new ArrayList<>();
		String sql = "SELECT * FROM " 
				+"(SELECT ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM, N.* "
				+ "FROM NOTICE_VIEW N "
				+ "WHERE "+ field +" LIKE ? ) "
				+ "WHERE NUM BETWEEN ? AND ?";

		try {
			con = dataSource.getConnection();
			st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			rs = st.executeQuery();

			while(rs.next()){	
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("REGDATE");
				String writer_id = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				//String content = rs.getString("CONTENT");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				
				NoticeView notice = new NoticeView(id, title, regdate, writer_id, hit, files, pub,cmtCount);
				list.add(notice);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, st, rs);
		}
		
		return list;
	}
	
	public List<NoticeView> getNoticePubList(String field, String query, int page) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		List<NoticeView> list = new ArrayList<>();
		String sql = "SELECT * FROM " 
				+"(SELECT ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM, N.* "
				+ "FROM NOTICE_VIEW N "
				+ "WHERE "+ field +" LIKE ? ) "
				+ "WHERE PUB=1 AND NUM BETWEEN ? AND ?";

		try {
			con = dataSource.getConnection();
			st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			rs = st.executeQuery();

			while(rs.next()){	
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("REGDATE");
				String writer_id = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				//String content = rs.getString("CONTENT");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				
				NoticeView notice = new NoticeView(id, title, regdate, writer_id, hit, files, pub,cmtCount);
				list.add(notice);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, st, rs);
		}
		
		return list;
	}
	
	public int getNoticeCount(){
		
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		int count = 0; 
		String sql = "SELECT COUNT(*) COUNT "
				+ "FROM NOTICE N "
				+ "WHERE "+ field +" LIKE ? ";
		
		try {
			con = dataSource.getConnection();
			st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			rs = st.executeQuery();

			if(rs.next())
				count = rs.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, st, rs);
		}
		
		return count;
	}
	
	public Notice getNotice(int id) {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		Notice notice = null;
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		
		try {
			con = dataSource.getConnection();
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if(rs.next()){	
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("REGDATE");
				String writer_id = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(nid, title, regdate, writer_id, hit, files, content, pub);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, st, rs);
		}
		
		return notice;
	}
	
	public Notice getPrevNotice(int id) {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		Notice notice = null;
		String sql = "SELECT * FROM NOTICE WHERE ID = "
				   + "(SELECT ID FROM NOTICE WHERE REGDATE < ( "
				   + "SELECT REGDATE FROM NOTICE WHERE ID = ?) "
				   + "AND ROWNUM=1)";
		
		try {
			con = dataSource.getConnection();
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if(rs.next()){	
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("REGDATE");
				String writer_id = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(nid, title, regdate, writer_id, hit, files, content, pub);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, st, rs);
		}
		
		return notice;
	}
	
	public Notice getNextNotice(int id) {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		Notice notice = null;
		String sql = "SELECT * FROM NOTICE WHERE ID = "
				   + "(SELECT MAX(ID), ROW_NUMBER() OVER (ORDER BY REGDATE DESC) "
				   + "FROM NOTICE WHERE REGDATE > ( "
				   + "SELECT REGDATE FROM NOTICE WHERE ID = ?) "
				   + "AND ROWNUM=1)";
		

		try {
			con = dataSource.getConnection();
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if(rs.next()){	
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("REGDATE");
				String writer_id = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(nid, title, regdate, writer_id, hit, files, content, pub);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, st, rs);
		}
		
		return notice;
	}

	public int deleteNoticeAll(int[] ids) {
		
		int result = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		String params = "";
		for(int i=0; i<ids.length;i++) {
			params += ids[i];
			if(i<ids.length-1) params += ",";
		}
		String sql = "DELETE NOTICE WHERE ID IN ("+params+")";
		

		try {
			con = dataSource.getConnection();
			st = con.createStatement();
			
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCClose.close(con, st, rs);
		}
		
		return result;
	}

	

	
}