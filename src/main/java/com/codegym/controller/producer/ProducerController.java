package com.codegym.controller.producer;

import com.codegym.model.Producer;
import com.codegym.model.Product;
import com.codegym.service.ProducerService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProducerController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProducerService producerService;

    @GetMapping("/producers")
    public ModelAndView listProducer(){
        Iterable<Producer> producers = producerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/producer/list");
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("/create-producer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/producer/create");
        modelAndView.addObject("producer", new Producer());
        return modelAndView;
    }

    @PostMapping("/create-producer")
    public ModelAndView saveProvince(@ModelAttribute("producer") Producer producer){
        producerService.save(producer);

        ModelAndView modelAndView = new ModelAndView("/producer/create");
        modelAndView.addObject("producer", new Producer());
        modelAndView.addObject("message", "New producer created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-producer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Producer producer = producerService.findById(id);
        if(producer != null) {
            ModelAndView modelAndView = new ModelAndView("/producer/edit");
            modelAndView.addObject("producer", producer);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-producer")
    public ModelAndView updateProducer(@ModelAttribute("producer") Producer producer){
        producerService.save(producer);
        ModelAndView modelAndView = new ModelAndView("/producer/edit");
        modelAndView.addObject("producer", producer);
        modelAndView.addObject("message", "Producer updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-producer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Producer producer = producerService.findById(id);
        if(producer != null) {
            ModelAndView modelAndView = new ModelAndView("/producer/delete");
            modelAndView.addObject("producer", producer);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-producer")
    public String deleteProvince(@ModelAttribute("producer") Producer producer){
        producerService.remove(producer.getProducerId());
        return "redirect:producers";
    }

}
