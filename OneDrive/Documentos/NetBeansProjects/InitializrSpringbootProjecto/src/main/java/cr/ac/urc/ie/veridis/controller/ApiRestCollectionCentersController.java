package cr.ac.urc.ie.veridis.controller;

import cr.ac.urc.ie.veridis.domain.CollectionCenters;
import cr.ac.urc.ie.veridis.service.ICollectionCentersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/collectionCenters")
public class CollectionCentersController {
    
    
     @Autowired
    private ICollectionCentersService service;
   

    @GetMapping("/index")
    public String index(Model model) {

        model.addAttribute("collectionCenters", service.getAll());
        return "collectionCenters/index";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {

        model.addAttribute("collectionCenter", new CollectionCenters());
        return "collectionCenters/create";
    }

    @PostMapping("/save")
    public String save(CollectionCenters collectionCenters, Model model) {
        model.addAttribute("collectionCenters", collectionCenters);

        service.save(collectionCenters);
        return "redirect:/collectionCenters/index";
    }

    
    @GetMapping("/deleteById")
    public String delete(@RequestParam int collectionCentersId) {
        service.delete(collectionCentersId);
        return "redirect:/collectionCenters/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int collectionCentersId, Model model) {

        model.addAttribute("collectionCenter", service.getById(collectionCentersId));
        return "collectionCenters/edit";
    }

    @GetMapping("/findById")
    @ResponseBody
    public CollectionCenters find(@RequestParam int collectionCentersId) {
        return service.getById(collectionCentersId);

    }

    @PostMapping("/update")
    public String update(CollectionCenters collectionCenters, Model model) {

        model.addAttribute("collectionCenter", collectionCenters);
        service.update(collectionCenters);
        return "redirect:/collectionCenters/index";
    }
}
