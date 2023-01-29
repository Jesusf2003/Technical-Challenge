package com.challenge.domain;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class Users {

	@Id
	@Column("id")
	public Long id;
	@Column("username")
	public String username;
	@Column("password")
	public String password;
}