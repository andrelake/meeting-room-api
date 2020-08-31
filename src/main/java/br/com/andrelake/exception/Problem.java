package br.com.andrelake.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Problem {

	private Date timestamp;
	private String message;
	private String details;
}
