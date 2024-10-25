package com.hk.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.board.dtos.HkDto;
import com.hk.board.service.HkServiceImp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(value = "/thboard")
public class ThController {
	@Autowired
	private HkServiceImp hkServiceImp;
	@GetMapping("/home")
	public String home() {
		return "thymeleaf/home";
	}
	@GetMapping("/boardlist")
	public String boardList(Model model) {
		List<HkDto> list=hkServiceImp.getAllList();
		model.addAttribute("list",list);
		return "thymeleaf/boardlist";
	}
	@GetMapping("/insertboardform")
	public String insertboardform(Model model) {
		return "thymeleaf/insertboardform";
	}
	@GetMapping("/insertboard")
	public String insertboard(Model model,HkDto dto) {
		System.out.println(dto);
		boolean isS=hkServiceImp.insertBoard(dto);
		if(isS) {
			return "redirect:/thboard/boardlist";
		}else {
			return "error";
		}
	}
	@GetMapping("/boarddetail")
	public String boarddetail(Model model,int seq) {
		HkDto dto=hkServiceImp.getBoard(seq);
		model.addAttribute("dto",dto);
		return "thymeleaf/boarddetail";
	}
	@GetMapping("/boardupdateform")
	public String boardupdateform(@RequestParam(value="seq") int param,Model model) {
		HkDto dto=hkServiceImp.getBoard(param);
		model.addAttribute("dto",dto);
		return "thymeleaf/boardupdateform";
	}
	@PostMapping("/boardupdate")
	public String boardupdate(HkDto dto) {
		boolean isS=hkServiceImp.updateBoard(dto);
		if(isS) {
			return "redirect:/thboard/boarddetail?seq="+dto.getSeq();
		}else {
			return "error";
		}
	}
//	@PostMapping("/muldel")
	@RequestMapping(value="/muldel")
	public String muldel(String[] chk) {
		boolean isS=hkServiceImp.mulDel(chk);
		if(isS) {
			return "redirect:/thboard/boardlist";
		}else {
			return "error";
		}
	}
}