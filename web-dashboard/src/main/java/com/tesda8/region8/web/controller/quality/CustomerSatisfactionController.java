package com.tesda8.region8.web.controller.quality;

import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.service.FeedbackService;
import com.tesda8.region8.web.controller.DefaultController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerSatisfactionController extends DefaultController {

    private static Logger logger = LoggerFactory.getLogger(CustomerSatisfactionController.class);


    private FeedbackService feedbackService;

    @Autowired
    public CustomerSatisfactionController(RegisteredProgramStatusService registeredProgramStatusService,
                                          FeedbackService feedbackService) {
        super(registeredProgramStatusService);
        this.feedbackService = feedbackService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer_satisfaction")
    public String customerSatisfaction(Model model) {
        addExpiredDocumentsListToModel(model);
        model.addAttribute("feedbackForm", FeedbackDto.build());
        return "quality/customer_satisfaction";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customer_satisfaction/tesdaForm")
    public String customerSatisfaction(@ModelAttribute FeedbackDto feedbackDto, BindingResult bindingResult,
                                       Model model) {
        addExpiredDocumentsListToModel(model);
        model.addAttribute("feedbackForm", feedbackDto);
        return "quality/customer_satisfaction_tesda_form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customer_satisfaction/save")
    public String customerSatisfactionSave(@ModelAttribute FeedbackDto feedbackDto, BindingResult bindingResult,
                                       Model model) {
        addExpiredDocumentsListToModel(model);
        model.addAttribute("feedbackForm", feedbackDto);
        feedbackService.createFeedback(feedbackDto);
        return "/home";
    }
}
