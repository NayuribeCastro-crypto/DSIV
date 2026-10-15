
package cr.ac.urc.ie.veridis.controller;


import cr.ac.urc.ie.veridis.domain.RecyclingInventory;
import cr.ac.urc.ie.veridis.service.IRecyclingInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recyclingInventories")
public class RecyclingInventoryController {
  
   
 @Autowired
    private IRecyclingInventoryService service;
  
    @GetMapping("/index")
    public String index(Model model) {

        
        model.addAttribute("recyclingInventories", service.getAll());
        return "recyclingInventories/index";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {

        model.addAttribute("recyclingInventory", new RecyclingInventory());
        return "recyclingInventories/create";
    }

    @PostMapping("/save")
    public String save(RecyclingInventory recyclingInventory, Model model) {
        model.addAttribute("recyclingInventory", recyclingInventory);

        service.save(recyclingInventory);
        return "redirect:/recyclingInventories/index";
    }

    
    @GetMapping("/deleteById")
    public String delete(@RequestParam int recyclingInventoryId) {
        service.delete(recyclingInventoryId);
        return "redirect:/recyclingInventories/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int recyclingInventoryId, Model model) {

        model.addAttribute("recyclingInventory", service.getById(recyclingInventoryId));
        return "recyclingInventories/edit";
    }

    @GetMapping("/findById")
    @ResponseBody
    public RecyclingInventory find(@RequestParam int recyclingInventoryId) {
        return service.getById(recyclingInventoryId);

    }

    @PostMapping("/update")
    public String update(RecyclingInventory recyclingInventory, Model model) {

        model.addAttribute("recyclingInventory", recyclingInventory);
        service.update(recyclingInventory);
        return "redirect:/recyclingInventories/index";
    }
    
}
