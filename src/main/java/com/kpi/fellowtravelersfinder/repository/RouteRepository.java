package com.kpi.fellowtravelersfinder.repository;


import com.kpi.fellowtravelersfinder.model.Route;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RouteRepository {
    JdbcTemplate jdbcTemplate;
    private final String SELECT_BY_ID = "SELECT id, arrival_point, departure_point FROM ROUTE WHERE id = ?";
    private final String SELECT_ALL = "SELECT id, arrival_point, departure_point FROM ROUTE";
    private final String INSERT = "INSERT INTO ROUTE (arrival_point, departure_point) VALUES (?, ?)";
    private final String UPDATE = "UPDATE ROUTE SET arrival_point = ?, departure_point = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM ROUTE WHERE id = ?";

    public RouteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Route rowToRouteMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        return new Route(resultSet.getInt("id"),
                resultSet.getString("arrival_point"),
                resultSet.getString("departure_point"));
    }

    public Optional<Route> findById(int id) {
        List<Route> routes = jdbcTemplate.query(SELECT_BY_ID, this::rowToRouteMapper, id);
        return routes.size() == 0 ? Optional.empty() : Optional.of(routes.get(0));
    }
    public List<Route> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::rowToRouteMapper);
    }
    public Route save(Route route) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT, new String[]{"id"});
            preparedStatement.setString(1, route.getArrivalPoint());
            preparedStatement.setString(2, route.getDeparturePoint());

            return preparedStatement;
        }, generatedKeyHolder);
        return new Route(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue(), route.getArrivalPoint(), route.getDeparturePoint());
    }
    public Optional<Route> update(Route route) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(UPDATE);
            preparedStatement.setString(1, route.getArrivalPoint());
            preparedStatement.setString(2, route.getDeparturePoint());
            preparedStatement.setInt(3, route.getId());

            return preparedStatement;
        });
        return Optional.of(route);
    }
    public void delete(int id) {
        jdbcTemplate.update(DELETE, id);
    }
}
