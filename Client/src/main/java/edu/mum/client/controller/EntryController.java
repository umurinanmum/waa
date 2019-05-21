package edu.mum.client.controller;

import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockModel;
import edu.mum.client.model.EntryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/entries")
public class EntryController {

    private String api_url = Constants.URL + "entries";

    private final TokenHelper tokenHelper;

    @Autowired
    public EntryController(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @GetMapping("/list")
    public String getAll(Model model){

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<EntryModel[]> entity = new HttpEntity<EntryModel[]>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<EntryModel[]> response = restTemplate.exchange(api_url, HttpMethod.GET, entity, EntryModel[].class);
            final List<EntryModel> res = Arrays.stream(response.getBody()).collect(Collectors.toList());

            model.addAttribute("entries", res);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "entries/entry-list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute EntryModel entryModel){
        System.out.println("entries/entry-add");
        return "entries/entry-add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute EntryModel entryModel,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model){
        String result_str = "saved";
        try {
            if (bindingResult.hasErrors()) {
                return "entries/entry-add";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<EntryModel> entity = new HttpEntity<>(entryModel, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.postForEntity(api_url, entity, String.class);
            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "entries/entry-add";
            }

            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("entryModel", entryModel);
            model.addAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "entries/entry-add";
        }
        return "redirect:list";
    }

    @GetMapping("/edit/{entryid}")
    public String edit(@PathVariable("entryid") Long entryid,
                       Model model){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());

            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<EntryModel> response = restTemplate.exchange(api_url + "/" + entryid.toString(), HttpMethod.GET, entity, EntryModel.class);

            EntryModel mod = response.getBody();
            System.out.println("response: " + mod);

            model.addAttribute("entryModel", mod);
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        return "entries/entry-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute EntryModel entryModel,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model){
        try {
            if (bindingResult.hasErrors()) {
                return "entries/entry-edit";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<EntryModel> entity = new HttpEntity<>(entryModel, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.exchange(api_url, HttpMethod.PUT, entity, String.class);

            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "entries/entry-edit";
            }

            String result_str = "edited";
            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "entries/entry-edit";
        }
        return "redirect:list";
    }

    @GetMapping("/delete/{entryid}")
    public String delete(@PathVariable("entryid") Long entryid,
                         RedirectAttributes redirectAttributes,
                         Model model){

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.exchange(api_url + "/" + entryid, HttpMethod.DELETE, entity, String.class);
            System.out.println("result: " + result.getBody());

            String result_str = "Deleted";
            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            model.addAttribute("resultInfo", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("resultInfo", e);
        }
        return "result";
    }
}
