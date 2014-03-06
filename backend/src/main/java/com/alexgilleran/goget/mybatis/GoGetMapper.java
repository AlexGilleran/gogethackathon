package com.alexgilleran.goget.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.alexgilleran.goget.model.Booking;
import com.alexgilleran.goget.model.Car;
import com.alexgilleran.goget.model.CarModel;
import com.alexgilleran.goget.model.Pod;

public interface GoGetMapper {
    @Select("SELECT COUNT(*) FROM pod")
    int getPodCount();

    @Select("SELECT * FROM Pod")
    @Results(value = { @Result(property = "podId", column = "idPod"),//
	    @Result(property = "areaName", column = "name"),//
	    @Result(property = "areaLatitude", column = "latitude"),//
	    @Result(property = "areaLongitude", column = "longitude") })
    List<Pod> getPods();

    @Select("SELECT * FROM Pod WHERE idPod = #{id}")
    @Results(value = { @Result(property = "podId", column = "idPod"),//
	    @Result(property = "areaName", column = "name"),//
	    @Result(property = "areaLatitude", column = "latitude"),//
	    @Result(property = "areaLongitude", column = "longitude") })
    Pod getPod(long id);

    @Select("SELECT * FROM vehicle WHERE idPod = #{podId}")
    @Results(value = { @Result(property = "carId", column = "idVehicle"),//
	    @Result(property = "podId", column = "idPod"),//
	    @Result(property = "modelId", column = "idCarModel") })
    List<Car> getVehiclesForPod(long id);

    @Select("SELECT * FROM booking WHERE idVehicle = #{id}")
    @Results(value = { @Result(property = "bookingId", column = "idBooking"),//
	    @Result(property = "carId", column = "idVehicle"),//
	    @Result(property = "podId", column = "idPod"),//
	    @Result(property = "kmsLogged", column = "kmsLogged") })
    List<Booking> getBookingsForVehicle(long id);

    @Select("SELECT * FROM carmodel WHERE idCarModel = #{id}")
    @Results(value = { @Result(property = "modelId", column = "idCarModel"),//
	    @Result(property = "consumptionCity", column = "consumptionCity"),//
	    @Result(property = "carbonDioxide", column = "co2") })
    CarModel getCarModel(long id);

    @Insert("INSERT INTO pod (idPod, name, latitude, longitude) VALUES (#{podId}, #{areaName}, #{areaLatitude}, #{areaLongitude})")
    void insertPod(Pod pod);

    @Insert("INSERT INTO carmodel (idCarModel, consumptionCity, co2) VALUES (#{modelId}, #{consumptionCity}, #{carbonDioxide})")
    void insertCarModel(CarModel carModel);

    @Insert("INSERT INTO vehicle (idVehicle, idPod, idCarModel) VALUES (#{carId}, #{podId}, #{modelId})")
    void insertCar(Car car);

    @Insert("INSERT INTO booking (idBooking, idVehicle, idPod, kmsLogged) VALUES (#{bookingId}, #{carId}, #{podId}, #{kmsLogged})")
    void insertBooking(Booking booking);

    @Delete("DELETE FROM pod")
    void clearPods();

    @Delete("DELETE FROM booking")
    void clearBookings();

    @Delete("DELETE FROM carmodel")
    void clearCarModels();

    @Delete("DELETE FROM vehicle")
    void clearVehicles();
}
