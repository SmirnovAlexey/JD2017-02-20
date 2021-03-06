package by.it.smirnov.project.java.DAO;

import by.it.smirnov.project.java.Connection.ConnectorCreator;
import by.it.smirnov.project.java.bean.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * Created by aleksey.smirnov on 01.05.2017.
 */
public class RoleDAO extends AbstractDAO<Role> {
    private static final String selectSQL = "SELECT `id`, `name` FROM `roles`";
    private static final String countSQL = "SELECT count(*) FROM `roles`";
    private static final String insertSQL = "INSERT INTO `roles`(`id`, `name`) VALUES (?,?);";
    private static final String updateSQL = "UPDATE `roles` SET `name`=? WHERE ID=?";
    private static final String deleteSQL = "DELETE FROM `roles` WHERE ID=?";

    @Override
    Role CreateElement(ResultSet rs) throws SQLException {
        return new Role(
                rs.getInt("ID"),
                rs.getString("name")
        );
    }

    @Override
    Role CreateElement(int id) {
        Role role = new Role();
        role.setId(id);
        return role;
    }

    @Override
    PreparedStatement getStatementInsertSQL(Connection connection, Role role) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(insertSQL, RETURN_GENERATED_KEYS);
        ps.setInt(1,role.getId());
        ps.setString(2,role.getName());
        return ps;
    }

    @Override
    PreparedStatement getStatementUpdateSQL(Connection connection, Role role) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(updateSQL);
        ps.setString(1,role.getName());
        ps.setInt(2,role.getId());
        return ps;
    }

    @Override
    PreparedStatement getStatementDeleteSQL(Connection connection, Role role) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(deleteSQL);
        ps.setInt(1,role.getId());
        return ps;
    }

    @Override
    PreparedStatement getStatementSelectSQL(Connection connection, String whereExpression) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(selectSQL.concat(whereExpression));
        return ps;
    }

    @Override
    PreparedStatement getStatementCountSQL(Connection connection, String whereExpression) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(countSQL.concat(whereExpression));
        return ps;
    }


}

