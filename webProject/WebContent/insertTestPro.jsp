<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" %>

<%@ page import="java.sql.*" %>
<%request.setCharacterEncoding("euc-kr"); %>

<%
    String id = request.getParameter("id");
    String passwd = request.getParameter("passwd");
    String name = request.getParameter("name");
    Timestamp register = new Timestamp(System.currentTimeMillis());

    Connection conn = null;
    PreparedStatement psmt = null;
    String str = "";
    try {
        String jdbcUrl = "jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        String dbId = "root";
        String dbPass = "1234";

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);


        String sql = "insert into db_test.member values(?,?,?,?)";
        psmt = conn.prepareStatement(sql);
        psmt.setString(1, id);
        psmt.setString(2, passwd);
        psmt.setString(3, name);
        psmt.setTimestamp(4, register);
        psmt.executeUpdate();

        str = "member ���̺��� ���ο� ���ڵ带 �߰��޽��ϴ�. ";
    } catch (Exception e) {
        e.printStackTrace();
        str = "member ���̺��� ���ο� ���ڵ带 �����߽��ϴ�. ";
    } finally {
        if (psmt != null) try {
            psmt.close();
        } catch (SQLException e) {
        }
        if (conn != null) try {
            conn.close();
        } catch (SQLException e) {
        }
    }


%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>���ڵ� �߰�</title>
</head>
<body>

</body>
</html>