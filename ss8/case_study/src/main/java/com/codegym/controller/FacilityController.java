package com.codegym.controller;

import com.codegym.dto.facility.FacilityDto;
import com.codegym.model.facility.Facility;
import com.codegym.model.facility.FacilityType;
import com.codegym.model.facility.RentType;
import com.codegym.service.facility.IFacilityService;
import com.codegym.service.facility.IFacilityTypeService;
import com.codegym.service.facility.IRentTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    private IFacilityService facilityService;

    @Autowired
    private IFacilityTypeService facilityTypeService;

    @Autowired
    private IRentTypeService rentTypeService;

    @GetMapping("/list")
    public String showList(@PageableDefault(size = 4) Pageable pageable, @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(value = "facilityType", defaultValue = "") String facilityType, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("facilityType", facilityType);
        model.addAttribute("facilityTypeList", facilityTypeService.findAll());
        model.addAttribute("facilities", facilityService.list(pageable, name, facilityType));
        return "/facility/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        List<FacilityType> facilityTypes = facilityTypeService.findAll();
        List<RentType> rentTypes = rentTypeService.findAll();
        model.addAttribute("facilityDto", new FacilityDto());
        model.addAttribute("facilityTypes", facilityTypes);
        model.addAttribute("rentTypes", rentTypes);
        return "/facility/create";
    }

    @PostMapping("/create")
    public String createFacility(@Validated @ModelAttribute("facilityDto") FacilityDto facilityDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        new FacilityDto().validate(facilityDto, bindingResult);
        if (bindingResult.hasErrors()) {
            List<FacilityType> facilityTypes = facilityTypeService.findAll();
            List<RentType> rentTypes = rentTypeService.findAll();
            model.addAttribute("facilityTypes", facilityTypes);
            model.addAttribute("rentTypes", rentTypes);
            return "/facility/create";
        } else {
            Facility facility = new Facility();
            BeanUtils.copyProperties(facilityDto, facility);
            facilityService.save(facility);
            redirectAttributes.addFlashAttribute("msg", "Successfully added new");
            return "redirect:/facility/list";
        }
    }

    @GetMapping("/delete")
    public String deleteFacility(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        facilityService.remove(id);
        redirectAttributes.addFlashAttribute("msg", "Delete successfully");
        return "redirect:/facility/list";
    }

    @GetMapping("/edit")
    public String showFormEdit(@RequestParam("id") int id, Model model) {
        List<FacilityType> facilityTypes = facilityTypeService.findAll();
        List<RentType> rentTypes = rentTypeService.findAll();
        model.addAttribute("facilityTypes", facilityTypes);
        model.addAttribute("rentTypes", rentTypes);
        Facility facility = facilityService.findById(id);
        FacilityDto facilityDto = new FacilityDto();
        BeanUtils.copyProperties(facility, facilityDto);
        model.addAttribute("facilityDto", facilityDto);
        return "/facility/edit";
    }

    @PostMapping("/edit")
    public String editFacility(@ModelAttribute("facilityDto") FacilityDto facilityDto, RedirectAttributes redirectAttributes) {
        Facility facility = new Facility();
        BeanUtils.copyProperties(facilityDto, facility);
        facilityService.save(facility);
        redirectAttributes.addFlashAttribute("msg", "Update successful");
        return "redirect:/facility/list";
    }
}
