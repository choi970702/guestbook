package com.ict.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DAO 
{
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs; // select문의 결과 
	
	// 싱글톤
	private static DAO dao = new DAO();
	public static DAO getInstance() 
	{
		return dao;
	}
	
	//DB 접속
	public Connection getConnection() 
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.0.9:1521:xe";
			String user = "c##choi970702";
			String password = "chl147258";
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) 
		{
			System.out.println(e+"1");
		}
	   return null;	
	}
	
	// list
	public List<VO> getSelectAll()
	{
		try 
		{
			List<VO> list = new ArrayList<VO>();
			conn = getConnection();
			String sql = "select * from guestbook order by idx";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) 
			{
				VO vo = new VO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContent(rs.getString("content"));
				vo.setEmail(rs.getString("email"));
				vo.setPwd(rs.getString("pwd"));
				vo.setRegdate(rs.getString("regdate"));
				list.add(vo);
				
			}
			return list;
		} catch (Exception e) 
		{
			System.out.println(e+"2");
		}finally
		{
			try 
			{
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) 
			{
				System.out.println(e2+"3");
			}
		}
		return null;
		
	}
	
	// insert
	public int getInsert(VO vo)
	{
		try 
		{
			int result = 0;
			conn = getConnection();
			String sql = "insert into guestbook values"
					+ "(guestbook_seq.nextval,?, ?,?,?,?,sysdate)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, vo.getName());
			pstm.setString(2, vo.getSubject());
			pstm.setString(3, vo.getContent());
			pstm.setString(4, vo.getEmail());
			pstm.setString(5, vo.getPwd());
			result = pstm.executeUpdate();
			return result;
			
		} catch (Exception e) 
		{
			System.out.println(e+"4");
		}finally
		{
			try 
			{
				pstm.close();
				conn.close();
			} catch (Exception e2) 
			{
				System.out.println(e2+"5");
			}
		}
		return 0;
		
	}
	
	// select문
	public VO getSelectOne(String idx)
	{
		try 
		{
			VO vo = null;
			conn = getConnection();
			String sql = "select * from guestbook where idx=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idx);
			rs = pstm.executeQuery();
			while (rs.next()) 
			{
				vo = new VO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContent(rs.getString("content"));
				vo.setEmail(rs.getString("email"));
				vo.setPwd(rs.getString("pwd"));
				vo.setRegdate(rs.getString("regdate"));
				
				
			}
			return vo;
		} catch (Exception e) 
		{
			System.out.println(e+"6");
		}finally
		{
			try 
			{
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) 
			{
				System.out.println(e2+"7");
			}
		}
		return null;
		
	}
	
	// update
		public int getUpdate(VO vo)
		{
			try 
			{
				int result = 0;
				conn = getConnection();
				String sql = "update guestbook set name=?,subject=?,content=?,email=? where idx=?";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, vo.getName());
				pstm.setString(2, vo.getSubject());
				pstm.setString(3, vo.getContent());
				pstm.setString(4, vo.getEmail());
				pstm.setString(5, vo.getIdx());
				result = pstm.executeUpdate();
				return result;
				
			} catch (Exception e) 
			{
				System.out.println(e+"8");
			}finally
			{
				try 
				{
					pstm.close();
					conn.close();
				} catch (Exception e2) 
				{
					System.out.println(e2+"9");
				}
			}
			return 0;
			
		}
		
		// delete
		public int getDelete(String idx)
		{
			try 
			{
				int result = 0;
				conn = getConnection();
				String sql = "delete from guestbook where idx=?";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, idx);
				result = pstm.executeUpdate();
				return result;
				
			} catch (Exception e) 
			{
				System.out.println(e+"10");
			}finally
			{
				try 
				{
					pstm.close();
					conn.close();
				} catch (Exception e2) 
				{
					System.out.println(e2+"11");
				}
			}
			return 0;
			
		}
	
	
	
}
