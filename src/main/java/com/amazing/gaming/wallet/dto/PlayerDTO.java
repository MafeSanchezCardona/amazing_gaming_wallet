package com.amazing.gaming.wallet.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDTO
{
	@NotEmpty(message = "The name must not be empty")
	private String name;

	@NotEmpty(message = "The last name must not be empty")
	private String lastName;
}
