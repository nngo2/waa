package edu.mum.cs544;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarController {

	@Resource
	private ICarDao carDao;
	
	@RequestMapping(value="/cars", method=RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("cars", carDao.getAll());
		return "carList";
	}
	
	@RequestMapping(value="addCar", method=RequestMethod.GET)
	public String addCar(@ModelAttribute("car") Car car) {
		return "addCar";
	}
	
	@RequestMapping(value="/cars", method=RequestMethod.POST)
	public String add(@Valid Car car, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addCar";
		} else {
			carDao.add(car);
			return "redirect:/cars";
		}
	}

	@RequestMapping(value="/cars/{id}", method=RequestMethod.GET)
	public String get(@PathVariable int id, Model model) {
		model.addAttribute("car", carDao.get(id));
		return "carDetail";
	}
	
	@RequestMapping(value="/cars/{id}", method=RequestMethod.POST)
	public String update(@PathVariable int id, @Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "carDetail";
		} else {
			carDao.update(id, car); // car.id already set by binding
			return "redirect:/cars";			
		}
	}
	
	@RequestMapping(value="/cars/delete", method=RequestMethod.POST)
	public String delete(int carId) {
		carDao.delete(carId);
		return "redirect:/cars";
	}

	@ExceptionHandler(value=NoSuchResourceException.class)
	public ModelAndView handle(Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("e", e);
		mv.setViewName("noSuchResource");
		return mv;
	}
}
