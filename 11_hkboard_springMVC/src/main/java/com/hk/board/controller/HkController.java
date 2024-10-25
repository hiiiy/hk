package com.hk.board.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.board.dtos.HkDto;
import com.hk.board.service.IHkService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HkController {
	@Autowired
	private IHkService hkService;
	//command확인 X
	public String login(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.getAttribute("ldto");
//		session.setAttribute("ldto",ldto);
		return "";
	}
	@RequestMapping(value="/boardlist.do", method = RequestMethod.GET)
	public String boardList(Model model) {
		System.out.println("글목록 요청");
		List<HkDto>	list=hkService.getAllList();
//		request.setAttribute("list",list)와 같은 기능
		model.addAttribute("list",list);
		//viewResolver객체가 실행시켜줌
		return "boardlist";
//		return "redirect:boardlist.do";//redirect 방식
	}
	//글추가폼이동
	@RequestMapping(value="/insertboardform.do",method=RequestMethod.GET)
	public String insertBoardForm(){
		System.out.println("글추가폼이동");
//		return "redirect:insertboardform";
		return "insertboardform";
	}
	@RequestMapping(value="/insertboard.do",method=RequestMethod.POST)
	//restAPI 구현 URL 맵핑방법
//	@GetMapping("/insertBoard.do")
//	@PostMapping
//	@PutMapping
//	@DeleteMapping
	public String insertBoard(Model model,HkDto dto,HttpServletRequest request) {
		System.out.println(dto);
		boolean isS=hkService.insertBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg","글추가실패");
			return "board/error";
		}
	}
	//상세보기
	@RequestMapping(value="/boarddetail.do",method=RequestMethod.GET)
	public String getBoard(Model model,int seq) {
		System.out.println("글 상세조회");
		HkDto dto=hkService.getBoard(seq);
		model.addAttribute("dto",dto);
		return "boarddetail";
	}
	//수정폼이동
	@RequestMapping(value="/boardupdateform.do",method=RequestMethod.GET)
	public String updateBoardForm(Model model,int seq) {
		System.out.println("글 수정폼 이동");
		HkDto dto=hkService.getBoard(seq);
		model.addAttribute("dto",dto);
		return "boardupdateform";
	}
	//수정하기
	@RequestMapping(value="/boardupdate.do",method=RequestMethod.POST)
	public String updateBoard(Model model,HkDto dto) {
		System.out.println("글 수정하기");
		boolean isS=hkService.updateBoard(dto);
		if(isS) {
			return "redirect:boarddetail.do?seq="+dto.getSeq();
		}else {
			model.addAttribute("msg","수정실패");
			return "error";
		}
	}
	//삭제하기
		@RequestMapping(value="/muldel.do",method= {RequestMethod.POST,RequestMethod.GET})
		public String mulDel(Model model,HkDto dto,String[] chk) {
//			String[] chks=request.getParameterValues("chk");
			System.out.println("글 삭제하기");
			boolean isS=hkService.mulDel(chk);
			if(isS) {
				return "redirect:boardlist.do";
			}else {
				model.addAttribute("msg","삭제실패");
				return "error";
			}
		}
}
