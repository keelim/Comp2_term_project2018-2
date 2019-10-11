<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR" %>

<%@ page import="java.sql.*" %>
<%request.setCharacterEncoding("euc-kr"); %>

<%
    String id = request.getParameter("id");
    String passwd = request.getParameter("passwd");
    String name = request.getParameter("name");


    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;

    try {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_test?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        String dbId = "root";
        String dbPass = "1234";

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
        String sql = "slect id, passwd from db_test.member where id=?";

        psmt = conn.prepareStatement(sql);
        psmt.setString(1, id);

        rs = psmt.executeQuery();

        if (rs.next()) {
            String rId = rs.getString("id");
            String rpasswd = rs.getString("passwd");
            if (id.equals(rId) && passwd.equals(rpasswd)) {
                sql = "update db_test.member set name = ? where id = ?";
                psmt = conn.prepareStatement(sql);
                psmt.setString(1, name);
                psmt.setString(2, id);
                psmt.executeUpdate();


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
<%
            } else {
                out.println("�н����尡 Ʋ�ǽ��ϴ�.");
            }

        } else {
            out.println("���̵� Ʋ�Ƚ��ϴ�. ");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) try {
            rs.close();
        } catch (SQLException e) {
        }
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

