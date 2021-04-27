package com.saltlux.mysite.exception;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saltlux.dto.JsonResult;


@ControllerAdvice
public class GlobalExceptionHandler {
	//네이밍을 글로벌로 처리한건 모든 클래스에 걸리는 예외라는 의미
	@ExceptionHandler(Exception.class)
	public void handleException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e) throws Exception {
		// 1. 로깅
		System.out.println(e);

		// 2. 요청 구분
		String accept = request.getHeader("accept");
		if(accept.matches(".*application/json.*")) {
			/* JSON 응답 */
			response.setStatus(HttpServletResponse.SC_OK);//fail되어도 통신은 정상이기때문에 OK를 보낸다.

			
			JsonResult jsonResult = JsonResult.fail(e.toString());
			String jsonString = new ObjectMapper().writeValueAsString(jsonResult);
			
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("utf-8"));//char이기때문에 byte로 만들어줘야한다.
			os.close();
			
		} else {
			// 3. 사과
			request.setAttribute("error", e.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	}
	
	
}