package com.kpi.fellowtravelersfinder.restcontrollers;

import com.kpi.fellowtravelersfinder.dto.TripDto;
import com.kpi.fellowtravelersfinder.model.Pageable;
import com.kpi.fellowtravelersfinder.model.Route;
import com.kpi.fellowtravelersfinder.model.Trip;
import com.kpi.fellowtravelersfinder.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;

    }


    @GetMapping("/filtered")
    public List<Trip> getAll(@RequestParam(value = "departurePoint", defaultValue = "") String departurePoint,
                             @RequestParam(value = "arrivalPoint", defaultValue = "") String arrivalPoint,
                             @RequestParam(value = "pageNumber") Integer pageNum,
                             @RequestParam(value = "itemsCount") Integer itemsCount){

        Pageable pageable = new Pageable(pageNum, itemsCount);
        return tripService.findAllByRoute(departurePoint, arrivalPoint, pageable);

    }


    @GetMapping()
    public List<Trip> getAll() {
        return tripService.getAll();
    }

    @Operation(summary = "Returns the trip by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The trip found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Trip.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping("/{id}")
    public Trip get(@PathVariable int id) {
        return tripService.getById(id);
    }

    @Operation(summary = "Create new trip with route")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New trip created successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Trip.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Trip is not valid",
                    content = @Content)
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Trip createTripWithRoutes(@RequestBody TripDto tripDto) {
        return tripService.saveTripWithRoute(tripDto);
    }

    @Operation(summary = "Updates trip by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The trip successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Trip.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Trip not found",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Trip is not valid",
                    content = @Content)
    })
    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean update(Trip trip, @PathVariable int id) {
        boolean update = tripService.update(trip, id);
        return update;
    }

    @Operation(summary = "Deletes trip by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "The trip successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Trip not found",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@PathVariable int id) {
        tripService.deleteById(id);
        return true;
    }

}
