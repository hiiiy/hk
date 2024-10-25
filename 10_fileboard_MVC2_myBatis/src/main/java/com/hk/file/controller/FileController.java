package com.hk.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.file.daos.FileDao;
import com.hk.file.dtos.FileDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("*.file")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getRequestURI()
				.substring(request.getContextPath().length());
		System.out.println("요청내용:"+command);
		FileDao dao=new FileDao();
		if(command.equals("/fileuploadform.file")) {
			response.sendRedirect("uploadform.jsp");
		}else if(command.equals("/fileupload.file")) {
			//1.경로 설정(상대경로,절대경로)
			//-절대경로
			String saveDirectory="C:/Users/user/eclipse-20240627/"+"10_fileboard_MVC2_myBatis/src/main/webapp/upload";
			//-상대경로
//			String saveDirectory=request.getSession().getServletContext().getRealPath("upload");
			System.out.println("업로드경로:"+saveDirectory);
			//2.file 업로드 최대 사이즈 설정:byte 단위
			int maxPostSize=1*1024*1024*10;//10MB
			MultipartRequest multi=null;
			try {
				//MultipartRequest 객체가 생성될때 파일업로드가 실행된다.
				multi=new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8",new DefaultFileRenamePolicy());
				//text 파라미터 받을 경우
				//			multi.getParameter("tilte");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//try-catch 종료
			//DB에 파일정보를 추가하기
			//1.원본파일명 구하기
			//name="filename"
			String origin_name=multi.getOriginalFileName("filename");
			System.out.println("원본파일명:"+origin_name);
			//2.저장 파일명 구하기:UUID 객체--->32자리 값을 구함
			String random32=
			UUID.randomUUID().toString().replace("-", "");//제거하기
			String stored_name=random32+(origin_name.lastIndexOf("."));
			System.out.println("저장파일명:"+stored_name);
			//3.파일사이즈 구하기
			int file_size=(int)multi.getFile("filename").length();
			System.out.println("파일사이즈:"+file_size);
			//4.DB에 정보 추가하기
			boolean isS=dao.insertFile(new FileDto(0,origin_name,stored_name,file_size,null));
			//5.업로드된 파일명을 변경하기
			//old 이름--->stored 이름 변경
			File oldFile=new File(saveDirectory+"/"+multi.getFilesystemName("filename"));
			File newFile=new File(saveDirectory+"/"+stored_name);
			oldFile.renameTo(newFile);//old 명-->new 명으로 변경
			response.sendRedirect("uploadform.jsp");
		}else if(command.equals("/downloadlist.file")) {
			List<FileDto> list=dao.getFileList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("filelist.jsp").forward(request, response);
		}else if(command.equals("/download.file")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			//origin_name값은 다운로드할때 사용자에게 보내줄 파일명
			//stored_name값은 실제 파일의 경로를 구할때 사용
			FileDto dto=dao.getFileInfo(seq);
			//파일의 저장 경로
			String saveDirectory=request.getSession().getServletContext().getRealPath("upload");
			String filePath=saveDirectory+"/"+dto.getStored_name();
			//다운로드 환경을 설정
			response.reset();
			//다운로드 파일의 형식 설정
			response.setContentType("application/octet-stream");
			//다운로드 파일명 인코딩 처리
			String fileName=new String(dto.getOrigin_name().getBytes("utf-8"),"8859_1");
			//파일 다운로드할때 저장화면창을 제공하면서 원본파일명이 초기값으로 보기에 처리
			response.setHeader("content-Disposition", "attachment;filename="+fileName);
			//다운로드 환경 설정 종료
			
			//다운로드 구현
			//디렉터리에 저장파일 -->자바-->클라이언트로 출력
			File file=new File(filePath);//file 객체를 생성
			byte[] b=new byte[(int)file.length()];
			FileInputStream in=null;//입력
			ServletOutputStream out=null;//출력
			try {
				in=new FileInputStream(file);
				out=response.getOutputStream();
				int numRead=0;//읽어들이는 값의 개수를 저장할 변수
				while((numRead=in.read(b,0,b.length))!=-1) {
					out.write(b,0,numRead);//클라이언트로 출력
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}finally {
				out.flush();
				out.close();
				in.close();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
