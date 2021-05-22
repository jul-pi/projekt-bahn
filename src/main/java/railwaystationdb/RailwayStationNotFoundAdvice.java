package railwaystationdb;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
class RailwayStationNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(RailwayStationNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String RSNotFoundHandler(RailwayStationNotFoundException ex) {
		return ex.getMessage();
	}
	
}
