package com.amazing.gaming.wallet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="player")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;

	@NotEmpty(message = "The name must not be empty")
	@Column(name="name")
	private String name;

	@NotEmpty(message = "The last name must not be empty")
	@Column(name="lastname")
	private String lastName;
}
