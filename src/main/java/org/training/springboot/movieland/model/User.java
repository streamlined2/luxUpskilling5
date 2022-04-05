package org.training.springboot.movieland.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@EqualsAndHashCode
public class User {

	private Long id;
	private String name;
	private String login;
	private String password;
	private String role;
	
}
