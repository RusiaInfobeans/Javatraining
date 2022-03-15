package TweeterProject.Exception;

import TweeterProject.Utilis.Utility;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = CommonException.class)
    public ModelAndView handelError(CommonException exception) {
        return Utility.errorMessageModelAndView(exception.getMessage());
    }
}