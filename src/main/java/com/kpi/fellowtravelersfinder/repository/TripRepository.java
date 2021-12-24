package com.kpi.fellowtravelersfinder.repository;


import com.kpi.fellowtravelersfinder.model.Route;
import com.kpi.fellowtravelersfinder.model.Trip;
import com.kpi.fellowtravelersfinder.model.User;
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
public class TripRepository {
    private UsersRepository usersRepository;
    private RouteRepository routeRepository;

    JdbcTemplate jdbcTemplate;
    private final String SELECT_BY_ID = "SELECT id, arrival_date, departure_date, initiator_id, route_id FROM TRIP WHERE id = ?";
    private final String SELECT_ALL = "SELECT id, arrival_date, departure_date, initiator_id, route_id FROM TRIP";
    private final String INSERT = "INSERT INTO TRIP (arrival_date, departure_date, initiator_id) VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE TRIP SET arrival_date = ?, departure_date = ?, initiator_id = ?, route_id = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM TRIP WHERE id = ?";

    public TripRepository(UsersRepository usersRepository, RouteRepository routeRepository, JdbcTemplate jdbcTemplate) {
        this.usersRepository = usersRepository;
        this.routeRepository = routeRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    private Trip rowToTripMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        User userSaved = null;
        Route routeSaved = null;
        Optional<User> initiator = usersRepository.findById(resultSet.getInt("initiator_id"));
        Optional<Route> route = routeRepository.findById(resultSet.getInt("route_id"));
        if(initiator.isPresent()){
            userSaved = initiator.get();
        }
        if(route.isPresent()){
            routeSaved = route.get();
        }

        return new Trip(resultSet.getInt("id"),
                resultSet.getDate("departure_date"),
                resultSet.getDate("arrival_date"),
                userSaved, routeSaved);
    }

    public Optional<Trip> findById(int id) {
        List<Trip> trips = jdbcTemplate.query(SELECT_BY_ID, this::rowToTripMapper, id);
        return trips.size() == 0 ? Optional.empty() : Optional.of(trips.get(0));
    }
    public List<Trip> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::rowToTripMapper);
    }
    public Trip save(Trip trip) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT, new String[]{"id"});
            preparedStatement.setDate(1, trip.getArrivalDate());
            preparedStatement.setDate(2, trip.getDepartureDate());
            preparedStatement.setInt(3, trip.getInitiator().getId());
            return preparedStatement;
        }, generatedKeyHolder);
        return new Trip(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue(), trip.getDepartureDate(), trip.getArrivalDate(), trip.getInitiator(), trip.getRoute());
    }
    public Optional<Trip> update(Trip trip) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(UPDATE);
            preparedStatement.setDate(1,  trip.getArrivalDate());
            preparedStatement.setDate(2,  trip.getDepartureDate());
            preparedStatement.setInt(3, trip.getInitiator().getId());
            preparedStatement.setInt(4,    trip.getRoute().getId());
            preparedStatement.setInt(5,    trip.getId());
            return preparedStatement;
        });
        return Optional.of(trip);
    }
    public void delete(int id) {
        jdbcTemplate.update(DELETE, id);
    }
}
