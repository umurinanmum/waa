package edu.mum.client.controller;


import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockModel;
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
@RequestMapping("/blocks")
public class BlockController {


    private String api_url = Constants.URL + "blocks";

    private final TokenHelper tokenHelper;

    @Autowired
    public BlockController(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @GetMapping("/list")
    public String getAll(Model model){

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<BlockModel[]> entity = new HttpEntity<BlockModel[]>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<BlockModel[]> response = restTemplate.exchange(api_url, HttpMethod.GET, entity, BlockModel[].class);
            final List<BlockModel> students = Arrays.stream(response.getBody()).collect(Collectors.toList());

            model.addAttribute("blocks", students);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "blocks/block-list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute BlockModel blockModel){
        System.out.println("blocks/block-add");
        return "blocks/block-add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute BlockModel blockModel,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model){
        String result_str = "saved";
        try {
            if (bindingResult.hasErrors()) {
                return "blocks/block-add";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<BlockModel> entity = new HttpEntity<>(blockModel, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.postForEntity(api_url, entity, String.class);
            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "blocks/block-add";
            }

            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("blockModel", blockModel);
            model.addAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "blocks/block-add";
        }
        return "redirect:list";
    }


    @GetMapping("/edit/{blockid}")
    public String edit(@PathVariable("blockid") Long blockid,
                       Model model){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());

            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<BlockModel> response = restTemplate.exchange(api_url + "/" + blockid.toString(), HttpMethod.GET, entity, BlockModel.class);

            BlockModel mod = response.getBody();
            System.out.println("response: " + mod);

            model.addAttribute("blockModel", mod);
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        return "blocks/block-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute BlockModel blockModel,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model){
        try {
            if (bindingResult.hasErrors()) {
                return "blocks/block-edit";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<BlockModel> entity = new HttpEntity<>(blockModel, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.exchange(api_url, HttpMethod.PUT, entity, String.class);

            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "blocks/block-edit";
            }

            String result_str = "edited";
            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "blocks/block-edit";
        }
        return "redirect:list";
    }

    @GetMapping("/delete/{blockid}")
    public String delete(@PathVariable("blockid") Long blockid,
                         RedirectAttributes redirectAttributes,
                         Model model){

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.exchange(api_url + "/" + blockid, HttpMethod.DELETE, entity, String.class);
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
