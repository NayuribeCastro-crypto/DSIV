
package cr.ac.urc.ie.veridis.controller;

import cr.ac.urc.ie.veridis.domain.Reception;
import cr.ac.urc.ie.veridis.service.IReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/receptions")
public class ReceptionController {
    
     @Autowired
    private IReceptionService service;
    

    @GetMapping("/index")
    public String index(Model model) {

        model.addAttribute("receptions", service.getAll());
        return "receptions/index";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {

        model.addAttribute("reception", new Reception());
        return "receptions/create";
    }

    @PostMapping("/save")
    public String save(Reception reception, Model model) {
        model.addAttribute("reception", reception);

        service.save(reception);
        return "redirect:/receptions/index";
    }

    
    @GetMapping("/deleteById")
    public String delete(@RequestParam int receptionId) {
        service.delete(receptionId);
        return "redirect:/receptions/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int receptionId, Model model) {

        model.addAttribute("reception", service.getById(receptionId));
        return "receptions/edit";
    }

    @GetMapping("/findById")
    @ResponseBody
    public Reception find(@RequestParam int receptionId) {
        return service.getById(receptionId);

    }

    @PostMapping("/update")
    public String update(Reception reception, Model model) {

        model.addAttribute("reception", reception);
        service.update(reception);
        return "redirect:/receptions/index";
    }

    
}
