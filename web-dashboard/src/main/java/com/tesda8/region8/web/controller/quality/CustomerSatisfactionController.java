package com.tesda8.region8.web.controller.quality;

import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.quality.model.dto.FeedbackDto;
import com.tesda8.region8.quality.service.CaptchaValidator;
import com.tesda8.region8.quality.service.FeedbackService;
import com.tesda8.region8.web.controller.HeaderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class CustomerSatisfactionController extends HeaderController {

    private static Logger logger = LoggerFactory.getLogger(CustomerSatisfactionController.class);

    private FeedbackService feedbackService;
    private CaptchaValidator captchaValidator;

    @Autowired
    public CustomerSatisfactionController(RegisteredProgramStatusService registeredProgramStatusService,
                                          ExpiredCertificateService expiredCertificateService,
                                          FeedbackService feedbackService,
                                          CaptchaValidator captchaValidator) {
        super(registeredProgramStatusService, expiredCertificateService);
        this.feedbackService = feedbackService;
        this.captchaValidator = captchaValidator;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer_satisfaction")
    public String customerSatisfaction(Model model) {
        addExpiredDocumentsListToModel(model);
        model.addAttribute("feedbackForm", FeedbackDto.build(feedbackService.generateControlNumber(LocalDateTime.now())));
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
                                           HttpServletRequest request,
                                           Model model) throws Exception {
        Boolean isValidCaptcha = captchaValidator.validateCaptcha(request.getParameter("g-recaptcha-response"));
        if(!isValidCaptcha){
            throw new Exception("Captcha is not valid");
        }
        addExpiredDocumentsListToModel(model);
        model.addAttribute("feedbackForm", feedbackDto);
        feedbackService.createFeedback(feedbackDto);
        return "quality/customer_satisfaction_success";
    }
}
