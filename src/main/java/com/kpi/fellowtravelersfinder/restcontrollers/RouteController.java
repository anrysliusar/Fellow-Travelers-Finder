package com.kpi.fellowtravelersfinder.restcontrollers;

import com.kpi.fellowtravelersfinder.model.Route;
import com.kpi.fellowtravelersfinder.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;


    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @Operation(summary = "Returns all routes created by the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All routes returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Route.class)))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Route> getAll(){
        return routeService.getAll();
    }

    @Operation(summary = "Returns the route by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The route found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Route.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping("{id}")
    public Route get(@PathVariable int id){
        return routeService.getById(id).get();
    }


    @Operation(summary = "Create new route")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New route created successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Route.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Route is not valid",
                    content = @Content)
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createRoute(@RequestBody Route route) {
        return routeService.save(route);
    }

    @Operation(summary = "Updates form by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The form successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Route.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Form not found",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Form is not valid",
                    content = @Content)
    })
    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody Route route, @PathVariable int id) {
        return routeService.update(route, id);
    }

    @Operation(summary = "Deletes route by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "The route successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Route not found",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@PathVariable int id) {
        routeService.deleteById(id);
        return true;
    }
}
