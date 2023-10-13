package com.example.platform.utils;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class InitDBUtils {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(InitDBUtils.class);

    // jdbc地址
    private static final String jdbcTemplate =
            "jdbc:mysql://#{mysqlUrl}:3306/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2b8";
    // Druid驱动
    private static final String driveClass = "com.alibaba.druid.pool.DruidDataSource";
    //删除现有该名称的的数据库
    private static final String dropSchemaSqlTemplate = "DROP DATABASE IF EXISTS #{schema}";
    // 创建数据库
    private static final String createSchemaSqlTemplate =
            "CREATE DATABASE `#{schema}` CHARACTER SET 'utf8mb4' collate 'utf8mb4_general_ci' ";
    // 使用此建立的数据库
    private static final String useSchemaSqlTemplate = "use #{schema}";

    /**
     * 该方法用于创建使用者的数据库
     * @param mysqlUrl
     * @param schema
     * @param username
     * @param password
     * @param user_password
     * @return
     */
    public static boolean initDB(String mysqlUrl, String schema, String username, String password,String user_password) {

        Connection connection = null;
        try {
            Class.forName(driveClass); // 初始化
            // 链接数据库
            connection = DriverManager.getConnection(jdbcTemplate.replace("#{mysqlUrl}", mysqlUrl), username, password);
            Statement statement = connection.createStatement(); // 创建数据库
            // 创建代码
            statement.execute(dropSchemaSqlTemplate.replace("#{schema}", schema));
            statement.execute(createSchemaSqlTemplate.replace("#{schema}", schema));
            statement.execute(useSchemaSqlTemplate.replace("#{schema}", schema));
            // 用来读取和执行sql脚本
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.setStopOnError(true);
            ClassPathResource classPathResource = new ClassPathResource("company.sql"); // 加载此company.sql sql文件
            InputStream inputStream = classPathResource.getInputStream(); // 读取
            InputStreamReader isr = new InputStreamReader(inputStream);
            scriptRunner.runScript(isr); // 读取完之后执行
            // 创建用户id和密码
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            String updatePassword =
                    "update system_user set password = " +"'"+user_password+"'"+"  ,  id =  "  +"'"+id+"'"+"   where id = 1 ;";

            String insertUserRole = "  insert into user_role (role_id , user_id ) values ('1' , " +"'"+id+"'"+") ;";

          //  updatePassword =updatePassword + insertUserRole;
            // 将创建的用户id和密码传给数据库中的sysytem user记录
            insertSystemUser(connection, mysqlUrl, schema, username, password,updatePassword,insertUserRole);
            //提交关闭
            connection.commit();
            connection.close();

        } catch (Exception e) { // 异常抛出
            logger.error("初始化数据库报错", e.getMessage());
            return false;

        } finally { // 否则就直接关闭
            if (connection != null) {
                try {
                    connection.commit();
                    connection.close();
                } catch (Exception e) {

                }
            }
        }
        return true;
    }

    // 连接数据库
    public static boolean tryConnectDB(String mysqlUrl, String schema, String username, String password) {
        Connection connection = null;
        try {
            Class.forName(driveClass); // 初始化说
            connection = DriverManager.getConnection(jdbcTemplate.replace("#{mysqlUrl}", mysqlUrl), username, password);
            return true;
        } catch (Exception e) {
            logger.error("连接数据库失败", e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.commit();
                    connection.close();
                } catch (Exception e) {

                }
            }
        }
    }

    // 对数据库连接池做初始化设置
    public static DruidDataSource getInitDBConfig() {
        DruidDataSource druidDataSource = new DruidDataSource(); // 连接池数据对象
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // 设置驱动
        druidDataSource.setInitialSize(5); // 初始化五个连接
        druidDataSource.setMinIdle(5); // 最小连接
        druidDataSource.setMaxActive(20); // 最大连接
        druidDataSource.setMaxWait(60000); // 最大等待
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("select 1 from dual ");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return druidDataSource;
    }

    // 把数据放入system user表中
    private static void insertSystemUser(Connection connection, String mysqlUrl, String schema, String username, String password) throws SQLException {
        Statement statement = null;
        try {
            // 随机生成id
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            // sql语句，插入一个system user
            String sql =
                    "INSERT INTO system_user (id,nick_name,username,password,create_time) values ('#{id}','超级管理员','#{username}','#{password}',now())";
            String jdbcUrl =
                    "jdbc:mysql://#{mysql}:3306/#{schema}?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2b8"; // 数据库连接地址
            String replace = jdbcUrl.replace("#{mysql}", mysqlUrl).replace("#{schema}", schema); // 将参数传入jdbc
            String runSql = sql.replace("#{id}", id).replace("#{username}", schema).replace("#{password}", schema); // 将参数传入sql
            connection = DriverManager.getConnection(
                    replace,
                    username, password); // 连接数据库
            statement = connection.createStatement();
            statement.executeUpdate(runSql);
            statement.close();
        } catch (Exception e) {
            logger.error("系统用户添加失败", e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    // overload 相比于上面的方法，执行了多条语句。
    private static void insertSystemUser(Connection connection, String mysqlUrl, String schema, String username, String password,String user_password,String userRole) throws SQLException {
        Statement statement = null;
        try {
            String id = UUID.randomUUID().toString().replaceAll("-", "");

            String jdbcUrl =
                    "jdbc:mysql://#{mysql}:3306/#{schema}?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2b8";
            String replace = jdbcUrl.replace("#{mysql}", mysqlUrl).replace("#{schema}", schema);
            connection = DriverManager.getConnection(
                    replace,
                    username, password);
            statement = connection.createStatement(); // 创建一个数据库传输。Creates a Statement object for sending SQL statements to the database
            statement.executeUpdate(user_password); // Parameter is a sql statement not a String
            statement.executeUpdate(userRole); // Parameter is a sql statement not a string
            statement.close();
        } catch (Exception e) {
            logger.error("系统用户添加失败", e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String sql = "select * from system_user ";
        Class.forName(driveClass);
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/event_visualization?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2b8",
                "root", "root");

        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Map<String, Object>> resultList = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> dataMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                dataMap.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }
            resultList.add(dataMap);
        }
        System.out.println(JSONObject.toJSONString(resultList));
    }
}
